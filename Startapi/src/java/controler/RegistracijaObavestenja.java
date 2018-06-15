package controler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Grupa;
import model.GrupaDAO;
import model.Investitor;
import model.InvestitorDao;
import model.Korisnik;
import model.Obavestenje;
import model.Startap;
import model.Vidljivost;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.DualListModel;

@ManagedBean
@ViewScoped
public class RegistracijaObavestenja {

    private String vidljivostIzabrana = "";
    private Investitor investitorId;
    private Obavestenje obavestenje = new Obavestenje();
    private boolean prSt = false;
    private boolean grupe = false;
    private DualListModel<Startap> startapovi;
    private ArrayList<Startap> rezultatPretrageSta;
    private ArrayList<Startap> izabraniStartapovi;
    private ArrayList<Grupa> korisnikoveGrupe;

    @ManagedProperty("#{nadjiStartapove}")
    private NadjiStartapove ns;

    public RegistracijaObavestenja() {
        this.ns = new NadjiStartapove();
    }

    @PostConstruct
    public void init() {
        korisnikoveGrupe = GrupaDAO.dohvatiKorisnikoveGrupe();
        izabraniStartapovi = new ArrayList<>();
        rezultatPretrageSta = new ArrayList<>();
        startapovi = new DualListModel<>(rezultatPretrageSta, izabraniStartapovi);
    }

    public Obavestenje getObavestenje() {
        return obavestenje;
    }

    public void setObavestenje(Obavestenje obavestenje) {
        this.obavestenje = obavestenje;
    }

    public String getVidljivostIzabrana() {
        return vidljivostIzabrana;
    }

    public void setVidljivostIzabrana(String vidljivostIzabrana) {
        this.vidljivostIzabrana = vidljivostIzabrana;
    }

    public Investitor getInvestitorId() {
        return investitorId;
    }

    public void setInvestitorId(Investitor investitorId) {
        this.investitorId = investitorId;
    }

    public boolean isPrSt() {
        return prSt;
    }

    public void setPrSt(boolean prSt) {
        this.prSt = prSt;
    }

    public boolean isGrupe() {
        return grupe;
    }

    public void setGrupe(boolean grupe) {
        this.grupe = grupe;
    }

    public DualListModel<Startap> getStartapovi() {
        return startapovi;
    }

    public void setStartapovi(DualListModel<Startap> startapovi) {
        this.startapovi = startapovi;
    }

    public ArrayList<Startap> getRezultatPretrageSta() {
        return rezultatPretrageSta;
    }

    public void setRezultatPretrageSta(ArrayList<Startap> rezultatPretrageSta) {
        this.rezultatPretrageSta = rezultatPretrageSta;
    }

    public ArrayList<Startap> getIzabraniStartapovi() {
        return izabraniStartapovi;
    }

    public void setIzabraniStartapovi(ArrayList<Startap> izabraniStartapovi) {
        this.izabraniStartapovi = izabraniStartapovi;
    }

    public NadjiStartapove getNs() {
        return ns;
    }

    public void setNs(NadjiStartapove ns) {
        this.ns = ns;
    }

    public ArrayList<Grupa> getKorisnikoveGrupe() {
        return korisnikoveGrupe;
    }

    public void setKorisnikoveGrupe(ArrayList<Grupa> korisnikoveGrupe) {
        this.korisnikoveGrupe = korisnikoveGrupe;
    }

    public void otvoriPoljaZaPretragu() {
        if (vidljivostIzabrana.equals("pretragaSt")) {
            prSt = true;
            grupe = false;
        } else if (vidljivostIzabrana.equals("grupa")) {
            grupe = true;
            prSt = false;
        } else {
            grupe = false;
            prSt = false;
        }
    }

    public void pretraziSt() {
        rezultatPretrageSta = ns.Pretrazi();
        startapovi.setSource(rezultatPretrageSta);
    }

    public String sacuvajObavestenje() {
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        ArrayList<Korisnik> izabraniKor = new ArrayList<>();
        if (user.getAttribute("ulogovaniKorisnik") != null && user.getAttribute("tip").equals("investitor")) {
            investitorId = (Investitor) user.getAttribute("ulogovaniKorisnik");
            obavestenje.setInvestitorId(investitorId);

            switch (vidljivostIzabrana) {
                case ("svi"):
                    obavestenje.setVidljivost((short) Vidljivost.SVI.getSifra());
                    break;
                case ("startapovi"):
                    obavestenje.setVidljivost((short) Vidljivost.STARTAPI.getSifra());
                    break;

                case ("pretragaSt"):
                    obavestenje.setVidljivost((short) Vidljivost.CUSTOM.getSifra());
                    if (!startapovi.getTarget().isEmpty()) {
                        HashSet<Startap> start = new HashSet(startapovi.getTarget());
                        for (Startap st : start) {
                            izabraniKor.add(st);
                        }
                    }
                    break;
                case ("grupa"):
                    obavestenje.setVidljivost((short) Vidljivost.CUSTOM.getSifra());
                    //dodaj grupe!!!
                    break;
                case ("korisnici"):
                    obavestenje.setVidljivost((short) Vidljivost.KORISNICI.getSifra());

            }
            //dodaj da idu mejlom oni koji treba da idu mejlom!!!!

            if (!izabraniKor.isEmpty()) {
                obavestenje.setKorisnikCollection(izabraniKor);
            }
            if(obavestenje.getDatumIVremeKreiranja()== null){
                obavestenje.setDatumIVremeKreiranja(new Date());
            }
        } else {

            return "/index?faces-redirect=true";
        }

        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(obavestenje);

            tx.commit();

            if (obavestenje.getTipObavestenjaValjda().equals("mail")) {
                Mail.posaljiObavestenjeMejlom(obavestenje);
            }

            return "/index?faces-redirect=true";
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
            return null;
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }

    }

}

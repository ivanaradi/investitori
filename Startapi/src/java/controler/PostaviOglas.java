/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Investitor;
import model.Korisnik;
import model.Oglas;
import model.Startap;
import model.Vidljivost;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@ViewScoped
public class PostaviOglas implements Serializable{

    private Oglas oglas = new Oglas();
    private Investitor investitorId;
    private String vidljivostIzabrana;
    private boolean prSt = false;
    private boolean grupe = false;
    private DualListModel<Startap> startapovi;
    private ArrayList<Startap> rezultatPretrageSta;
    private ArrayList<Startap> izabraniStartapovi;
    @ManagedProperty("#{nadjiStartapove}")
    private NadjiStartapove ns;

    public PostaviOglas() {
        this.ns = new NadjiStartapove();
    }

    @PostConstruct
    public void init() {
        izabraniStartapovi = new ArrayList<>();
        rezultatPretrageSta = new ArrayList<>();
        startapovi = new DualListModel<>(rezultatPretrageSta, izabraniStartapovi);
    }

    public boolean isGrupe() {
        return grupe;
    }

    public void setGrupe(boolean grupe) {
        this.grupe = grupe;
    }

    
    public NadjiStartapove getNs() {
        return ns;
    }

    public void setNs(NadjiStartapove ns) {
        this.ns = ns;
    }

    public String getVidljivostIzabrana() {
        return vidljivostIzabrana;
    }

    public void setVidljivostIzabrana(String vidljivostIzabrana) {
        this.vidljivostIzabrana = vidljivostIzabrana;
    }

    public boolean isPrSt() {
        return prSt;
    }

    public void setPrSt(boolean prSt) {
        this.prSt = prSt;
    }

    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas oglas) {
        this.oglas = oglas;
    }

    public Investitor getInvestitorId() {
        return investitorId;
    }

    public void setInvestitorId(Investitor investitorId) {
        this.investitorId = investitorId;
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

    /*
    sakriva i pokazuje polja za pretragu prema izabranoj vidljivosti, ako korisnik izabere da vide rezultati pretrage startapova, pokazuje se panel group koji ukljucuje 
    parametriPretrage stranice iz template, polja sa stranice su vezana za NadjiStartapove
    ako izabere grupe izlistace se korisnikove grupe i moci ce da izabere jednu ili vise grupa DODAJ!!!
    */
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
    
   
    /*
    metoda pokrece metodu za pretragu iz bean-a NadjiStartap
    sva polja za pretragu sa stranice PostaviOglas su vec vezana za NadjiStartap
    metoda vraca rezultat i postavlja ga za source dual liste startapova
    */
    public void pretraziSt() {
        rezultatPretrageSta = ns.Pretrazi();
        startapovi.setSource(rezultatPretrageSta);
    }

    /*
    metoda unosi oglas i postavlja vidljivost 
    DODAJ GRUPE!
    */
    public String dodaj() {

        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        ArrayList<Korisnik> izabraniKor = new ArrayList<>();
        if (user.getAttribute("ulogovaniKorisnik") != null && user.getAttribute("tip").equals("investitor")) {
            investitorId = (Investitor) user.getAttribute("ulogovaniKorisnik");
            oglas.setInvestitorId(investitorId);

            switch (vidljivostIzabrana) {
                case ("svi"):
                    oglas.setVidljivost((short) Vidljivost.SVI.getSifra());
                    break;
                case ("startapovi"):
                    oglas.setVidljivost((short) Vidljivost.STARTAPI.getSifra());
                    break;

                case ("pretragaSt"):
                    oglas.setVidljivost((short) Vidljivost.CUSTOM.getSifra());
                    if (!startapovi.getTarget().isEmpty()) {
                        HashSet<Startap> start = new HashSet(startapovi.getTarget());
                        for (Startap st : start) {
                            izabraniKor.add(st);
                        }
                    }
                    break;
                case ("grupa"):
                    oglas.setVidljivost((short) Vidljivost.CUSTOM.getSifra());
                    //dodaj grupe!!!
                    break;
                case ("korisnici"):
                     oglas.setVidljivost((short) Vidljivost.KORISNICI.getSifra());

            }

            if (!izabraniKor.isEmpty()) {
                oglas.setKorisnikCollection(izabraniKor);
            }
        } else {
            
            return "/Oglasi?faces-redirect=true";
        }

        Session s = HibernateUtil.createSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        try {
            // sta je tip oglasa??
            oglas.setTipOglasa("nesto");
            s.save(oglas);
            t.commit();
            
            return ("/user/MojiOglasi?faces-redirect=true");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println(e);
            return null;
        } finally {

            try {
                s.close();
            } catch (HibernateException ignored) {
                System.out.println("Couldn't close Session " + ignored);
            }

        }
    }

}

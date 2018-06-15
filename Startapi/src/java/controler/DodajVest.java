/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.*;
import model.Korisnik;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Korisnik
 */

//dodajem koment
@ManagedBean
@ViewScoped
public class DodajVest implements Serializable {

    private Vest vest = new Vest();
    private Korisnik k;
    private List<Kategorijavesti> sveKategorije = KategorijeVestiDAO.dohvatiVazeceKategorije();
    private int kvId = 0;
    private String vidljivostIzabrana;

    private boolean prSt = false;
    private boolean prInv = false;
    private DualListModel<Startap> startapovi;
    private DualListModel<Investitor> investitori;
    // private ArrayList<Integer> izabraniInvestitori = new ArrayList<>();
    private ArrayList<Startap> rezultatPretrageSta;
    private ArrayList<Startap> izabraniStartapovi;
    private ArrayList<Investitor> rezultatPretrageInv;
    private ArrayList<Investitor> izabraniInvestitori;

    private ArrayList<String> drzave = new ArrayList<>();
    private String izabraneDrzave = "";
    private ArrayList<Grad> dohvaceniGradovi = new ArrayList<>();
    private ArrayList<String> gradovi = new ArrayList<>();
    private String izabraniGradovi = "";
    private ArrayList<String> delatnosti = new ArrayList<>();
    private String izabraneDelatnosti = "";
    @ManagedProperty("#{nadjiInvestitore}")
    private NadjiInvestitore ni;
    @ManagedProperty("#{nadjiStartapove}")
    private NadjiStartapove ns;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DodajVest() {
        this.ni = new NadjiInvestitore();

        this.ns = new NadjiStartapove();
    }

    @PostConstruct
    public void init() {
        izabraniStartapovi = new ArrayList<>();
        rezultatPretrageSta = new ArrayList<>();
        startapovi = new DualListModel<>(rezultatPretrageSta, izabraniStartapovi);
        izabraniInvestitori = new ArrayList<>();
        rezultatPretrageInv = new ArrayList<>();
        investitori = new DualListModel<>(rezultatPretrageInv, izabraniInvestitori);

    }

    public DualListModel<Investitor> getInvestitori() {
        return investitori;
    }

    public void setInvestitori(DualListModel<Investitor> investitori) {
        this.investitori = investitori;
    }

    public ArrayList<Investitor> getRezultatPretrageInv() {
        return rezultatPretrageInv;
    }

    public void setRezultatPretrageInv(ArrayList<Investitor> rezultatPretrageInv) {
        this.rezultatPretrageInv = rezultatPretrageInv;
    }

    public ArrayList<Investitor> getIzabraniInvestitori() {
        return izabraniInvestitori;
    }

    public void setIzabraniInvestitori(ArrayList<Investitor> izabraniInvestitori) {
        this.izabraniInvestitori = izabraniInvestitori;
    }

    public NadjiInvestitore getNi() {
        return ni;
    }

    public void setNi(NadjiInvestitore ni) {
        this.ni = ni;
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

    public ArrayList<String> getDrzave() {
        return drzave;
    }

    public void setDrzave(ArrayList<String> drzave) {
        this.drzave = drzave;
    }

    public String getIzabraneDrzave() {
        return izabraneDrzave;
    }

    public void setIzabraneDrzave(String izabraneDrzave) {
        this.izabraneDrzave = izabraneDrzave;
    }

    public ArrayList<Grad> getDohvaceniGradovi() {
        return dohvaceniGradovi;
    }

    public void setDohvaceniGradovi(ArrayList<Grad> dohvaceniGradovi) {
        this.dohvaceniGradovi = dohvaceniGradovi;
    }

    public ArrayList<String> getGradovi() {
        return gradovi;
    }

    public void setGradovi(ArrayList<String> gradovi) {
        this.gradovi = gradovi;
    }

    public String getIzabraniGradovi() {
        return izabraniGradovi;
    }

    public void setIzabraniGradovi(String izabraniGradovi) {
        this.izabraniGradovi = izabraniGradovi;
    }

    public boolean isPrSt() {
        return prSt;
    }

    public void setPrSt(boolean prSt) {
        this.prSt = prSt;
    }

    public boolean isPrInv() {
        return prInv;
    }

    public void setPrInv(boolean prInv) {
        this.prInv = prInv;
    }

    public int getKvId() {
        return kvId;
    }

    public void setKvId(int kvId) {
        this.kvId = kvId;
    }

    public String getVidljivostIzabrana() {
        return vidljivostIzabrana;
    }

    public void setVidljivostIzabrana(String vidljivostIzabrana) {
        this.vidljivostIzabrana = vidljivostIzabrana;
    }

    public int getKv() {
        return kvId;
    }

    public void setKv(int kv) {
        this.kvId = kv;
    }

    public List<Kategorijavesti> getSveKategorije() {

        return sveKategorije;
    }

    public void setSveKategorije(List<Kategorijavesti> sveKategorije) {
        this.sveKategorije = sveKategorije;
    }

    public Vest getVest() {
        return vest;
    }

    public void setVest(Vest vest) {
        this.vest = vest;
    }

    public Korisnik getK() {
        return k;
    }

    public void setK(Korisnik k) {
        this.k = k;
    }

    public ArrayList<String> getDelatnosti() {
        return delatnosti;
    }

    public void setDelatnosti(ArrayList<String> delatnosti) {
        this.delatnosti = delatnosti;
    }

    public String getIzabraneDelatnosti() {
        return izabraneDelatnosti;
    }

    public void setIzabraneDelatnosti(String izabraneDelatnosti) {
        this.izabraneDelatnosti = izabraneDelatnosti;
    }

    public NadjiStartapove getNs() {
        return ns;
    }

    public void setNs(NadjiStartapove ns) {
        this.ns = ns;
    }

    private java.sql.Timestamp sqlDate(java.util.Date datum) {
        return new java.sql.Timestamp(datum.getTime());
    }

    // pokazuje polja za pretragu na strani kada se izabere pretrazi startapa/investitora iz padajuceg menija
    public void otvoriPoljaZaPretragu() {
        if (vidljivostIzabrana.equals("pretragaSt")) {
            prSt = true;
            prInv = false;
        } else if (vidljivostIzabrana.equals("pretragaInv")) {
            prInv = true;
            prSt = false;
        } else {
            prInv = false;
            prSt = false;
        }
    }

    /*
    metoda proverava da li unet neki parametar pretrage i ako jeste poziva metodu bean-a NadjiStartap koja vrsi pretragu
    vraca kao rezultat arraylistu koja se postavlja kao source dual liste
    PARAMETRI PRETRAGE IZ TEMPLATE!!
     */
    public void pretraziSt() {
        if (ns.getTekstPretrage().equals("") && ns.getMinVrednost() == 0 && ns.getMaxVrednost() == 0
                && ns.getDelatnosti().isEmpty() && ns.getMaxBrojZaposlenih() == 0 && ns.getMinBrojZaposlenih() == 0 && ns.getMaxPrihodTriGodine() == 0 && ns.getMinPrihodTriGodine() == 0
                && ns.getMaxProfitTriGodine() == 0 && ns.getMinProfitTriGodine() == 0 && ns.getTrenutnaFaza().isEmpty() && ns.getOblastiPoslovanja().isEmpty() && ns.getDrzave().isEmpty() && ns.getGradovi().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Morate uneti neki parametar za pretragu"));
        } else {

            rezultatPretrageSta = ns.Pretrazi();
            startapovi.setSource(rezultatPretrageSta);
        }
    }

    //pretrazi investitore i rezultat stavi u source pick list
    public void pretraziIn() {
        if (ni.getTekstPretrage().equals("") && ni.getMinVrednost() == 0 && ni.getMaxVrednost() == 0
                && ni.getDelatnosti().isEmpty() && ni.getMaxBrojZaposlenih() == 0 && ni.getMinBrojZaposlenih() == 0 && ni.getMaxPrihodTriGodine() == 0 && ni.getMinPrihodTriGodine() == 0
                && ni.getMaxProfitTriGodine() == 0 && ni.getMinProfitTriGodine() == 0 && ni.getUsluge().isEmpty() && ni.getOblastiPoslovanja().isEmpty() && ni.getDrzave().isEmpty() && ni.getGradovi().isEmpty()
                && ni.getInteresovanja().isEmpty() && ni.getProfVestine().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Morate uneti neki parametar za pretragu"));
        } else {

            rezultatPretrageInv = ni.Pretrazi();
            investitori.setSource(rezultatPretrageInv);
        }
    }

    public void prikaziIzgledVesti() {
        
    }

    /*
    metoda dodaje vest u bazu i popunjava tabelu vidljivostvesti ako je izabrana opcija pretrage
    vraca stranicu sa svim vestima
     */
    public String dodaj() {

        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        ArrayList<Korisnik> izabraniKor = new ArrayList<>();

        if (user.getAttribute("ulogovaniKorisnik") == null) {
            return "/index?faces-redirect=true";
        }
        if (user.getAttribute("ulogovaniKorisnik") != null) {
            k = (Korisnik) user.getAttribute("ulogovaniKorisnik");
            vest.setKorisnikId(k);
            for (Kategorijavesti kv : sveKategorije) {
                if (kv.getId() == kvId) {
                    vest.setKategorijaVestiId(kv);
                }
            }
            switch (vidljivostIzabrana) {
                case ("svi"):
                    vest.setVidljivost((short) Vidljivost.SVI.getSifra());
                    break;
                case ("startapovi"):
                    vest.setVidljivost((short) Vidljivost.STARTAPI.getSifra());
                    break;
                case ("investitori"):
                    vest.setVidljivost((short) Vidljivost.INVESTITORI.getSifra());
                    break;
                case ("pretragaSt"):
                    vest.setVidljivost((short) Vidljivost.CUSTOM.getSifra());
                    if (!startapovi.getTarget().isEmpty()) {
                        HashSet<Startap> start = new HashSet(startapovi.getTarget());
                        for (Startap st : start) {
                            izabraniKor.add(st);
                        }
                    }
                    break;
                case ("pretragaInv"):
                    vest.setVidljivost((short) Vidljivost.CUSTOM.getSifra());
                    if (!investitori.getTarget().isEmpty()) {
                        HashSet<Investitor> inv = new HashSet(investitori.getTarget());
                        for (Investitor invest : inv) {
                            izabraniKor.add(invest);
                        }
                    }
                    break;
            }

            if (!izabraniKor.isEmpty()) {
                vest.setKorisnikCollection(izabraniKor);
            }
            if(vest.getVremeKreiranja() == null){
                vest.setVremeKreiranja(new Date());
            }
        }

        Session s = HibernateUtil.createSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        try {
            s.save(vest);
            t.commit();
            //  String linkKaVesti = "/Vest?faces-redirect=true&includeViewParams=true?d=" + sdf.format(vest.getVremeKreiranja()) + "&naziv=" + vest.getNaziv();
            return "/user/MojeVesti?faces-redirect=true";
            // return linkKaVesti;
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

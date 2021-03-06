/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Grad;
import model.GradDAO;
import model.Korisnik;
import model.Startap;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Korisnik
 */
@ManagedBean(name = "nadjiStartapove", eager = true)
@ViewScoped
public class NadjiStartapove implements Serializable {

    private ArrayList<String> drzave = new ArrayList<>();
    private ArrayList<Grad> dohvaceniGradovi = new ArrayList<>();
    private ArrayList<String> gradovi = new ArrayList<>();
    private ArrayList<String> delatnosti = new ArrayList<>();
      private ArrayList<String> interesovanja = new ArrayList<>();
    private ArrayList<String> profVestine = new ArrayList<>();
    private String tekstPretrage = "";
    private boolean napredno = false;
    private Integer minVrednost = 0;
    private Integer maxVrednost = 0;
    private String trenutnaFaza = "";
    private String oblastiPoslovanja = "";
    private Short minBrojZaposlenih = 0;
    private Short maxBrojZaposlenih = 0;
    private Integer minPrihodTriGodine = 0;
    private Integer maxPrihodTriGodine = 0;
    private Integer minProfitTriGodine = 0;
    private Integer maxProfitTriGodine = 0;


    private ArrayList<Startap> rezultatPretrage;


    public ArrayList<String> getDrzave() {
        return drzave;
    }

    public void setDrzave(ArrayList<String> drzave) {
        this.drzave = drzave;
    }

    public ArrayList<String> getInteresovanja() {
        return interesovanja;
    }

    public void setInteresovanja(ArrayList<String> interesovanja) {
        this.interesovanja = interesovanja;
    }

    public ArrayList<String> getProfVestine() {
        return profVestine;
    }

    public void setProfVestine(ArrayList<String> profVestine) {
        this.profVestine = profVestine;
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


    public ArrayList<Startap> getRezultatPretrage() {
        return rezultatPretrage;
    }

    public void setRezultatPretrage(ArrayList<Startap> rezultatPretrage) {
        this.rezultatPretrage = rezultatPretrage;
    }

    public String getTekstPretrage() {
        return tekstPretrage;
    }

    public void setTekstPretrage(String tekstPretrage) {
        this.tekstPretrage = tekstPretrage;
    }

    public ArrayList<String> getDelatnosti() {
        return delatnosti;
    }

    public void setDelatnosti(ArrayList<String> delatnosti) {
        this.delatnosti = delatnosti;
    }

    public boolean isNapredno() {
        return napredno;
    }

    public void setNapredno(boolean napredno) {
        this.napredno = napredno;
    }

    public String getTrenutnaFaza() {
        return trenutnaFaza;
    }

    public void setTrenutnaFaza(String trenutnaFaza) {
        this.trenutnaFaza = trenutnaFaza;
    }

    public String getOblastiPoslovanja() {
        return oblastiPoslovanja;
    }

    public void setOblastiPoslovanja(String oblastiPoslovanja) {
        this.oblastiPoslovanja = oblastiPoslovanja;
    }

    public Short getMinBrojZaposlenih() {
        return minBrojZaposlenih;
    }

    public void setMinBrojZaposlenih(Short minBrojZaposlenih) {
        this.minBrojZaposlenih = minBrojZaposlenih;
    }

    public Short getMaxBrojZaposlenih() {
        return maxBrojZaposlenih;
    }

    public void setMaxBrojZaposlenih(Short maxBrojZaposlenih) {
        this.maxBrojZaposlenih = maxBrojZaposlenih;
    }

    public Integer getMinPrihodTriGodine() {
        return minPrihodTriGodine;
    }

    public void setMinPrihodTriGodine(Integer minPrihodTriGodine) {
        this.minPrihodTriGodine = minPrihodTriGodine;
    }

    public Integer getMaxPrihodTriGodine() {
        return maxPrihodTriGodine;
    }

    public void setMaxPrihodTriGodine(Integer maxPrihodTriGodine) {
        this.maxPrihodTriGodine = maxPrihodTriGodine;
    }

    public Integer getMinProfitTriGodine() {
        return minProfitTriGodine;
    }

    public void setMinProfitTriGodine(Integer minProfitTriGodine) {
        this.minProfitTriGodine = minProfitTriGodine;
    }

    public Integer getMaxProfitTriGodine() {
        return maxProfitTriGodine;
    }

    public void setMaxProfitTriGodine(Integer maxProfitTriGodine) {
        this.maxProfitTriGodine = maxProfitTriGodine;
    }

    public Integer getMaxVrednost() {
        return maxVrednost;
    }

    public void setMaxVrednost(Integer maxVrednost) {
        this.maxVrednost = maxVrednost;
    }

    public Integer getMinVrednost() {
        return minVrednost;
    }

    public void setMinVrednost(Integer minVrednost) {
        this.minVrednost = minVrednost;
    }
    
    /*
    metoda dohvata gradove izabranih drzava sa stranica koje ukljucuju stranicu parametriPretrage iz template
    */

    public ArrayList<Grad> dohvatiGradove() {
        dohvaceniGradovi = new ArrayList<>();
        for (String drzava : drzave) {
            ArrayList<Grad> dohvatiGradovePoNazivuDrzave = GradDAO.dohvatiGradovePoNazivuDrzave(drzava);
            dohvatiGradovePoNazivuDrzave.forEach((dohvGrad) -> {
                dohvaceniGradovi.add(dohvGrad);
            });
        };

        return dohvaceniGradovi;
    }

    private List<String> stringToList(String s) {
        List<String> list = new ArrayList<>(Arrays.asList(s.split(",")));
        return list;
    }

    private String[] splitString(String tekst) {
        String[] parametri = tekst.split("\\s+");
        return parametri;
    }
    
    /*
    metoda proverava da li je unet neki parametar pretrage i vrsi pretragu baze
    vraca kao rezultat listu nadjenih startapova
    poziva se direktno iz stranica koje ukljucuju stranicu parametriPretrage iz template i indirektno iz stranice PretragaStartap
    */

    public ArrayList<Startap> Pretrazi() {
    
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession user = (HttpSession) context.getExternalContext().getSession(true);
        Korisnik ulogovaniKor = (Korisnik) user.getAttribute("ulogovaniKorisnik");
        String upit = "SELECT DISTINCT new Startap(s.id, s.punNaziv) FROM Startap s LEFT JOIN s.projekatCollection p LEFT JOIN s.interesovanjeCollection int LEFT JOIN s.profesionalnevestineCollection pv left join s.ulicaId u left join u.opstinaIdopstina o left join o.gradid g left join g.drzavAid d WHERE s.id <> " + ulogovaniKor.getId();
        Map<String, Object> unosi = new HashMap<>();
        if (tekstPretrage.equals("") && minVrednost == 0 && maxVrednost == 0
                && delatnosti.isEmpty() && maxBrojZaposlenih == 0 && minBrojZaposlenih == 0 && maxPrihodTriGodine == 0 && minPrihodTriGodine == 0
                && maxProfitTriGodine == 0 && minProfitTriGodine == 0 && trenutnaFaza.isEmpty() && oblastiPoslovanja.isEmpty() && drzave.isEmpty() && gradovi.isEmpty()
                && interesovanja.isEmpty() && profVestine.isEmpty()) {
            context.addMessage(null, new FacesMessage("Morate uneti neki parametar za pretragu"));

        }

        if (!tekstPretrage.equals("")) {

            String[] parametri = splitString(tekstPretrage);
            if (parametri != null) {
                upit += " and (";
                for (int i = 0; i < parametri.length; i++) {
                    upit = upit + " s.punNaziv like :p" + i + " or p.naziv like :p" + i + " or";
                    unosi.put("p" + i, "%" + parametri[i] + "%");
                }
                upit = upit.substring(0, upit.length() - 2);
                upit += ")";
            } else {
                unosi.put("tekstPretrage", "%" + tekstPretrage + "%");
                upit += " and (s.punNaziv like :tekstPretrage or p.naziv like :tekstPretrage)";
            }
        }
        if (minVrednost != 0 && maxVrednost == 0) {
            unosi.put("minVrednost", minVrednost);
            upit += " and p.trazeniIznos >= :minVrednost";
        }
        if (maxVrednost != 0) {
            unosi.put("minVrednost", minVrednost);
            unosi.put("maxVrednost", maxVrednost);
            upit += " and p.trazeniIznos between :minVrednost and :maxVrednost";
        }

        if (!trenutnaFaza.equals("")) {
            unosi.put("trenutnaFaza", trenutnaFaza);
            upit += " and s.trenutnaFazaFirme = :trenutnaFaza";
        }

        if (!oblastiPoslovanja.equals("")) {
            unosi.put("tekstPretrage", "%" + oblastiPoslovanja + "%");
            upit += " and s.oblastiPoslovanja like :oblastiPoslovanja";
        }
        if (minBrojZaposlenih != 0 && maxBrojZaposlenih == 0) {
            unosi.put("minBrojZaposlenih", minBrojZaposlenih);
            upit += " and s.brojZaposlenih >= :minBrojZaposlenih";
        }
        if (maxBrojZaposlenih != 0) {
            unosi.put("minBrojZaposlenih", minBrojZaposlenih);
            unosi.put("maxBrojZaposlenih", maxBrojZaposlenih);
            upit += " and s.brojZaposlenih between :minBrojZaposlenih and :maxBrojZaposlenih";
        }

        if (!delatnosti.isEmpty()) {
            upit += " and s.delatnostId.naziv in (";
            for (int i = 0; i < delatnosti.size(); i++) {
                upit = upit + ":delatnosti" + i + " , ";
                unosi.put("delatnosti" + i, delatnosti.get(i));
            }
            upit = upit.substring(0, upit.length() - 2);
            upit += ")";
        }
        
         if (!profVestine.isEmpty()) {
            upit += " and pv.naziv in (";
            for (int i = 0; i < profVestine.size(); i++) {
                upit = upit + ":profVestine" + i + " , ";
                unosi.put("profVestine" + i, profVestine.get(i));
            }
            upit = upit.substring(0, upit.length() - 2);
            upit += ")";
        }

        if (!interesovanja.isEmpty()) {
             upit += " and int.naziv in (";
            for (int i = 0; i < interesovanja.size(); i++) {
                upit = upit + ":interesovanja" + i + " , ";
                unosi.put("interesovanja" + i, interesovanja.get(i));
            }
            upit = upit.substring(0, upit.length() - 2);
            upit += ")";
        
        }
        if (minPrihodTriGodine != 0 && maxPrihodTriGodine == 0) {
            unosi.put("minPrihodTriGodine", minPrihodTriGodine);
            upit += " and s.prihodUPoslednjeTriGodine >= :minPrihodTriGodine";
        }
        if (maxPrihodTriGodine != 0) {
            unosi.put("minPrihodTriGodine", minPrihodTriGodine);
            unosi.put("maxPrihodTriGodine", maxPrihodTriGodine);
            upit += " and s.prihodUPoslednjeTriGodine between :minPrihodTriGodine and :maxPrihodTriGodine";
        }
        if (minProfitTriGodine != 0 && maxProfitTriGodine == 0) {
            unosi.put("minProfitTriGodine", minProfitTriGodine);
            upit += " and s.profitUPoslednjeTriGodine >= :minProfitTriGodine";
        }
        if (maxProfitTriGodine != 0) {
            unosi.put("minProfitTriGodine", minProfitTriGodine);
            unosi.put("maxProfitTriGodine", maxProfitTriGodine);
            upit += " and s.profitUPoslednjeTriGodine between :minProfitTriGodine and :maxProfitTriGodine";
        }
        if (!drzave.isEmpty()) {
            upit += " and g.drzavAid.naziv in (";
            for (int i = 0; i < drzave.size(); i++) {
                upit = upit + ":drzave" + i + " , ";
                unosi.put("drzave" + i, drzave.get(i));
            }
            upit = upit.substring(0, upit.length() - 2);
            upit += ")";

        }

        if (!gradovi.isEmpty()) {
            upit += " and o.gradid.naziv in (";
            for (int i = 0; i < gradovi.size(); i++) {
                upit = upit + ":gradovi" + i + " , ";
                unosi.put("gradovi" + i, gradovi.get(i));
            }
            upit = upit.substring(0, upit.length() - 2);
            upit += ")";

        }

        Transaction t = null;
        Session s = HibernateUtil.createSessionFactory().openSession();

        try {
            Query q = s.createQuery(upit);

            for (String param : q.getNamedParameters()) {
                q.setParameter(param, unosi.get(param));
            }
            
            rezultatPretrage = (ArrayList<Startap>) q.list();
            return rezultatPretrage;
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }

            System.out.println(e);
            return null;
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }

    }

}

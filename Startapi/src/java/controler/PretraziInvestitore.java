/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Grad;
import model.GradDAO;
import model.Investitor;

/**
 *
 * @author Korisnik
 */
@ManagedBean(name = "pretraziInvestitore", eager = true)
@ViewScoped
public class PretraziInvestitore implements Serializable {

    private String tekstPretrage = "";
    private boolean napredno = false;
    private Integer minVrednost = 0;
    private Integer maxVrednost = 0;
    private ArrayList<Investitor> rezultat = new ArrayList<>();
    private String oblastiPoslovanja = "";
    private Short minBrojZaposlenih = 0;
    private Short maxBrojZaposlenih = 0;
    private Integer minPrihodTriGodine = 0;
    private Integer maxPrihodTriGodine = 0;
    private Integer minProfitTriGodine = 0;
    private Integer maxProfitTriGodine = 0;
    private List<String> delatnosti = new ArrayList<>();
    private String izabraneDelatnosti = "";
    private String usluge = "";
    private ArrayList<String> drzave = new ArrayList<>();
    private String izabraneDrzave = "";

    private ArrayList<Grad> dohvaceniGradovi = new ArrayList<>();
    private ArrayList<String> gradovi = new ArrayList<>();
    private String izabraniGradovi = "";
    private ArrayList<String> interesovanja = new ArrayList<>();
    private ArrayList<String> profVestine = new ArrayList<>();
    private String izabranaInteresovanja = "";
    private String izabraneProfVestine = "";

    @ManagedProperty("#{nadjiInvestitore}")
    private NadjiInvestitore ni;

    public PretraziInvestitore() {
        this.ni = new NadjiInvestitore();
    }

    public NadjiInvestitore getNi() {
        return ni;
    }

    public void setNi(NadjiInvestitore ni) {
        this.ni = ni;
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

    public String getIzabraneDelatnosti() {
        return izabraneDelatnosti;
    }

    public void setIzabraneDelatnosti(String izabraneDelatnosti) {
        this.izabraneDelatnosti = izabraneDelatnosti;
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

    public String getUsluge() {
        return usluge;
    }

    public void setUsluge(String usluge) {
        this.usluge = usluge;
    }

    public List<String> getDelatnosti() {
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

    public String getTekstPretrage() {
        return tekstPretrage;
    }

    public void setTekstPretrage(String tekstPretrage) {
        this.tekstPretrage = tekstPretrage;
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

    public ArrayList<Investitor> getRezultat() {
        return rezultat;
    }

    public void setRezultat(ArrayList<Investitor> rezultat) {
        this.rezultat = rezultat;
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

    public String getIzabranaInteresovanja() {
        return izabranaInteresovanja;
    }

    public void setIzabranaInteresovanja(String izabranaInteresovanja) {
        this.izabranaInteresovanja = izabranaInteresovanja;
    }

    public String getIzabraneProfVestine() {
        return izabraneProfVestine;
    }

    public void setIzabraneProfVestine(String izabraneProfVestine) {
        this.izabraneProfVestine = izabraneProfVestine;
    }

    /*
        dohvata gradove iz izabranih drzava sa stranice PretragaInvestitora 
        
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

    /*
    metoda proverava da li je unet neki parametar pretrage, pretvara arrayliste u stringove i stavlja ih u url, redirectuje na stranu sa rezultatima gde se tek pokrece metoda
    koja pretrazuje bazu
     */
    public String prosledi() {
        if (ni.getTekstPretrage().equals("") && ni.getMinVrednost() == 0 && ni.getMaxVrednost() == 0
                && delatnosti.isEmpty() && ni.getMaxBrojZaposlenih() == 0 && ni.getMinBrojZaposlenih() == 0 && ni.getMaxPrihodTriGodine() == 0 && ni.getMinPrihodTriGodine() == 0
                && ni.getMaxProfitTriGodine() == 0 && ni.getMinProfitTriGodine() == 0 && ni.getUsluge().isEmpty() && ni.getOblastiPoslovanja().isEmpty() && drzave.isEmpty() && gradovi.isEmpty()
                && interesovanja.isEmpty() && profVestine.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Morate uneti neki parametar za pretragu"));
            return null;
        }
        if (!delatnosti.isEmpty()) {
            izabraneDelatnosti = String.join(",", delatnosti);
        }
        if (!drzave.isEmpty()) {
            izabraneDrzave = String.join(",", drzave);
        }
        if (!gradovi.isEmpty()) {
            izabraniGradovi = String.join(",", gradovi);
        }
        if (!interesovanja.isEmpty()) {
            izabranaInteresovanja = String.join(",", interesovanja);
        }
        if (!profVestine.isEmpty()) {
            izabraneProfVestine = String.join(",", profVestine);
        }

        return "/user/RezultatInvestitori?faces-redirect=true&amp;includeViewParams=true";
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
    metoda stranice pretragaInvestitora, prebacuje stringove iz url u arrayliste i salje ih kao parametar pretrage beanu NadjiInvestitore i pokrece metodu koja pretrazuje bazu
    rezultati se vracaju u listu rezultatPretrage
    metoda se pokrece iz stranice ReazultatInvestitori
     */
    public void Pretrazi() {
        if (!izabraneDrzave.isEmpty()) {
            ni.setDrzave((ArrayList<String>) stringToList(izabraneDrzave));
        }
        if (!izabraniGradovi.isEmpty()) {
            ni.setGradovi((ArrayList<String>) stringToList(izabraniGradovi));
        }
        if (!izabraneDelatnosti.isEmpty()) {
            ni.setDelatnosti((ArrayList<String>) stringToList(izabraneDelatnosti));
        }
        if (!izabranaInteresovanja.isEmpty()) {
            ni.setInteresovanja((ArrayList<String>) stringToList(izabranaInteresovanja));
        }
        if (!izabraneProfVestine.isEmpty()) {
            ni.setProfVestine((ArrayList<String>) stringToList(izabraneProfVestine));
        }

        rezultat = ni.Pretrazi();
       // return rezultat;

    }

    private Investitor investitor;

    public Investitor getInvestitor() {
        return investitor;
    }

    public void setInvestitor(Investitor investitor) {
        this.investitor = investitor;
    }

    public String pogledajProfil() {
        return ("/index.xhtml?faces-redirect=true&amp;includeViewParams=true");

    }

}

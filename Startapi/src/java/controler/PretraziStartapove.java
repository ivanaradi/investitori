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
import model.Startap;

/**
 *
 * @author Korisnik
 */
@ManagedBean(name = "pretraziStartapove", eager = true)
@ViewScoped
public class PretraziStartapove implements Serializable {

    private ArrayList<String> delatnosti = new ArrayList<>();
    private String izabraneDelatnosti = "";
    private ArrayList<Startap> rezultatPretrage;
    private ArrayList<String> drzave = new ArrayList<>();
    private String izabraneDrzave = "";
    private ArrayList<Grad> dohvaceniGradovi = new ArrayList<>();
    private ArrayList<String> gradovi = new ArrayList<>();
    private String izabraniGradovi = "";
    private ArrayList<String> interesovanja = new ArrayList<>();
    private ArrayList<String> profVestine = new ArrayList<>();
    private String izabranaInteresovanja = "";
    private String izabraneProfVestine = "";
    @ManagedProperty("#{nadjiStartapove}")
    private NadjiStartapove ns;

    public PretraziStartapove() {
        this.ns = new NadjiStartapove();
    }

    public NadjiStartapove getNs() {
        return ns;
    }

    public void setNs(NadjiStartapove ns) {
        this.ns = ns;
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

    public ArrayList<Startap> getRezultatPretrage() {
        return rezultatPretrage;
    }

    public void setRezultatPretrage(ArrayList<Startap> rezultatPretrage) {
        this.rezultatPretrage = rezultatPretrage;
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


    /*
        dohvata gradove iz izabranih drzava sa stranice PretragaStartap
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
    pomocna metoda, odvaja reci unesene u polja za pretragu
    UBACI DA KORISNIK MOZE DA IZABERE DA LI HOCE REZULTATE KOJI NALAZE SVE RECI ILI BAR JEDNU 
     */
    private String[] splitString(String tekst) {
        String[] parametri = tekst.split("\\s+");
        return parametri;
    }

    /*
    pomocna metoda, deli stringove i popunjava arrayliste
     */
    private List<String> stringToList(String s) {
        List<String> list = new ArrayList<>(Arrays.asList(s.split(",")));
        return list;
    }

    /*
    metoda proverava da li je unet neki parametar pretrage, pretvara arrayliste u stringove i stavlja ih u url, redirectuje na stranu sa rezultatima gde se tek pokrece metoda
    koja pretrazuje bazu
     */
    public String prosledi() {
        if (ns.getTekstPretrage().equals("") && ns.getMinVrednost() == 0 && ns.getMaxVrednost() == 0
                && delatnosti.isEmpty() && ns.getMaxBrojZaposlenih() == 0 && ns.getMinBrojZaposlenih() == 0 && ns.getMaxPrihodTriGodine() == 0 && ns.getMinPrihodTriGodine() == 0
                && ns.getMaxProfitTriGodine() == 0 && ns.getMinProfitTriGodine() == 0 && ns.getTrenutnaFaza().isEmpty() && ns.getOblastiPoslovanja().isEmpty() && drzave.isEmpty() && gradovi.isEmpty()
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

        return "/user/RezultatStartapovi?faces-redirect=true&amp;includeViewParams=true";
    }

    /*
    metoda stranice pretragaStartap, prebacuje stringove iz url u arrayliste i salje ih kao parametar pretrage beanu NadjiStartap i pokrece metodu koja pretrazuje bazu
    rezultati se vracaju u listu rezultatPretrage
    metoda se pokrece iz stranice rezultat pretrage
     */
    public ArrayList<Startap> Pretrazi() {
        if (!izabraneDrzave.isEmpty()) {
            ns.setDrzave((ArrayList<String>) stringToList(izabraneDrzave));
        }
        if (!izabraniGradovi.isEmpty()) {
            ns.setGradovi((ArrayList<String>) stringToList(izabraniGradovi));
        }
        if (!izabraneDelatnosti.isEmpty()) {
            ns.setDelatnosti((ArrayList<String>) stringToList(izabraneDelatnosti));
        }
        if (!izabranaInteresovanja.isEmpty()) {
            ns.setInteresovanja((ArrayList<String>) stringToList(izabranaInteresovanja));
        }
        if (!izabraneProfVestine.isEmpty()) {
            ns.setProfVestine((ArrayList<String>) stringToList(izabraneProfVestine));
        }

        rezultatPretrage = ns.Pretrazi();
        return rezultatPretrage;
    }

}

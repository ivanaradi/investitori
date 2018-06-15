/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import model.Oglas;
import model.OglasDAO;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@ViewScoped
public class DohvatiOglase implements Serializable{
    //private ArrayList<Oglas> aktuelniOglasi = OglasDAO.dohvatiAktuelneOglase();
    private ArrayList<Oglas> aktuelniOglasi;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    
    public ArrayList<Oglas> getAktuelniOglasi() {
        return aktuelniOglasi;
    }

    public void setAktuelniOglasi(ArrayList<Oglas> aktuelniOglasi) {
        this.aktuelniOglasi = aktuelniOglasi;
    }

    public ArrayList<Oglas> getPetOglasa() {
        return petOglasa;
    }

    public void setPetOglasa(ArrayList<Oglas> petOglasa) {
        this.petOglasa = petOglasa;
    }
    
    
    private ArrayList<Oglas> petOglasa = OglasDAO.dohvatiPetOglasa();
    private long vreme = new Date().getTime();


    public long getVreme() {
        return vreme;
    }

    public void setVreme(long vreme) {
        this.vreme = vreme;
    }
    
    public String dateToString(Date date) {
        String datum = sdf.format(date);
        return datum;
    }
    
    /*
    metoda racuna koliko dana je ostalo do isteka oglasa
    */
    public long izracunajVreme(Date datumIsteka){
        long datum = datumIsteka.getTime();
        long preostaloVreme = datum-vreme;
        long toDays = TimeUnit.MILLISECONDS.toDays(preostaloVreme);
        return toDays;
    }
    /*
    za oglase ciji tekstovi su duzi od 200 karaktera metoda pravi preview i na stranicama gde se oglasi izlistavaju pokazuje se ceo oglas ako je kraci od 200 karaktera i preview ako je duzi
    */
    public String napraviPreview(String tekst){
        if(tekst.length()>200){
            String preview = tekst.substring(0, 200);
            preview+="...";
            return preview;
        }
        else {
            return tekst;
        }
    }
    public void dohvatiOglase(){
        aktuelniOglasi = OglasDAO.dohvatiAktuelneOglase();
    }
    
}

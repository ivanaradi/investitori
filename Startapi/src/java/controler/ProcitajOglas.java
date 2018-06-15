/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Oglas;
import model.OglasDAO;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@ViewScoped
public class ProcitajOglas  implements Serializable {
    private String naslov = "";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String datum = "";
    private Oglas  oglas= new Oglas();

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas oglas) {
        this.oglas = oglas;
    }

   

    private java.sql.Timestamp sqlDate(java.util.Date datum) {
        return new java.sql.Timestamp(datum.getTime());
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String dateToString(Date date) {
        datum = sdf.format(date);
        return datum;
    }

    public String procitajOglas() {
        if(naslov.isEmpty() && datum.isEmpty()){
            return "/Oglasi";
        }

        oglas = OglasDAO.dohvatiOglas(naslov, datum);
        return null;

    }
}


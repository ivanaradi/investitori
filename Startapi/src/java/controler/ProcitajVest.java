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
import model.Vest;
import model.VestiDAO;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@ViewScoped
public class ProcitajVest implements Serializable {

    private String naziv = "";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String datum = "";
    private Vest vest = new Vest();

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Vest getVest() {
        return vest;
    }

    public void setVest(Vest vest) {
        this.vest = vest;
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

    public void procitajVest() {

        vest = VestiDAO.dohvatiVestPoNazivuIDatumu(naziv, datum);

    }
}

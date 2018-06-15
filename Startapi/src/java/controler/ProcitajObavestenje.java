/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Obavestenje;
import model.ObavestenjeDAO;

/**
 *
 * @author Korisnik
 */

@ManagedBean
@RequestScoped
public class ProcitajObavestenje implements Serializable{
    private String naslov = "";
    private String datum = "";
    
    
    private Obavestenje obavestenje = new Obavestenje();

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Obavestenje getObavestenje() {
        return obavestenje;
    }

    public void setObavestenje(Obavestenje obavestenje) {
        this.obavestenje = obavestenje;
    }
    
    
    public void procitajObavestenje(){
        obavestenje = ObavestenjeDAO.dohvatiObavestenje(naslov, datum);
    }
}

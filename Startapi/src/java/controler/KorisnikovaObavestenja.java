/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Obavestenje;
import model.ObavestenjeDAO;
import org.hibernate.Session;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@ViewScoped
public class KorisnikovaObavestenja {
    private ArrayList<Obavestenje> obavestenja;
    public void dohvatiObavestenja(){
                obavestenja = ObavestenjeDAO.dohvatiObavestenja();
        
    }

    public ArrayList<Obavestenje> getObavestenja() {
        return obavestenja;
    }

    public void setObavestenja(ArrayList<Obavestenje> obavestenja) {
        this.obavestenja = obavestenja;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Interesovanje;
import model.InteresovanjeDAO;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@SessionScoped
public class DohvatiInteresovanja implements Serializable {
    private ArrayList<Interesovanje> svaInteresovanja = InteresovanjeDAO.dohvatiSvaInteresovanja();

    public ArrayList<Interesovanje> getSvaInteresovanja() {
        return svaInteresovanja;
    }

    public void setSvaInteresovanja(ArrayList<Interesovanje> svaInteresovanja) {
        this.svaInteresovanja = svaInteresovanja;
    }
    
    
    
}

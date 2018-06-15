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
import model.Profesionalnevestine;
import model.ProfesionalnevestineDAO;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@SessionScoped
public class DohvatiProfesionalneVestine  implements Serializable{
    
    
     
    private ArrayList<Profesionalnevestine> sveVestine = ProfesionalnevestineDAO.dohvatiSveVestine();
  

    public ArrayList<Profesionalnevestine> getSveVestine() {
        return sveVestine;
    }

    public void setSveVestine(ArrayList<Profesionalnevestine> sveVestine) {
        this.sveVestine = sveVestine;
    }
    
    
    
    
}

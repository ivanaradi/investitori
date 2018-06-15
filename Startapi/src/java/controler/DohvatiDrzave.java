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
import model.Drzava;
import model.DrzavaDAO;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@SessionScoped
public class DohvatiDrzave implements Serializable {
    private ArrayList<Drzava> sveDrzave = DrzavaDAO.dohvatiDrzave();

    public ArrayList<Drzava> getSveDrzave() {
        return sveDrzave;
    }

    public void setSveDrzave(ArrayList<Drzava> sveDrzave) {
        this.sveDrzave = sveDrzave;
    }
    
    
    
}

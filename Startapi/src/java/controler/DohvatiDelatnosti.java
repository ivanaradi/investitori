/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Delatnost;
import model.DelatnostDAO;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@RequestScoped
public class DohvatiDelatnosti implements Serializable {

    private ArrayList<Delatnost> sveDelatnosti = DelatnostDAO.dohvatiDelatnosti();

    public ArrayList<Delatnost> getSveDelatnosti() {
        return sveDelatnosti;
    }

    public void setSveDelatnosti(ArrayList<Delatnost> sveDelatnosti) {
        this.sveDelatnosti = sveDelatnosti;
    }

}

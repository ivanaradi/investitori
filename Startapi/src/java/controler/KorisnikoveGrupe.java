/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Grupa;
import model.GrupaDAO;
import model.Korisnik;

/**
 *
 * @author Korisnik
 */
@ManagedBean (name = "korisnikovegr", eager = true)
@RequestScoped
public class KorisnikoveGrupe implements Serializable{
    
  //  private Korisnik k;
   
    private ArrayList<Grupa> korisnikoveGrupe;
  
    public ArrayList<Grupa> getKorisnikoveGrupe() {
        return korisnikoveGrupe;
    }

    public void setKorisnikoveGrupe(ArrayList<Grupa> korisnikoveGrupe) {
        this.korisnikoveGrupe = korisnikoveGrupe;
    }

    
    
    @PostConstruct
    public void init(){
//        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        k = (Korisnik) user.getAttribute("ulogovaniKorisnik");
        korisnikoveGrupe = GrupaDAO.dohvatiKorisnikoveGrupe();
        
    }
    
}

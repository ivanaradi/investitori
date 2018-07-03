/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Korisnik;
import model.Vest;
import model.Vidljivost;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@ViewScoped
public class KorisnikoveVesti implements Serializable{
    private ArrayList<Vest> vesti;
    private boolean arhiviraneVesti = false;

    public ArrayList<Vest> getVesti() {
        return vesti;
    }

    public void setVesti(ArrayList<Vest> vesti) {
        this.vesti = vesti;
    }

    public boolean isArhiviraneVesti() {
        return arhiviraneVesti;
    }

    public void setArhiviraneVesti(boolean arhiviraneVesti) {
        this.arhiviraneVesti = arhiviraneVesti;
    }
    
    
    /*
    metoda dohvata sve vesti ulogovanog korisnika
    korisnik moze da izabere da vidi i arhivirane vesti
    */        
            
     public ArrayList<Vest> dohvatiVesti(){
        Transaction transaction = null;
        Session ses = HibernateUtil.createSessionFactory().openSession();
         String upit = "";
        if (!arhiviraneVesti){
            upit = "select v from Vest v where v.vidljivost <> " + Vidljivost.ADMIN.getSifra() + " and";
        }
        if(arhiviraneVesti){
            upit = "select v from Vest v where";
        }
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Korisnik kor;
        if (user.getAttribute("ulogovaniKorisnik") != null) {
            kor = (Korisnik) user.getAttribute("ulogovaniKorisnik");
            upit+= " v.korisnikId.id = " + kor.getId() + " order by v.vremeKreiranja DESC";
         
        }
        try {
            Query query = ses.createQuery(upit);
           
            if(!query.list().isEmpty()){
                  vesti = (ArrayList<Vest>) query.list();
                
            }
            return vesti;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
            return null;
        } finally {
            if (ses != null) {
                try {
                    ses.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }

        }
        
        
    }
    
    
    
}

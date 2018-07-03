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
 * @author Djidjo
 */
@ManagedBean
@ViewScoped
public class DohvatiVesti implements Serializable{
    private ArrayList<Vest> vesti;

    public ArrayList<Vest> getVesti() {
        return vesti;
    }

    public void setVesti(ArrayList<Vest> vesti) {
        this.vesti = vesti;
    }

  
      public void dohvatiDesetNajnovijihVesti(){
        Transaction transaction = null;
        Session ses = HibernateUtil.createSessionFactory().openSession();
        String upit = "select v from Vest v left join v.korisnikCollection kk where (v.vidljivost in (" + Vidljivost.SVI.getSifra();
       
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (user.getAttribute("ulogovaniKorisnik") == null) {
            upit += "))";
        } else {
            Korisnik kor = (Korisnik) user.getAttribute("ulogovaniKorisnik");

            String tip = (String) user.getAttribute("tip");
            switch (tip) {
                case ("startap"):
                    upit += ", " + Vidljivost.STARTAPI.getSifra();
                    break;
                case ("investitor"):
                    upit += ", " + Vidljivost.INVESTITORI.getSifra();
                    break;
            }
            upit += ") or " + kor.getId() + " = kk.id) or v.korisnikId =" + kor.getId() +"  order by v.vremeKreiranja DESC";
        }
        try {
            Query query = ses.createQuery(upit);//.setMaxResults(10);
            if(!query.list().isEmpty()){
                  vesti = (ArrayList<Vest>) query.list();
                
            } else{
                vesti = new ArrayList<>();
            }
          //  return vesti;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
        //    return null;
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

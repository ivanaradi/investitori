/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controler.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Korisnik
 */
public class ObavestenjeDAO {
    public static ArrayList<Obavestenje> dohvatiObavestenja(){
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        String upit = "select o from Obavestenje o left join o.korisnikCollection kk where (o.vidljivost in (" + Vidljivost.SVI.getSifra();
       
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (user.getAttribute("ulogovaniKorisnik") == null) {
            upit += "))";
        } else {
            Korisnik kor = (Korisnik) user.getAttribute("ulogovaniKorisnik");
            upit += ", " + Vidljivost.KORISNICI.getSifra();

            String tip = (String) user.getAttribute("tip");
            if(tip.equals("startap")){
                upit += ", " + Vidljivost.STARTAPI.getSifra();
            }
            upit += ") or " + kor.getId() + " = kk.id or o.investitorId =" + kor.getId() +")";
            
        }
        try {
            upit+= " and o.tipObavestenjaValjda='platforma' order by o.datumIVremeKreiranja DESC";
            Query q = session.createQuery(upit);
           
             ArrayList<Obavestenje> obavestenja = (ArrayList<Obavestenje>) q.list();
            return obavestenja;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
            return null;
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }
     
    }
    
    
    public static Obavestenje dohvatiObavestenje(String naslov, String datum){
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        Obavestenje o = null;
        try {
            Query upit = session.createQuery("SELECT o FROM Obavestenje o WHERE o.naslov = :naslov and o.datumIVremeKreiranja = :datumIVremeKreiranja");
            upit.setString("naslov", naslov);
            upit.setString("datumIVremeKreiranja", datum);
            if(!upit.list().isEmpty()){
                  o =  (Obavestenje) upit.list().get(0);
            }
          

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
            return null;
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }

        }
        return o;
        
    }
    
    
    
}

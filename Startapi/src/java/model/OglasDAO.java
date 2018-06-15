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
public class OglasDAO {
    
    public static ArrayList<Oglas> dohvatiAktuelneOglase(){
        ArrayList<Oglas> oglasi;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        String upit = "select o from Oglas o left join o.korisnikCollection kk where (o.vidljivost in (" + Vidljivost.SVI.getSifra();
       
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
            upit+= " and o.datumIVremeIsteka >= :danas order by o.datumIVremeIsteka ASC";
            Query q = session.createQuery(upit);
            q.setDate("danas", new Date());
            oglasi = (ArrayList<Oglas>) q.list();
            return oglasi;
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
    
     public static ArrayList<Oglas> dohvatiPetOglasa(){
        ArrayList<Oglas> oglasi;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        String upit = "select o from Oglas o left join o.korisnikCollection kk where (o.vidljivost in (" + Vidljivost.SVI.getSifra();
       
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (user.getAttribute("ulogovaniKorisnik") == null) {
            upit += "))";
        } else {
            Korisnik kor = (Korisnik) user.getAttribute("ulogovaniKorisnik");
            upit += ", " + Vidljivost.KORISNICI.getSifra();

            String tip = (String) user.getAttribute("tip");
            switch (tip) {
                case ("startap"):
                    upit += ", " + Vidljivost.STARTAPI.getSifra();
                    break;
                case ("investitor"):
                    break;
            }
            upit += ") or " + kor.getId() + " = kk.id)";
            
        }
        try {
             upit+= " and o.datumIVremeIsteka >= :danas order by o.datumIVremeIsteka ASC";
            Query q = session.createQuery(upit).setMaxResults(5);
            q.setDate("danas", new Date());
            oglasi = (ArrayList<Oglas>) q.list();
            return oglasi;
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
     
     public static Oglas dohvatiOglas(String naslov, String datum){
       Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        Oglas oglas = null;
        try {
            Query upit = session.createQuery("SELECT o FROM Oglas o WHERE o.naslov = :naslov and o.datumIVremePostavljanja = :datumIVremePostavljanja");
            upit.setString("naslov", naslov);
            upit.setString("datumIVremePostavljanja", datum);
            if(!upit.list().isEmpty()){
                  oglas = (Oglas) upit.list().get(0);
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
        return oglas;
     }
    
}

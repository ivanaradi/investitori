/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controler.HibernateUtil;
import java.util.ArrayList;
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
public class InteresovanjeDAO {
     public static ArrayList<Interesovanje> dohvatiSvaInteresovanja() {
        ArrayList<Interesovanje> svaInteresovanja;
        Transaction t = null;
        Session ses = HibernateUtil.createSessionFactory().openSession();
        try {
            Query query = ses.getNamedQuery("Interesovanje.findAll");
            svaInteresovanja = (ArrayList<Interesovanje>) query.list();
            return svaInteresovanja;
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
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

    public static ArrayList<Interesovanje> dohvatiKorisnikovaInteresovanja() {
        ArrayList<Interesovanje> korisnikovaInteresovanja;
        Transaction t = null;
        Korisnik kor = null;
        Session ses = HibernateUtil.createSessionFactory().openSession();
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (user.getAttribute("ulogovaniKorisnik") != null) {
            kor = (Korisnik) user.getAttribute("ulogovaniKorisnik");
        }
        try {
            Query query = ses.getNamedQuery("Select i from Interesovanje i, left join i.korisnikCollection kk where kk.korisnikId.id == :kor");
            query.setInteger("kor", kor.getId());
            korisnikovaInteresovanja = (ArrayList<Interesovanje>) query.list();
            return korisnikovaInteresovanja;
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
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

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
public class ProfesionalnevestineDAO {

    public static ArrayList<Profesionalnevestine> dohvatiSveVestine() {
        ArrayList<Profesionalnevestine> sveVestine;
        Transaction t = null;
        Session ses = HibernateUtil.createSessionFactory().openSession();
        try {
            Query query = ses.getNamedQuery("Profesionalnevestine.findAll");
            sveVestine = (ArrayList<Profesionalnevestine>) query.list();
            return sveVestine;
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

    public static ArrayList<Profesionalnevestine> dohvatiKorisnikoveVestine() {
        ArrayList<Profesionalnevestine> korisnikoveVestine;
        Transaction t = null;
        Korisnik kor = null;
        Session ses = HibernateUtil.createSessionFactory().openSession();
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (user.getAttribute("ulogovaniKorisnik") != null) {
            kor = (Korisnik) user.getAttribute("ulogovaniKorisnik");
        }
        try {
            Query query = ses.getNamedQuery("Select p from Profesionalnevestine p, left join p.korisnikCollection kk where kk.korisnikId.id == :kor");
            query.setInteger("kor", kor.getId());
            korisnikoveVestine = (ArrayList<Profesionalnevestine>) query.list();
            return korisnikoveVestine;
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

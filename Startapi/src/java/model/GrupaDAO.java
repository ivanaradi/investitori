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
public class GrupaDAO {

    public static ArrayList<Grupa> dohvatiKorisnikoveGrupe() {
        ArrayList<Grupa> korisnikoveGrupe;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        String upit = "select distinct g from Grupa g where g.korisnikId = :id";
        Korisnik kor = null;
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (user.getAttribute("ulogovaniKorisnik") != null) {

            kor = (Korisnik) user.getAttribute("ulogovaniKorisnik");

        }
        try {
            HibernateUtil.createSessionFactory().getCache().evictAllRegions();
            Query q = session.createQuery(upit);
            q.setInteger("id", kor.getId());

            korisnikoveGrupe = (ArrayList<Grupa>) q.list();

            session.flush();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
            return null;
        } finally {
            if (session != null) {
                try {
                    session.flush();
                    session.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }
        return korisnikoveGrupe;
    }

    public static Grupa dohvatiGrupuPoID(int id) {
        Grupa grupa = new Grupa();
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        String upit = "select g from Grupa g where g.id = :id";

        try {
            Query q = session.createQuery(upit);
            q.setInteger("id", id);
            grupa = (Grupa) q.list().get(0);
            session.flush();
            return grupa;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
            return null;
        } finally {
            if (session != null) {
                try {
                    session.flush();
                    session.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }

    }

    public static ArrayList<Grupa> dohvatiGrupeGdeKorisnikClan(Korisnik kor) {
        ArrayList<Grupa> korisnikoveGrupe = new ArrayList<>();
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        String upit = "select new Grupa(g.id, g.naziv, g.opis) from Grupa g join g.korisnikCollection kk where kk.id = :id";
        //Korisnik kor = null;
//        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        if (user.getAttribute("ulogovaniKorisnik") != null) {
//
//            kor = (Korisnik) user.getAttribute("ulogovaniKorisnik");
//
//        }
        try {
            Query q = session.createQuery(upit);
            q.setInteger("id", kor.getId());
           
            korisnikoveGrupe = (ArrayList<Grupa>) q.list();
            return korisnikoveGrupe;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
            return null;
        } finally {
            if (session != null) {
                try {
                    session.flush();
                    session.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }
    }

    public static ArrayList<Grupa> dohvatiKorisnikoveGrupeSaClanovima(Korisnik kor) {
        ArrayList<Grupa> korisnikoveGrupe;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        String upit = "select distinct g from Grupa g left join fetch g.korisnikCollection kk where g.korisnikId = :id";

        try {
            Query q = session.createQuery(upit);
            q.setInteger("id", kor.getId());

            korisnikoveGrupe = (ArrayList<Grupa>) q.list();
            session.flush();
            return korisnikoveGrupe;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
            return null;
        } finally {
            if (session != null) {
                try {
                    session.flush();
                    session.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }
    }

}

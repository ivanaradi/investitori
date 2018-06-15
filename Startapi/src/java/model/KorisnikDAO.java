/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controler.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Korisnik
 */
public class KorisnikDAO {
    public static ArrayList<Korisnik> dohvatiKorisnikePoNazivu(String naziv){
        ArrayList<Korisnik> korisniciPoNazivu = null;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.getNamedQuery("Korisnik.findByPunNaziv");
            q.setString("punNaziv", naziv);
            korisniciPoNazivu = (ArrayList<Korisnik>) q.list();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }
        return korisniciPoNazivu;
    }
    
     public static Korisnik dohvatiKorisnikaPoId(int id){
        Korisnik k = null;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.getNamedQuery("Korisnik.findById");
            q.setInteger("id", id);
            k = (Korisnik) q.list().get(0);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }
        return k;
    }
    
    public static List<String> SvakorisnickaImena() {
        Transaction tx = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        List<String> investitori = new ArrayList();
        try {
            tx = session.beginTransaction();
            //Criteria c =  session.createCriteria(Investitor.class).add(Restrictions.eq("korisnickoIme", korisnickoIme));     

            String hql = "select korisnickoIme from Korisnik";

            Query query = session.createQuery(hql);
            List<String> results = query.list();
            if (results != null) {
                investitori = results;
            }

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }
       
            return investitori;
       

    }
    
    public static ArrayList<String> dohvatiEmailAdreseSvihKorisnika(){
        ArrayList<String> sviMejlovi = new ArrayList();
        Transaction tx = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT k.mail from Korisnik k");
            sviMejlovi = (ArrayList<String>) query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }
        return sviMejlovi;
   }
    public static ArrayList<String> dohvatiEmailAdreseStartapova(){
        ArrayList<String> mejloviStartap = new ArrayList();
        Transaction tx = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT s.mail from Startap s");
            mejloviStartap = (ArrayList<String>) query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }
            return mejloviStartap;
    }
  public static ArrayList<String> dohvatiEmailAdreseInvestitora(){
        ArrayList<String> mejloviInvestitori = new ArrayList();
        Transaction tx = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT i.mail from Investitor i");
            mejloviInvestitori = (ArrayList<String>) query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }
            return mejloviInvestitori;
    }
  
    
    
}

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
public class DelatnostDAO {
    public static ArrayList<Delatnost> dohvatiDelatnosti(){
        ArrayList<Delatnost> sveDelatnosti = null;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.getNamedQuery("Delatnost.findAll");
            sveDelatnosti = (ArrayList<Delatnost>) q.list();
            
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
        return sveDelatnosti;
    }
     public static List<String> NaziviSvihDelatnoosti() {
        Transaction tx = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        List<String> delatnosti = new ArrayList();
        try {
            tx = session.beginTransaction();
            String hql = "select naziv from Delatnost";

            Query query = session.createQuery(hql);
            List<String> results = query.list();
            if (results != null) {
                delatnosti = results;
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
       
            return delatnosti;
       

    }
    public static List<Delatnost> sveDelatnosti() {
        Transaction tx = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        List<Delatnost> delatnosti = new ArrayList();
        try {
            tx = session.beginTransaction();
            Query q = session.getNamedQuery("Delatnost.findAll");
            delatnosti = (List<Delatnost>)q.list();
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
       
            return delatnosti;
       

    }
    
}

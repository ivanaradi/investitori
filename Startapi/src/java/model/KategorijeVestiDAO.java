/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controler.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Korisnik
 */

public class KategorijeVestiDAO {

    public static ArrayList<Kategorijavesti> dohvatiKategorije() {

        ArrayList<Kategorijavesti> sveKategorije = null;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.getNamedQuery("Kategorijavesti.findAll");
            sveKategorije = (ArrayList<Kategorijavesti>) q.list();

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
        return sveKategorije;
    }

    public static ArrayList<Kategorijavesti> dohvatiVazeceKategorije() {

        ArrayList<Kategorijavesti> vazeceKategorije;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Date datum = new Date();
            Query q = session.createQuery("SELECT k FROM Kategorijavesti k WHERE :datum >= k.datumPocetka and :datum<= k.datumKraja");
            q.setDate("datum", datum);
            vazeceKategorije = (ArrayList<Kategorijavesti>) q.list();
            return vazeceKategorije;

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
}

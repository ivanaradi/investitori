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
public class VestiDAO {

   

    public static List<Integer> listaIdVestiVidljivihKorisniku(Korisnik kor) {
        List<Integer> lista = new ArrayList<>();
        for (Vest v : kor.getVestCollection()) {
            lista.add(v.getId());
        }
        return lista;
    }

    public static Vest dohvatiVestPoNazivuIDatumu(String naziv, String datum) {
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        Vest vest = null;
        try {
            Query upit = session.createQuery("SELECT v FROM Vest v WHERE v.naziv = :naziv and v.vremeKreiranja = :vremeKreiranja");
            upit.setString("naziv", naziv);
            upit.setString("vremeKreiranja", datum);
            if(!upit.list().isEmpty()){
                  vest = (Vest) upit.list().get(0);
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
        return vest;
    }
    
    public static Vest dohvatiVestPoIdu(int id) {
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        Vest vest = null;
        try {
            Query upit = session.getNamedQuery("Vest.findById");
            upit.setInteger("id", id);
            if(!upit.list().isEmpty()){
                  vest = (Vest) upit.list().get(0);
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
        return vest;
    }
}

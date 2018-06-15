/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controler.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Korisnik
 */
public class OpstinaDAO {
    public static ArrayList<Opstina> dohvatiOpstinePoGradu(Grad grad){
        ArrayList<Opstina> opstinePoGradu = null;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.createQuery("select o from Opstina o where  gradid= " + grad.getId());
            opstinePoGradu = (ArrayList<Opstina>) q.list();
            
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
        return opstinePoGradu;
    }
    public static ArrayList<Integer> dohvatiIdOpstinaPoIdGrada(int idGrada){
        ArrayList<Integer> idOpstinePoIdGrada = null;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.createQuery("select o.id from Opstina o where  gradid= " + idGrada);
            idOpstinePoIdGrada = (ArrayList<Integer>) q.list();
            
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
        return idOpstinePoIdGrada;
    }
    
     public static Opstina dohvatiOpstinuPoNazivu(String naziv){
     Transaction tx = null;
     Session session = HibernateUtil.createSessionFactory().openSession();
     Opstina opstina = null;
     List<Opstina> opstine;
     try{
        tx = session.beginTransaction();
        Criteria q =  session.createCriteria(Opstina.class).add(Restrictions.eq("naziv", naziv));        
        opstine = q.list();
        opstina = opstine.get(0);
        tx.commit();
      
    }
    catch(HibernateException e){
    if(tx!=null){
        tx.rollback();
    }
        System.out.println(e);    
    }
    finally{
       if (session != null) {
            try {
                session.close();
            } catch (HibernateException ignored) {
                System.out.println("Couldn't close Session "+ ignored);
            }
        }        

      
  }
     return opstina;
        
}
}

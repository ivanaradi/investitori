/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controler.HibernateUtil;
import java.util.ArrayList;
import java.util.Collection;
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
public class GradDAO {
     public static ArrayList<Grad> dohvatiGradovePoDrzavi(Drzava drzava){
        ArrayList<Grad> gradoviPoDrzavi = null;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.createQuery("select g from Grad g where  drzavaId= " + drzava.getId());
            gradoviPoDrzavi = (ArrayList<Grad>) q.list();
            
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
        return gradoviPoDrzavi;
    }
     public static ArrayList<Integer> dohvatiIdGradovaPoIdDrzave(int id){
        ArrayList<Integer> gradoviPoDrzavi = null;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.createQuery("select g.id from Grad g where drzavaId= " + id);
            gradoviPoDrzavi = (ArrayList<Integer>) q.list();
            
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
        return gradoviPoDrzavi;
    }
     public static ArrayList<Grad> dohvatiGradovePoIdDrzave(int id){
        ArrayList<Grad> gradoviPoDrzavi = null;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.createQuery("select g from Grad g where drzavaId= " + id);
            gradoviPoDrzavi = (ArrayList<Grad>) q.list();
            
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
        return gradoviPoDrzavi;
    }
     
     public static ArrayList<Grad> dohvatiGradovePoNazivuDrzave(String naziv){
        ArrayList<Grad> gradoviPoDrzavi = null;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.createQuery("select g from Grad g where drzavAid.naziv= :naziv");
            q.setString("naziv", naziv);
            gradoviPoDrzavi = (ArrayList<Grad>) q.list();
            
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
        return gradoviPoDrzavi;
    }
     public static Grad dohvatiGradPoNazivu(String naziv){
     Transaction tx = null;
     Session session = HibernateUtil.createSessionFactory().openSession();
     Grad grad = new Grad();
     List<Grad> gradovi;
     try{
        tx = session.beginTransaction();
        Criteria q =  session.createCriteria(Grad.class).add(Restrictions.eq("naziv", naziv));        
        gradovi = q.list();
        grad = gradovi.get(0);
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
     return grad;
        
}
    
//     public static Collection<Grad> dohvatiGradovePoNazivuDrzave(String naziv){
//     Transaction tx = null;
//     Session session = HibernateUtil.createSessionFactory().openSession();
//     Drzava drzava = null;
//     List<Drzava> drzave;
//     Collection<Grad> gradovi = new ArrayList();
//     try{
//        tx = session.beginTransaction();
//        Criteria q =  session.createCriteria(Drzava.class).add(Restrictions.eq("naziv", naziv));        
//        drzave = q.list();
//        tx.commit();
//         try{
//        drzava = drzave.get(0);
//        gradovi = drzava.getGradCollection();
//        }catch(Exception e)
//        { 
//           gradovi = new ArrayList(); 
//        }
//      
//    }
//    catch(HibernateException e){
//    if(tx!=null){
//        tx.rollback();
//    }
//        System.out.println(e);    
//    }
//    finally{
//       if (session != null) {
//            try {
//                session.close();
//            } catch (HibernateException ignored) {
//                System.out.println("Couldn't close Session "+ ignored);
//            }
//        }        
//
//      
//  }
//     return gradovi;
//        
//}
//     
     
  
    
  
}

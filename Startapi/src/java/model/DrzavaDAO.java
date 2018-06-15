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
public class DrzavaDAO {
    public static ArrayList<Drzava> dohvatiDrzave(){
        ArrayList<Drzava> sveDrzave = null;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.getNamedQuery("Drzava.findAll");
            sveDrzave = (ArrayList<Drzava>) q.list();
            
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
        return sveDrzave;
    }
     public static ArrayList<Integer> dohvatiIdDrzava(){
        ArrayList<Drzava> sveDrzave = dohvatiDrzave();
        ArrayList<Integer> drz = new ArrayList<>();; 
        for(Drzava d: sveDrzave){
            drz.add(d.getId());
        }
        return drz;
    }
     
 
   public static List<Drzava> sveDrzave(){
       
     System.out.println("sveee drzaveee");
     Transaction tx = null;
     Session session = HibernateUtil.createSessionFactory().openSession();
     List<Drzava> drzave = new ArrayList();
     try{System.out.println("try");
        tx = session.beginTransaction();
        Query q = session.getNamedQuery("Drzava.findAll");
        drzave = (List<Drzava>)q.list();
        tx.commit();
      
    }
    catch(HibernateException e){
        System.out.println("catch");
    if(tx!=null){
        tx.rollback();
    }
        System.out.println(e);    
    }
    finally{
         System.out.println("finaly");
       if (session != null) {
            try {
                session.close();
            } catch (HibernateException ignored) {
                System.out.println("Couldn't close Session "+ ignored);
            }
        }        
    }
      
     System.out.println("kraaaaajaaa ");
     System.out.println("drzave " + drzave); 
     
   for(Drzava d : drzave){
       System.out.println(d.getNaziv());
   }  
   return drzave;     
   }      
 
  public static Drzava dohvatiDrzavuPoNazivu(String naziv){
      
     Transaction tx = null;
     Session session = HibernateUtil.createSessionFactory().openSession();
     Drzava drzava = new Drzava();
     List<Drzava> drzave;
     
     try{
         
         
        tx = session.beginTransaction();
        Criteria q =  session.createCriteria(Drzava.class).add(Restrictions.eq("naziv", naziv));
        
        drzave = q.list();
        drzava = drzave.get(0);
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
            }
        }        

      
  }
     return drzava;
        
}
    
}

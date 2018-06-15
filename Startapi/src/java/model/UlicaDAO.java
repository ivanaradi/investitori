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
public class UlicaDAO {
    
public static ArrayList<Ulica> dohvatiUlicepoOpstini(Opstina opstina){
        ArrayList<Ulica> ulicePoOpstini = null;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.createQuery("select u from Ulica u where opstinaIdopstina = " + opstina.getIdopstina());
            ulicePoOpstini = (ArrayList<Ulica>) q.list();
            
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
        return ulicePoOpstini;
    }
    public static ArrayList<Ulica> dohvatiUlicepoGradu(Grad grad){
        ArrayList<Ulica> ulicePoGradu = null;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.createQuery("select u from Ulica u where gradId = " + grad.getId());
            ulicePoGradu = (ArrayList<Ulica>) q.list();
            
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
        return ulicePoGradu;
    }
    public static ArrayList<Integer> dohvatiIdUlicapoIdGrada(int idGrada){
        ArrayList<Integer> ulicePoGradu = null;
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.createQuery("select u.id from Ulica u where gradId = " + idGrada);
            ulicePoGradu = (ArrayList<Integer>) q.list();
            
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
        return ulicePoGradu;
    }
      
    public static Ulica dohvatiUlicuPoNazivuIOpstini(String naziv , int opstinaId){
        System.out.println("    sasasadsddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddaaaaaaaaaaaaaaaaaaaaaaa");
     Transaction tx = null;
     Session session = HibernateUtil.createSessionFactory().openSession();    
     Ulica ulica = null;
     
     List<Ulica> results;
     try{
        tx = session.beginTransaction();     
        
            String hql = "select u from Ulica u where naziv ='"+naziv+"' and opstina_idopstina ="+opstinaId+"";
            Query query = session.createQuery(hql);
            results = query.list();
            for(Ulica u : results){
                System.out.println(u.getNaziv());
            }
            
            tx.commit();
            try{
                ulica = results.get(0);
                
            }catch(Exception e){
                ulica = null;
            }
            
      
    }
    catch(HibernateException e){
    if(tx!=null){
        tx.rollback();
    }
     ulica = null;
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
    return ulica;    
    }
    
    public static List<String> dohvatiNazivePoImenuIOpstini( int opstinaId , String naziv){
        System.out.println("oooooooopstina id "+opstinaId);   
     Transaction tx = null;
     Session session = HibernateUtil.createSessionFactory().openSession();    
     
     List<String> results = new ArrayList<>();
     try{
        tx = session.beginTransaction();     
        
            String hql = "select naziv from Ulica where opstina_idopstina = "+opstinaId+" and naziv like '%"+naziv+"%'";
            Query query = session.createQuery(hql);
            results = query.list();
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
     
   return results;     
        
    }
        
    public static Ulica dohvatiUlicuPoIdu(int id){
     Transaction tx = null;
     Session session = HibernateUtil.createSessionFactory().openSession();
     Ulica ulica = new Ulica();
     try{
        tx = session.beginTransaction();
        ulica = (Ulica)session.get(Ulica.class,id);
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
     
   return ulica;     
        
    }
    
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Startap;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@ViewScoped
public class PogledajProfilStartap {
    private Startap startap = new Startap();
   
    public Startap getStartap() {
        return startap;
    }

    public void setStartap(Startap startap) {
        this.startap = startap;
    }
    
    
    
      public Startap dohvatiStartapPoId(){
         
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.createQuery("Select s from Startap s where s.id = :id");
            q.setInteger("id", startap.getId());          
            startap = (Startap) q.list().get(0);
            return startap;
                   
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

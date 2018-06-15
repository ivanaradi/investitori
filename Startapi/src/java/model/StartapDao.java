
package model;

import controler.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class StartapDao {
    
    public static Startap dohvatiStartapaPoIdu(int id){
     Transaction tx = null;
     Session session = HibernateUtil.createSessionFactory().openSession();
     Startap startap = new Startap();
     try{
        tx = session.beginTransaction();
        Criteria c =  session.createCriteria(Startap.class).add(Restrictions.eq("id",id));        
        startap = (Startap)c.uniqueResult();
         System.out.println("startappppp id "+startap.getId());
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
   return startap;     
        
    }
      
    
}

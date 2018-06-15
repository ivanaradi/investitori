/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Kategorijavesti;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


@ManagedBean
@RequestScoped
public class RegistracijaKategorijeVesti {
    
    private  Kategorijavesti kategorijaVesti = new Kategorijavesti();

    public Kategorijavesti getKategorijaVesti() {
        return kategorijaVesti;
    }

    public void setKategorijaVesti(Kategorijavesti kategorijaVesti) {
        this.kategorijaVesti = kategorijaVesti;
    }
     
      
    
     public String sacuvajKategoruijuVesti(){  
   
         
    Session session = HibernateUtil.createSessionFactory().openSession();
    Transaction tx = null;
  
    try{
        tx = session.beginTransaction();
        session.save(kategorijaVesti);
      
        tx.commit();
        return "/index?faces-redirect=true;";
    }
    catch(HibernateException e){
    if(tx!=null){
        tx.rollback();
    }
        System.out.println(e);  
        return null;
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
      
    }
    
}

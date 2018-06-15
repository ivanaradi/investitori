/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Investitor;
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
public class PogledajProfil implements Serializable{
    private Investitor investitor;
    private int id;
    private String punNaziv;

    public Investitor getInvestitor() {
        return investitor;
    }

    public void setInvestitor(Investitor investitor) {
        this.investitor = investitor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPunNaziv() {
        return punNaziv;
    }

    public void setPunNaziv(String punNaziv) {
        this.punNaziv = punNaziv;
    }
    
    public Investitor dohvatiInvestitoraPoId(){
        
        investitor = new Investitor();
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.getNamedQuery("Investitor.findById");
            q.setInteger("id", id);
            
            investitor =  (Investitor) q.list().get(0);
            
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
        return investitor;
    }
  
    
    
    
}

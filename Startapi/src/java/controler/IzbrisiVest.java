/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Korisnik;
import model.Vest;
import org.hibernate.Hibernate;
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
public class IzbrisiVest implements Serializable {

    public void izbrisiVest(Vest v) {

        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
//            HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//            Korisnik k = (Korisnik) sesija.getAttribute("ulogovaniKorisnik");
//            Hibernate.initialize(k.getVestCollection1());
//            
//            k.getVestCollection1().remove(v);
            v.getKorisnikCollection().clear();
            session.update(v);
           
            Query q = session.createQuery("select v from Vest v where v.id = :id");
            q.setInteger("id", v.getId());
            v = (Vest) q.list().get(0);
            session.delete(v);
            tr.commit();

        } catch (HibernateException e) {
            if (tr != null) {
                System.out.println(e);
                tr.rollback();
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
    }
}

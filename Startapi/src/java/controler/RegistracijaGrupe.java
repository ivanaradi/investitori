/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Grupa;
import model.Korisnik;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
@ViewScoped
public class RegistracijaGrupe {

    private Grupa grupa = new Grupa();

    public Grupa getGrupa() {
        return grupa;
    }

    public void setGrupa(Grupa grupa) {
        this.grupa = grupa;
    }

    public String sacuvajGrupu() {
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Korisnik korisnik;
        if (user.getAttribute("ulogovaniKorisnik") == null) {
           return "/index";
        } else {
        
            korisnik = (Korisnik) user.getAttribute("ulogovaniKorisnik");
            grupa.setKorisnikId(korisnik);
        }

        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(grupa);

            tx.commit();
            return "/user/dodajKorisnikeUGrupu"; 
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
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

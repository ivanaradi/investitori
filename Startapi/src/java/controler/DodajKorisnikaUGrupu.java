/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Grupa;
import model.GrupaDAO;
import model.Korisnik;
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
public class DodajKorisnikaUGrupu implements Serializable{

    private ArrayList<Grupa> grupe;
    private Grupa izabranaGrupa;

    public ArrayList<Grupa> getGrupe() {
        return grupe;
    }

    public void setGrupe(ArrayList<Grupa> grupe) {
        this.grupe = grupe;
    }

    public Grupa getIzabranaGrupa() {
        return izabranaGrupa;
    }

    public void setIzabranaGrupa(Grupa izabranaGrupa) {
        this.izabranaGrupa = izabranaGrupa;
    }

    @PostConstruct
    public void init() {
        grupe = GrupaDAO.dohvatiKorisnikoveGrupe();

    }
   
    public void dodajKorisnika(Korisnik k) {
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("select g from Grupa g left join g.korisnikCollection kk where g.id = :id");
            q.setInteger("id", izabranaGrupa.getId());
            izabranaGrupa = (Grupa) q.list().get(0);
            izabranaGrupa.getKorisnikCollection().add(k);
            session.update(izabranaGrupa);
            izabranaGrupa = new Grupa();
            transaction.commit();

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
    }

}

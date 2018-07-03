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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
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
public class NapustiGrupu implements Serializable {

    private ArrayList<Grupa> grupeGdeKorisnikClan;
    private String tip;
    private Korisnik k;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public ArrayList<Grupa> getGrupeGdeKorisnikClan() {
        return grupeGdeKorisnikClan;
    }

    public void setGrupeGdeKorisnikClan(ArrayList<Grupa> grupeGdeKorisnikClan) {
        this.grupeGdeKorisnikClan = grupeGdeKorisnikClan;
    }

    @PostConstruct
    public void init() {
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        k = (Korisnik) user.getAttribute("ulogovaniKorisnik");

        grupeGdeKorisnikClan = GrupaDAO.dohvatiGrupeGdeKorisnikClan(k);

        tip = (String) user.getAttribute("tip");
    }

    public void napustiGrupu(Grupa g) {
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Korisnik k = (Korisnik) user.getAttribute("ulogovaniKorisnik");
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("select g from Grupa g left join fetch g.korisnikCollection kk where g.id = :id");
            q.setInteger("id", g.getId());
            Grupa gr = (Grupa) q.list().get(0);
            gr.getKorisnikCollection().remove(k);
            session.update(gr);
            transaction.commit();
            grupeGdeKorisnikClan = GrupaDAO.dohvatiGrupeGdeKorisnikClan(k);
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

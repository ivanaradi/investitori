/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import model.Grupa;
import model.GrupaDAO;
import model.Korisnik;
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
public class IzbrisiGrupu implements Serializable {
    
    private Korisnik k;
    
    private ArrayList<Grupa> korisnikoveGrupe;
    
    public ArrayList<Grupa> getKorisnikoveGrupe() {
        return korisnikoveGrupe;
    }
    
    public void setKorisnikoveGrupe(ArrayList<Grupa> korisnikoveGrupe) {
        this.korisnikoveGrupe = korisnikoveGrupe;
    }
    
    @PostConstruct
    public void init() {
//        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        k = (Korisnik) user.getAttribute("ulogovaniKorisnik");
        //  korisnikoveGrupe = GrupaDAO.dohvatiKorisnikoveGrupeSaClanovima(k);
        korisnikoveGrupe = GrupaDAO.dohvatiKorisnikoveGrupe();
        
    }
    
    @Transactional
    public void izbrisiGrupu(Grupa g) {
        
        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        
        try {
            
            transaction = session.beginTransaction();
            
            Query q = session.createQuery("SELECT g FROM Grupa g where g.id = :id");
            q.setInteger("id", g.getId());
            
            Grupa izbrisi = (Grupa) q.list().get(0);
            Hibernate.initialize(izbrisi.getKorisnikCollection());
            Hibernate.initialize(izbrisi.getObavestenjeCollection());
            session.flush();
           
            session.delete(izbrisi);
            transaction.commit();
            HttpSession httpsesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            Korisnik kor = (Korisnik) httpsesija.getAttribute("ulogovaniKorisnik");
           
            q = session.createQuery("select distinct g from Grupa g where g.korisnikId = :id");
            q.setInteger("id", kor.getId());
            session.flush();
            korisnikoveGrupe = (ArrayList<Grupa>) q.list();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
            
        } finally {
            
            if (session != null) {
                try {
                    session.flush();
                    session.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }
    }
    
}

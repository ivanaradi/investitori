/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Kategorijavesti;
import model.KategorijeVestiDAO;
import model.Korisnik;
import model.Vest;
import model.Vidljivost;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Djidjo
 */
@ManagedBean
@ViewScoped
public class DohvatiVestiPoKategorijama implements Serializable{
    private ArrayList<Vest> vestiIzKategorije;
    private ArrayList<Kategorijavesti> vazeceKategorije = KategorijeVestiDAO.dohvatiVazeceKategorije();
    private String naslovKategorije = "";

    public ArrayList<Kategorijavesti> getVazeceKategorije() {
        return vazeceKategorije;
    }

    public void setVazeceKategorije(ArrayList<Kategorijavesti> vazeceKategorije) {
        this.vazeceKategorije = vazeceKategorije;
    }

    public ArrayList<Vest> getVestiIzKategorije() {
        return vestiIzKategorije;
    }

    public void setVestiIzKategorije(ArrayList<Vest> vestiIzKategorije) {
        this.vestiIzKategorije = vestiIzKategorije;
    }

    public String getNaslovKategorije() {
        return naslovKategorije;
    }

    public void setNaslovKategorije(String naslovKategorije) {
        this.naslovKategorije = naslovKategorije;
    }
    
    
    /*
    metoda dohvata sve vesti iz neke kategorije, poziva se na strani VestiIzKategorije
    */
    public void dohvatiVestiPoKategoriji(String naslov) {
        vestiIzKategorije = new ArrayList<>();
        Transaction transaction = null;
        Session ses = HibernateUtil.createSessionFactory().openSession();
        String upit = "select v from Vest v left join v.korisnikCollection kk where (v.vidljivost in (" + Vidljivost.SVI.getSifra();

        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (user.getAttribute("ulogovaniKorisnik") == null) {
            upit += "))";
        } else {
            Korisnik kor = (Korisnik) user.getAttribute("ulogovaniKorisnik");

            String tip = (String) user.getAttribute("tip");
            switch (tip) {
                case ("startap"):
                    upit += ", " + Vidljivost.STARTAPI.getSifra();
                    break;
                case ("investitor"):
                    upit += ", " + Vidljivost.INVESTITORI.getSifra();
                    break;
            }
            upit += ") or " + kor.getId() + " = kk.id)";
        }
        try {
            Query q = ses.createQuery(upit + " and v.kategorijaVestiId.naslov = :naslov order by v.vremeKreiranja DESC");
            q.setString("naslov", naslov);
            vestiIzKategorije = (ArrayList<Vest>) q.list();
          //  return vestiIzKategorije;

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
          //  return null;
        } finally {
            if (ses != null) {
                try {
                    ses.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }
        
    }
    /*
    dohvata 10 najnovijih vesti iz svake kategorije
    */
    
    public ArrayList<Vest> dohvatiNajnovijeVestiPoKategoriji(int idKat) {
        ArrayList<Vest> vesti = new ArrayList<>();
        Transaction transaction = null;
        Session ses = HibernateUtil.createSessionFactory().openSession();
        String upit = "select v from Vest v left join v.korisnikCollection kk where (v.vidljivost in (" + Vidljivost.SVI.getSifra();

        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (user.getAttribute("ulogovaniKorisnik") == null) {
            upit += "))";
        } else {
            Korisnik kor = (Korisnik) user.getAttribute("ulogovaniKorisnik");

            String tip = (String) user.getAttribute("tip");
            switch (tip) {
                case ("startap"):
                    upit += ", " + Vidljivost.STARTAPI.getSifra();
                    break;
                case ("investitor"):
                    upit += ", " + Vidljivost.INVESTITORI.getSifra();
                    break;
            }
            upit += ") or " + kor.getId() + " = kk.id)";
        }
        try {
            Query q = ses.createQuery(upit + " and v.kategorijaVestiId.id = " + idKat + " order by v.vremeKreiranja DESC").setMaxResults(10);
            vesti = (ArrayList<Vest>) q.list();
            return vesti;

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
        } finally {
            if (ses != null) {
                try {
                    ses.close();
                } catch (HibernateException ignored) {
                    System.out.println("Couldn't close Session " + ignored);
                }
            }
        }
        return vesti;
    }

}

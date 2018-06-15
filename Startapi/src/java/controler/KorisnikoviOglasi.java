/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Korisnik;
import model.Oglas;
import model.Vidljivost;
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
public class KorisnikoviOglasi implements Serializable {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ArrayList<Oglas> oglasi;
    private boolean arhiviraniIstekliOglasi = false;

    public ArrayList<Oglas> getOglasi() {
        return oglasi;
    }

    public void setOglasi(ArrayList<Oglas> oglasi) {
        this.oglasi = oglasi;
    }

    public boolean isArhiviraniIstekliOglasi() {
        return arhiviraniIstekliOglasi;
    }

    public void setArhiviraniIstekliOglasi(boolean arhiviraniIstekliOglasi) {
        this.arhiviraniIstekliOglasi = arhiviraniIstekliOglasi;
    }
    
    public String napraviPreview(String tekst){
        if(tekst.length()>200){
            String preview = tekst.substring(0, 200);
            preview+="...";
            return preview;
        }
        else {
            return tekst;
        }
    }
     public String dateToString(Date date) {
        String datum = sdf.format(date);
        return datum;
    }

    /*
    metoda dohvata sve oglase ulogovanog korisnika
    korisnik moze da izabere da li zeli da vidi arhivirane i istekle oglase
    
     */
    public ArrayList<Oglas> dohvatiOglase() {
        Transaction transaction = null;
        Session ses = HibernateUtil.createSessionFactory().openSession();
        String upit = "";
        if (!arhiviraniIstekliOglasi) {
            upit = "select o from Oglas o where o.vidljivost <> " + Vidljivost.ADMIN.getSifra() + " and";
        }
        if (arhiviraniIstekliOglasi) {
            upit = "select o from Oglas o where";
        }
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Korisnik kor;
        if (user.getAttribute("ulogovaniKorisnik") != null) {
            kor = (Korisnik) user.getAttribute("ulogovaniKorisnik");
            upit += " o.investitorId.id = " + kor.getId() + " order by o.datumIVremePostavljanja DESC";

        }
        try {
            Query query = ses.createQuery(upit);

            if (!query.list().isEmpty()) {
                oglasi = (ArrayList<Oglas>) query.list();

            }
            return oglasi;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
            return null;
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

}

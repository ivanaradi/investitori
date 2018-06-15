/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Investitor;
import model.Korisnik;
import model.Startap;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable {

    private Korisnik korisnik = null;
    private Investitor inv = null;
    private Startap st = null;
    private String username = "";
    private String password = "";

    private boolean ulogovan = false;

    public boolean isUlogovan() {
        return ulogovan;
    }

    public void setUlogovan(boolean ulogovan) {
        this.ulogovan = ulogovan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Investitor getInv() {
        return inv;
    }

    public void setInv(Investitor inv) {
        this.inv = inv;
    }

    public Startap getSt() {
        return st;
    }

    public void setSt(Startap st) {
        this.st = st;
    }

    /*
    metoda proverava da li postoji korisnik sa unetim korisnickim imenom ili mejlom, ako ne postoji vraca poruku da nisu uneti dobri parametri
    ako korisnik postoji proverava da li je uneta ispravna sifra, ukoliko nije izlazi ista poruka!
    ako su uneti podaci ispravni, metoda proverava da li je korisnik startap ili investitor, snima ceo objekat! u sesiju kao atribut "ulogovaniKorisnik" 
    i snima kao atribut "tip" "startap" ili "investitor"
     */
    public void login() {
        Transaction tx = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        HttpSession user = null;
        try {
            Query query = session.createQuery("Select k from Korisnik k where (korisnickoIme= :username or mail = :username) and lozinka = :password");
            query.setString("username", username);
            query.setString("password", password);

            if (query.list().isEmpty()) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Pogresno korisnicko ime ili sifra! Proverite unete podatke i pokusajte ponovo!"));
            } else {
                korisnik = (Korisnik) query.list().get(0);
                user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                query = session.getNamedQuery("Investitor.findById");
                query.setInteger("id", korisnik.getId());
                if (!query.list().isEmpty()) {
                    Investitor investitor = (Investitor) query.list().get(0);
                    user.setAttribute("ulogovaniKorisnik", investitor);
                    user.setAttribute("tip", "investitor");
                   
                } else {
                    query = session.getNamedQuery("Startap.findById");
                    query.setInteger("id", korisnik.getId());
                    if (!query.list().isEmpty()) {
                        Startap startap = (Startap) query.list().get(0);
                        user.setAttribute("ulogovaniKorisnik", startap);
                        user.setAttribute("tip", "startap");
                       

                    } else {
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Greska! Molimo Vas pokusajte ponovo"));
                    }

                }

            }

//         
        } catch (HibernateException e) {

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

    public String logout() {
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        user.invalidate();
        korisnik = null;

        return "/index?faces-redirect=true";
    }

    /**
     *
     * @return
     */
//    public boolean jeUlogovan() {
//        
//        return korisnik != null;
//        
//
//    }
    /*
    metoda proverava da li postoji snimljen ulogovani korisnik, vraca boolean koji odredjuje koji delovi strane ce biti renderovani
     */
    public boolean jeUlogovan() {
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return user.getAttribute("ulogovaniKorisnik") != null;
    }

}

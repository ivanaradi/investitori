package controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Anketa;
import model.Investitor;
import model.Korisnik;
import model.Pitanja;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
@ViewScoped
public class registrujAnketu implements Serializable {

    static final long serialVersionUID = -687991492884005033L;

    private Investitor i = new Investitor();
    private Anketa anketa = new Anketa();
    private String ponudjeniodgovor1;
    private String ponudjeniodgovor2;
    private String ponudjeniodgovor3;
    private String ponudjeniodgovor4;
    private String tekst;
    private String maksimalanBrojPitanja = "";
    private byte b = 0;
    private boolean disabled = false;

    List<Pitanja> pitanja = new ArrayList();

    public String getPonudjeniodgovor1() {
        return ponudjeniodgovor1;
    }

    public void setPonudjeniodgovor1(String ponudjeniodgovor1) {
        this.ponudjeniodgovor1 = ponudjeniodgovor1;
    }

    public String getPonudjeniodgovor2() {
        return ponudjeniodgovor2;
    }

    public void setPonudjeniodgovor2(String ponudjeniodgovor2) {
        this.ponudjeniodgovor2 = ponudjeniodgovor2;
    }

    public String getPonudjeniodgovor3() {
        return ponudjeniodgovor3;
    }

    public void setPonudjeniodgovor3(String ponudjeniodgovor3) {
        this.ponudjeniodgovor3 = ponudjeniodgovor3;
    }

    public String getPonudjeniodgovor4() {
        return ponudjeniodgovor4;
    }

    public void setPonudjeniodgovor4(String ponudjeniodgovor4) {
        this.ponudjeniodgovor4 = ponudjeniodgovor4;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getMaksimalanBrojPitanja() {
        return maksimalanBrojPitanja;
    }

    public void setMaksimalanBrojPitanja(String maksimalanBrojPitanja) {
        this.maksimalanBrojPitanja = maksimalanBrojPitanja;
    }

    public Investitor getI() {
        return i;
    }

    public void setI(Investitor i) {
        this.i = i;
    }

    public byte getB() {
        return b;
    }

    public void setB(byte b) {
        this.b = b;
    }

    public List<Pitanja> getPitanja() {
        return pitanja;
    }

    public void setPitanja(List<Pitanja> pitanja) {
        this.pitanja = pitanja;
    }

    public void setAnketa(Anketa anketa) {
        this.anketa = anketa;
    }

    public Anketa getAnketa() {
        return anketa;
    }

//    public Investitor getI() {
//        return i;
//    }
//
//    public void setI(Investitor i) {
//        this.i = i;
//    } 
    public void dodajPitanje() {
        Pitanja p = new Pitanja();
        p.setTekset(tekst);
        p.setAnketaId(anketa);
        p.setPonudjeniOdgovor1(ponudjeniodgovor1);
        p.setPonudjeniOdgovor2(ponudjeniodgovor2);
        p.setPonudjeniOdgovor3(ponudjeniodgovor3);
        p.setPonudjeniOdgovor4(ponudjeniodgovor4);
        pitanja.add(p);
        tekst = null;
        ponudjeniodgovor1 = null;
        ponudjeniodgovor2 = null;
        ponudjeniodgovor3 = null;
        ponudjeniodgovor4 = null;
        b += 1;

        if (b >= 20) {
            maksimalanBrojPitanja = "dozvoljeno je maksimalno 20 pitanja";
            disabled = true;
        }

    }

    public String registruj() {
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        
        if (user.getAttribute("ulogovaniKorisnik") != null && user.getAttribute("tip").equals("investitor")) {
            i = (Investitor) user.getAttribute("ulogovaniKorisnik");
            anketa.setInvestitorIdId(i);
        } else{
            return "/index";
        }
            Session session = HibernateUtil.createSessionFactory().openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                session.save(anketa);

                for (Pitanja p : pitanja) {
                    session.save(p);
                }
                anketa.setPitanjaCollection(pitanja);
                session.save(anketa);

                tx.commit();
                return "/Vesti";
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

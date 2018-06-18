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
import model.Odgovor;
import model.Pitanje;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


@ManagedBean
@ViewScoped
public class registrujAnketu  implements Serializable {
    
    static final long serialVersionUID = -687991492884005033L;
    
    Investitor i;
    Anketa anketa = new Anketa();  
    String ponudjeniodgovor1;
    String ponudjeniodgovor2;
    String ponudjeniodgovor3;
    String ponudjeniodgovor4;
    String tekst; 
    String maksimalanBrojPitanja="";
    byte b = 0;
    boolean disabled = false;
    
    List<Pitanje> pitanja = new ArrayList();

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
    
    public void dodajPitanje(){
    Pitanje p = new Pitanje();
    p.setTekst(tekst);
    p.setAnketaId(anketa);
    List<Odgovor> listaOdgovora = new ArrayList();
    
    if(ponudjeniodgovor1!=null&&ponudjeniodgovor1.trim().equals("")){
     Odgovor po = new Odgovor();
     po.setTekst(ponudjeniodgovor1);
     listaOdgovora.add(po);
     
    }
    if(ponudjeniodgovor2!=null&&ponudjeniodgovor2.trim().equals("")){
     Odgovor po = new Odgovor();
     po.setTekst(ponudjeniodgovor2);
     listaOdgovora.add(po);
     
    }
    if(ponudjeniodgovor3!=null&&ponudjeniodgovor3.trim().equals("")){
     Odgovor po = new Odgovor();
     po.setTekst(ponudjeniodgovor3);
     listaOdgovora.add(po);
     
    }
    if(ponudjeniodgovor4!=null&&ponudjeniodgovor4.trim().equals("")){
     Odgovor po = new Odgovor();
     po.setTekst(ponudjeniodgovor4);
     listaOdgovora.add(po);     
    }
    p.setOdgovorList(listaOdgovora);
    
    pitanja.add(p);    
    tekst = null;
    ponudjeniodgovor1 =null;
    ponudjeniodgovor2 =null;
    ponudjeniodgovor3 = null;
    ponudjeniodgovor4 =null;
    b+=1;
    
    if(b >=20){
     maksimalanBrojPitanja = "dozvoljeno je maksimalno 20 pitanja"; 
     disabled = true;
    }
    
    }
    
    public void registruj(){
         HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
         
        if (user.getAttribute("ulogovaniKorisnik") != null) {
            i = (Investitor) user.getAttribute("ulogovaniKorisnik");
            anketa.setInvestitorIdId(i);
        
    Session session = HibernateUtil.createSessionFactory().openSession();
    Transaction tx = null;
  
    try{
        tx = session.beginTransaction();
        
        session.save(anketa);
        
        for(Pitanje p : pitanja){
            for(Odgovor po : p.getOdgovorList()){
                 session.save(po);
            }
               session.save(p);
        }
           ;
        anketa.setPitanjeList(pitanja);
        session.save(anketa);
          
        tx.commit();
    }
    catch(HibernateException e){
    if(tx!=null){
        tx.rollback();
    }
        System.out.println(e);    
    }
    finally{
       if (session != null) {
            try {
                session.close();
            } catch (HibernateException ignored) {
                System.out.println("Couldn't close Session "+ ignored);
            }
        }  
    }
        } 
    }
    
    }


    

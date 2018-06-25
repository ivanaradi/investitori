package controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
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
    
    List<Pair<Pitanje,List<Odgovor>>> pitanja = new ArrayList();

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
     public void promeniPonudjeniodgovor1(AjaxBehaviorEvent e) {
        ponudjeniodgovor1 = (String) ((UIOutput) e.getSource()).getValue();
         System.out.println(ponudjeniodgovor1);
        
    }
    public void promeniPonudjeniodgovor2(AjaxBehaviorEvent e) {
        ponudjeniodgovor2 = (String) ((UIOutput) e.getSource()).getValue();
        System.out.println(ponudjeniodgovor2);
        
    }
     public void promeniPonudjeniodgovor3(AjaxBehaviorEvent e) {
        ponudjeniodgovor3 = (String) ((UIOutput) e.getSource()).getValue();
        System.out.println(ponudjeniodgovor3);
        
    }
      public void promeniPonudjeniodgovor4(AjaxBehaviorEvent e) {
        ponudjeniodgovor4 = (String) ((UIOutput) e.getSource()).getValue();
        System.out.println(ponudjeniodgovor4);
        
    }
//    public Investitor getI() {
//        return i;
//    }
//
//    public void setI(Investitor i) {
//        this.i = i;
//    } 
    
    public void dodajPitanje(){
    System.out.println("asdasdasdasdasdasda dodja");
     System.out.println(ponudjeniodgovor4);
      System.out.println(ponudjeniodgovor3);
       System.out.println(ponudjeniodgovor2);
        System.out.println(ponudjeniodgovor1);
    Pitanje p = new Pitanje();
    p.setTekst(tekst);
    p.setAnketaId(anketa);
    List<Odgovor> listaOdgovora = new ArrayList();
    Pair<Pitanje,List<Odgovor>> pair;
    System.out.println(ponudjeniodgovor4);
      System.out.println(ponudjeniodgovor3);
       System.out.println(ponudjeniodgovor2);
        System.out.println(ponudjeniodgovor1);
    
    if(ponudjeniodgovor1!=null&&!ponudjeniodgovor1.trim().equals("")){
     Odgovor po = new Odgovor();
     po.setTekst(ponudjeniodgovor1);
     po.setPitanjeId(p);
     listaOdgovora.add(po);
     System.out.println(ponudjeniodgovor1+" aaa");
     
    }
    if(ponudjeniodgovor2!=null&&!ponudjeniodgovor2.trim().equals("")){
     
        System.out.println("nije null");
        Odgovor po = new Odgovor();
     po.setTekst(ponudjeniodgovor2);
     po.setPitanjeId(p);
     listaOdgovora.add(po);
     System.out.println(ponudjeniodgovor2+" aaa");
     
    }
    if(ponudjeniodgovor3!=null&&!ponudjeniodgovor3.trim().equals("")){
     Odgovor po = new Odgovor();
     po.setTekst(ponudjeniodgovor3);
     po.setPitanjeId(p);
     listaOdgovora.add(po);
     System.out.println(ponudjeniodgovor3+" aaa");
     
    }
    if(ponudjeniodgovor4!=null&&!ponudjeniodgovor4.trim().equals("")){
     Odgovor po = new Odgovor();
     po.setTekst(ponudjeniodgovor4);
     po.setPitanjeId(p);
     listaOdgovora.add(po);
     System.out.println(ponudjeniodgovor4+" aaa");     
    }
  
    
    pair = new Pair(p,listaOdgovora);
    pitanja.add(pair);
            
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
            
            System.out.println("naso ulogovanog korisnika !");
            
            i = (Investitor) user.getAttribute("ulogovaniKorisnik");
            System.out.println(i.getKorisnickoIme());
            anketa.setInvestitorIdId(i);
        
    Session session = HibernateUtil.createSessionFactory().openSession();
    Transaction tx = null;
  
    try{
        tx = session.beginTransaction();
        List pitanjaAnkete = new ArrayList();
        session.save(anketa);
        System.out.println("sacuvo anketu");
        
        for(Pair p : pitanja){
            Pitanje pitanje = (Pitanje)p.getLeft();
            pitanje.setAnketaId(anketa);
            List<Odgovor> ogdovori = (List<Odgovor>)p.getRight();
            session.save(pitanje);
             
            for(Odgovor o : ogdovori){
               o.setPitanjeId(pitanje);
               session.save(o);               
            }
            
          
            
            pitanje.setOdgovorList(ogdovori);
            pitanjaAnkete.add(pitanje);
           
           
        }
           ;
        anketa.setPitanjeList(pitanjaAnkete);
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


    

package controler;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import model.Delatnost;
import model.DelatnostDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


@ManagedBean
@ViewScoped
public class RegistracijaDelatnosti implements Serializable{

    
      Delatnost delatnost = new Delatnost();
      private String poruka = "";
      private List<String> delatnosti = DelatnostDAO.NaziviSvihDelatnoosti();
      private boolean disejblovano = false;
      
     private List<Delatnost> filtriraneDelatnosti;

    public List<Delatnost> getFiltriraneDelatnosti() {
        return filtriraneDelatnosti;
    }

    public void setFiltriraneDelatnosti(List<Delatnost> filtriraneDelatnosti) {
        this.filtriraneDelatnosti = filtriraneDelatnosti;
    }
     
      

    public boolean isDisejblovano() {
        return disejblovano;
    }

    public void setDisejblovano(boolean disejblovano) {
        this.disejblovano = disejblovano;
    }
      
      

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public List<String> getDelatnosti() {
        return delatnosti;
    }

    public void setDelatnosti(List<String> delatnosti) {
        this.delatnosti = delatnosti;
    }

        
       
        public Delatnost getDelatnost() {
            return delatnost;
        }

        public void setDelatnost(Delatnost delatnost) {
            this.delatnost = delatnost;
        } 
        public void proveriKorisnickoIme(AjaxBehaviorEvent e) {
        String korisnickoIme = (String) ((UIOutput) e.getSource()).getValue();
        for (String s : delatnosti) {
            if (s.equalsIgnoreCase(korisnickoIme)) {
                poruka = "naziv je zauzet";
                disejblovano = true;
                return;
            }
        }
        disejblovano = false;
        poruka = "";
    }
 
    
    public String sacuvajDelatnost(){  
    Session session = HibernateUtil.createSessionFactory().openSession();
    Transaction tx = null;
  
    try{
        tx = session.beginTransaction();
        session.save(delatnost);
      
        tx.commit();
        delatnost = new Delatnost();
        
        return "/user/registracijaDelatnosti";
    }
    catch(HibernateException e){
    if(tx!=null){
        tx.rollback();
    }
        System.out.println(e); 
        return null;
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

    




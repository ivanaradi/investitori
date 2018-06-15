
package controler;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Projekat;
import model.Startap;
import model.StartapDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
@ViewScoped
public class RegistracijaProjekta implements Serializable { 

static final long serialVersionUID = -687991492884005033L;

    
private Projekat projekat = new Projekat(); 

private Startap startap;
@PostConstruct
    public void init() {
    startap = (Startap)FacesContext.getCurrentInstance().getExternalContext().getFlash().get("startap");
    System.out.println("startap id sa Registracije projekta "+startap.getId());
    }
    

    public Projekat getProjekat() {
        return projekat;
    }

    public void setProjekat(Projekat projekat) {
        this.projekat = projekat;
    }



public void sacuvajProjekat(){
    projekat.setStartapId(startap);
    
    
     Session session = HibernateUtil.createSessionFactory().openSession();
     Transaction tx = null;
      try {
            tx = session.beginTransaction();
            session.save(projekat);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
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
      

    

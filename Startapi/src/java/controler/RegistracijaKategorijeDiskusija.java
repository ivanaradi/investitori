package controler;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Kategorijadiskusija;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
@ViewScoped
public class RegistracijaKategorijeDiskusija {

    private Kategorijadiskusija kategorijaDiskusija = new Kategorijadiskusija();
  

    public Kategorijadiskusija getKategorijaDiskusija() {
        return kategorijaDiskusija;
    }

    public void setKategorijaDiskusija(Kategorijadiskusija kategorijaDiskusija) {
        this.kategorijaDiskusija = kategorijaDiskusija;
    }

    
    public String sacuvajKategoruijuDiskusija() {
               
        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(kategorijaDiskusija);

            tx.commit();
            return "/index?faces-redirect=true";
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Greska! Molimo Vas pokusajte ponovo kasnije"));
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


package controler;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Investitor;
import model.Opstina;
import model.Startap;
import model.Ulica;
import model.UlicaDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
@ViewScoped
public class ProveraKodaInvestitora {
    
    Integer noviKod ;

    public Integer getNoviKod() {
        return noviKod;
    }

    public void setNoviKod(Integer noviKod) {
        this.noviKod = noviKod;
    }
    
    
    private Integer kod = (Integer)FacesContext.getCurrentInstance().getExternalContext().getFlash().get("kod");
    private Investitor investitor = (Investitor)FacesContext.getCurrentInstance().getExternalContext().getFlash().get("investitor");
    private String ulicaStr = (String)FacesContext.getCurrentInstance().getExternalContext().getFlash().get("ulica");
    private Opstina opstina = (Opstina)FacesContext.getCurrentInstance().getExternalContext().getFlash().get("opstina");
    
    public void sacuvajInvestitora(){
        
        System.out.println("kod od ranije" + kod);
        System.out.println("novi kod sa maila " + noviKod);
        if(noviKod.equals(kod)){
        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;
        
        if(ulicaStr!=null&&ulicaStr.trim()!=""&&opstina!=null){
            
           int id = opstina.getIdopstina();

        Ulica ulica = UlicaDAO.dohvatiUlicuPoNazivuIOpstini(ulicaStr.trim(), id);
        if(ulica == null){
         try {
            ulica = new Ulica();
            ulica.setNaziv(ulicaStr);
            ulica.setOpstinaIdopstina(opstina);
             tx = session.beginTransaction();
                session.save(ulica);

                tx.commit();
            } catch (Exception e) {
                System.out.println("uso sam u catch");
                if (tx != null) {
                    tx.rollback();
                }
                System.out.println(e);
            }

        }    
        

        investitor.setUlicaId(ulica);        
        
        
        System.out.println("investitor ulica id:" + ulica.getId() + "investitor naziv ulice :" + ulica.getNaziv());
        }
        
        investitor.setDatumRegistracije(new Date());
        investitor.setDatumposlednjeglogovanja(new Date());
    try {
            tx = session.beginTransaction();
            session.save(investitor);

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
    
    else{
            System.out.println("pooogresan kod");
}
    

}
    
}

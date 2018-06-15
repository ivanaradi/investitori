
package controler;

import static com.sun.faces.el.FacesCompositeELResolver.ELResolverChainType.Faces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import model.Delatnost;
import model.DelatnostDAO;
import model.Drzava;
import model.DrzavaDAO;
import model.Grad;
import model.GradDAO;
import model.KorisnikDAO;
import model.Opstina;
import model.OpstinaDAO;
import model.Startap;
import model.Ulica;
import model.UlicaDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


@ManagedBean
@ViewScoped
public class RegistracijaStartapa implements Serializable {
 

    static final long serialVersionUID = -687991492884005033L;

    List<Drzava> drzave; 
    String drzava;
    List<Grad> gradovi; 
    String grad;
    List<Opstina> opstine;
    String opstina;
    List<Ulica> ulice;
    String ulica;
    Opstina o;
    private String ulicaAC = "";
    
    int delatnost;
    List<Delatnost> delatnosti;
    @PostConstruct
    public void init() {

        this.delatnosti = DelatnostDAO.sveDelatnosti();
        
        this.drzave = DrzavaDAO.sveDrzave();

        this.gradovi = new ArrayList<>();

        this.opstine = new ArrayList<>();

        this.ulice = new ArrayList<>();

    }

    public String getUlicaAC() {
        return ulicaAC;
    }

    public void setUlicaAC(String ulicaAC) {
        this.ulicaAC = ulicaAC;
    }
     
    

    public String getNazivUlice() {
        return nazivUlice;
    }

    public void setNazivUlice(String nazivUlice) {
        this.nazivUlice = nazivUlice;
    }
    String nazivUlice;

    public List<Grad> getGradovi() {
        return gradovi;
    }

    public void setGradovi(List<Grad> gradovi) {
        this.gradovi = gradovi;
    }

    public int getDelatnost() {
        return delatnost;
    }

    public void setDelatnost(int delatnost) {
        this.delatnost = delatnost;
    }

    public List<Delatnost> getDelatnosti() {
        return delatnosti;
    }

    public void setDelatnosti(List<Delatnost> delatnosti) {
        this.delatnosti = delatnosti;
    }

    
    public List<String> completeText(String naziv) {
        int id = o.getIdopstina();
        List<String> results = UlicaDAO.dohvatiNazivePoImenuIOpstini(id, naziv);
        
     return results;
    }
    

    public void promeniGradove() {

        Drzava d = DrzavaDAO.dohvatiDrzavuPoNazivu(drzava);
        gradovi = (List<Grad>) d.getGradCollection();
        grad = null;
        opstine = new ArrayList();
        opstina = null;
        ulice = new ArrayList();
        ulica = null;

    }

    public void promeniOpstine() {
        Grad g = GradDAO.dohvatiGradPoNazivu(grad);
        opstine = (List<Opstina>) g.getOpstinaCollection();
        opstina = null;
        ulice = new ArrayList();
        ulica = null;

    }

    public void promeniUlice() {
        o = OpstinaDAO.dohvatiOpstinuPoNazivu(opstina);
        ulice = (List<Ulica>) o.getUlicaCollection();
        disejbluj();

    }

    public List<Drzava> getDrzave() {
        return drzave;
    }

    public void setDrzave(List<Drzava> drzave) {
        this.drzave = drzave;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public List<Opstina> getOpstine() {
        return opstine;
    }

    public void setOpstine(List<Opstina> opstine) {
        this.opstine = opstine;
    }

    public String getOpstina() {
        return opstina;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }

    public List<Ulica> getUlice() {
        return ulice;
    }

    public void setUlice(List<Ulica> ulice) {
        this.ulice = ulice;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

   
    private Startap startap = new Startap();
    private String poruka = "";
    private int ulicaId = 0;

    private boolean disejblovano = true;

    public boolean isDisejblovano() {
        return disejblovano;
    }

    public void setDisejblovano(boolean disejblovano) {
        this.disejblovano = disejblovano;
    }


    public void disejbluj() {

        if (opstina != null) {
            
            disejblovano = false;
        } else {
           
            disejblovano = true;
        }

    }
    
    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String getPoruka() {
        return poruka;
    }
    
    public void dohvatiOpstinu(){
          o = OpstinaDAO.dohvatiOpstinuPoNazivu(opstina);
    }

    List<String> korisnici = KorisnikDAO.SvakorisnickaImena();

    public void proveriKorisnickoIme(AjaxBehaviorEvent e) {
        String korisnickoIme = (String) ((UIOutput) e.getSource()).getValue();
        System.out.println(korisnickoIme);
        for (String s : korisnici) {
            if (s.equalsIgnoreCase(korisnickoIme)) {
                poruka = "ime je zauzeto";
                return;
            }
        }
        poruka = "";
    }

    public int getUlicaId() {
        return ulicaId;
    }

    public void setUlicaId(int ulicaId) {
        this.ulicaId = ulicaId;
    }

    public Startap getStartap() {
        return startap;
    }

    public void setStartap(Startap startap) {
        this.startap = startap;
    }

   
    public String sacuvajStartapa() {
        String s = null;
        
        System.out.println("uso u sacuvaj startapa ");
       try{
           for(Delatnost d : delatnosti){
            if(d.getId() == delatnost){
                startap.setDelatnostId(d);
                break;
            } 
        }
       }
       catch(Exception e){}
       
       Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;
        
        if(ulicaAC.trim()!=""&&o!=null){
            
           int id = o.getIdopstina();

        Ulica ulica = UlicaDAO.dohvatiUlicuPoNazivuIOpstini(ulicaAC.trim(), id);
        if(ulica == null){
         try {
            ulica = new Ulica();
            ulica.setNaziv(ulicaAC);
            ulica.setOpstinaIdopstina(o);
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
        
        
//        System.out.println("ulicas je nova ulica()");
//        if ("".equals(nazivUlice.trim()) && ulicaId != 0) {
//            ulica = UlicaDao.dohvatiUlicuPoIdu(ulicaId);
//            try {
//                investitor.setUlicaId(ulica);
//            } catch (Exception e) {
//            }
//
//        } else if (!"".equals(nazivUlice.trim())) {
//            System.out.println("uso sam u elzeeee");
//            try {
//                System.out.println("uso sam u try");
//                ulica = new Ulica();
//                ulica.setOpstinaIdopstina(o);
//                ulica.setNaziv(nazivUlice);
//
//                tx = session.beginTransaction();
//                session.save(ulica);
//
//                tx.commit();
//            } catch (Exception e) {
//                System.out.println("uso sam u catch");
//                if (tx != null) {
//                    tx.rollback();
//                }
//                System.out.println(e);
//            }
//
//        }

        startap.setUlicaId(ulica);
        System.out.println("investitor ulica id:" + ulica.getId() + "investitor naziv ulice :" + ulica.getNaziv());
        }

        try {
           
            tx = session.beginTransaction();
            session.save(startap);
            tx.commit();           
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("startap", startap);
            s = "registracijaProjekta?faces-redirect=true";
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
        return s;
    }

}

    


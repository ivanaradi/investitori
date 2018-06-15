/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Korisnik;
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
public class PodaciOKorisniku {

    private Korisnik ulogovaniKorisnik;

    public Korisnik dohvatiKorisnika() {
        HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        ulogovaniKorisnik = (Korisnik) user.getAttribute("ulogovaniKorisnik");

        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            Query q = session.getNamedQuery("Korisnik.findById");
            q.setInteger("id", ulogovaniKorisnik.getId());
            Query query = session.createQuery("select k from Korisnik k where k.id = " + ulogovaniKorisnik.getId());
            if (!q.list().isEmpty()) {
                ulogovaniKorisnik = (Korisnik) q.list().get(0);
                return ulogovaniKorisnik;
            }

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
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
        return null;
    }

    public void update() {

        Transaction transaction = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
                 
            session.update(ulogovaniKorisnik);
            transaction.commit();
           

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
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

//    private String naziv;
//    private String sediste;
//    private int pib;
//    private int telefon;
//    private String mail;
//    private String oKompaniji;
//    private String oblastDelovanja;
//    private String webAdresaSajta;
//    private String korisnickoIme;
//    private String lozinka;
//
//    public String getNaziv() {
//        return naziv;
//    }
//
//    public void setNaziv(String naziv) {
//        this.naziv = naziv;
//    }
//
//    public String getSediste() {
//        return sediste;
//    }
//
//    public void setSediste(String sediste) {
//        this.sediste = sediste;
//    }
//
//    public int getPib() {
//        return pib;
//    }
//
//    public void setPib(int pib) {
//        this.pib = pib;
//    }
//
//    public int getTelefon() {
//        return telefon;
//    }
//
//    public void setTelefon(int telefon) {
//        this.telefon = telefon;
//    }
//
//    public String getMail() {
//        return mail;
//    }
//
//    public void setMail(String mail) {
//        this.mail = mail;
//    }
//
//    public String getOKompaniji() {
//        return oKompaniji;
//    }
//
//    public void setOKompaniji(String oKompaniji) {
//        this.oKompaniji = oKompaniji;
//    }
//
//
//    public String getOblastDelovanja() {
//        return oblastDelovanja;
//    }
//
//    public void setOblastDelovanja(String oblastDelovanja) {
//        this.oblastDelovanja = oblastDelovanja;
//    }
//
//    public String getWebAdresaSajta() {
//        return webAdresaSajta;
//    }
//
//    public void setWebAdresaSajta(String webAdresaSajta) {
//        this.webAdresaSajta = webAdresaSajta;
//    }
//
//    public String getKorisnickoIme() {
//        return korisnickoIme;
//    }
//
//    public void setKorisnickoIme(String korisnickoIme) {
//        this.korisnickoIme = korisnickoIme;
//    }
//
//    public String getLozinka() {
//        return lozinka;
//    }
//
//    public void setLozinka(String lozinka) {
//        this.lozinka = lozinka;
//    }
//    
//    List<Podatak> sviPodaci = new ArrayList<Podatak>();
//
//    public List<Podatak> getSviPodaci() {
//        return sviPodaci;
//    }
//
//    public void setSviPodaci(List<Podatak> sviPodaci) {
//        this.sviPodaci = sviPodaci;
//    }
//    
//    public List<Podatak> dohvatiPodatke(){
//        
//        try {
//            Connection conn = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
//            Statement stm = conn.createStatement();
//            ResultSet rs = stm.executeQuery("select id, punNaziv, PIB, telefon, mail, oblastiPoslovanja, webSite, korisnickoIme, lozinka from korisnik");
//            sviPodaci = new ArrayList<Podatak>();
//            while(rs.next()){
//                Podatak podatak = new Podatak();
//                podatak.setId(rs.getInt("id"));
//                podatak.setNaziv(rs.getString("punNaziv"));
//                podatak.setPib(rs.getInt("PIB"));
//                podatak.setTelefon(rs.getInt("telefon"));
//                podatak.setMail(rs.getString("mail"));
//                podatak.setOblastDelovanja(rs.getString("oblastiPoslovanja"));
//                podatak.setWebAdresaSajta(rs.getString("webSite"));
//                podatak.setKorisnickoIme(rs.getString("korisnickoIme"));
//                podatak.setLozinka(rs.getString("lozinka"));
//                
//                sviPodaci.add(podatak);
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(PodaciOKorisniku.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        return sviPodaci;
//    }
//    
//    private int id;
//    public void sacuvaj(Podatak podatak){
//        id = podatak.getId();
//        naziv = podatak.getNaziv();
//        //sediste = podatak.getSediste();
//        pib = podatak.getPib();
//        telefon = podatak.getTelefon();
//        mail = podatak.getMail();
//        //oKompaniji = podatak.getOKompaniji();
//        oblastDelovanja = podatak.getOblastDelovanja();
//        webAdresaSajta = podatak.getWebAdresaSajta();
//        korisnickoIme = podatak.getKorisnickoIme();
//        lozinka = podatak.getLozinka();
//    }
//
//    public String izmeniPodatke(){
//        try {
//            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
//            Statement stm = con.createStatement();
//            
//           stm.executeUpdate("update korisnik set punNaziv='"+naziv+/*"', sediste='"+sediste+*/"', PIB='"+pib+"', telefon='"+telefon+"', mail='"+mail+/*"', oKompaniji='"+oKompaniji+*/"', oblastiPoslovanja='"+oblastDelovanja+"', webSite='"+webAdresaSajta+"', korisnickoIme='"+korisnickoIme+"', lozinka="+lozinka+"where id="+id);
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(PodaciOKorisniku.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    
//    public void update(){
//        try {
//            Connection con = DriverManager.getConnection(db.DB.connectionString, db.DB.user, db.DB.password);
//            Statement stm = con.createStatement();
//            
//           stm.executeUpdate("update korisnik set punNaziv='"+naziv+/*"', sediste='"+sediste+*/"', PIB='"+pib+"', telefon='"+telefon+"', mail='"+mail+/*"', oKompaniji='"+oKompaniji+*/"', oblastiPoslovanja='"+oblastDelovanja+"', webSite='"+webAdresaSajta+"', korisnickoIme='"+korisnickoIme+"', lozinka="+lozinka+"where id="+id);
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(PodaciOKorisniku.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         
//    }
//   
    public Korisnik getUlogovaniKorisnik() {
        return ulogovaniKorisnik;
    }

    public void setUlogovaniKorisnik(Korisnik ulogovaniKorisnik) {
        this.ulogovaniKorisnik = ulogovaniKorisnik;
    }
}

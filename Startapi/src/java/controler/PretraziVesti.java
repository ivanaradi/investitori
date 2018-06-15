/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Kategorijavesti;
import model.KategorijeVestiDAO;
import model.Korisnik;
import model.Vest;
import model.Vidljivost;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@ViewScoped
public class PretraziVesti implements Serializable {

    private String tekstPretrage = "";
    private boolean napredno = false;
    private String autor = "";
    private ArrayList<String> izabraneKategorije = new ArrayList<>();
    private ArrayList<Kategorijavesti> sveKategorije = KategorijeVestiDAO.dohvatiKategorije();
    private Date datumObjaveOd;
    private Date datumObjaveDo;
    private ArrayList<Vest> rezultat;
    private Vest izabranaVest;
    private boolean nisuUnetiParametri = false;

    public boolean isNisuUnetiParametri() {
        return nisuUnetiParametri;
    }

    public void setNisuUnetiParametri(boolean nisuUnetiParametri) {
        this.nisuUnetiParametri = nisuUnetiParametri;
    }

    public Vest getIzabranaVest() {
        return izabranaVest;
    }

    public void setIzabranaVest(Vest izabranaVest) {
        this.izabranaVest = izabranaVest;
    }

    public ArrayList<Vest> getRezultat() {
        return rezultat;
    }

    public void setRezultat(ArrayList<Vest> rezultat) {
        this.rezultat = rezultat;
    }

    public Date getDatumObjaveOd() {
        return datumObjaveOd;
    }

    public void setDatumObjaveOd(Date datumObjaveOd) {
        this.datumObjaveOd = datumObjaveOd;
    }

    public Date getDatumObjaveDo() {
        return datumObjaveDo;
    }

    public void setDatumObjaveDo(Date datumObjaveDo) {
        this.datumObjaveDo = datumObjaveDo;
    }

    public ArrayList<Kategorijavesti> getSveKategorije() {
        return sveKategorije;
    }

    public void setSveKategorije(ArrayList<Kategorijavesti> sveKategorije) {
        this.sveKategorije = sveKategorije;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public ArrayList<String> getIzabraneKategorije() {
        return izabraneKategorije;
    }

    public void setIzabraneKategorije(ArrayList<String> izabraneKategorije) {
        this.izabraneKategorije = izabraneKategorije;
    }

    public boolean isNapredno() {
        return napredno;
    }

    public void setNapredno(boolean napredno) {
        this.napredno = napredno;
    }

    public String getTekstPretrage() {
        return tekstPretrage;
    }

    public void setTekstPretrage(String tekstPretrage) {
        this.tekstPretrage = tekstPretrage;
    }

    private java.sql.Date sqlDate(java.util.Date datum) {
        return new java.sql.Date(datum.getTime());
    }

    private String[] splitString(String tekst) {
        String[] parametri = tekst.split("\\s+");
        return parametri;
    }

    
    /*
    dohvata vesti koje korisnik/gost moze da vidi i koje ispunjavaju parametre pretrage
    
     */
    public void Pretrazi() {
        if (tekstPretrage.isEmpty() && autor.isEmpty() && datumObjaveOd == null && datumObjaveDo == null && izabraneKategorije.isEmpty()) {
            nisuUnetiParametri = true;
        } else {
            nisuUnetiParametri = false;
            
            String upit = "select distinct v from Vest v left join v.korisnikCollection kk where (v.vidljivost in (" + Vidljivost.SVI.getSifra();
            Map<String, Object> unosi = new HashMap<>();
            HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (user.getAttribute("ulogovaniKorisnik") == null) {
                upit += "))";
            } else {
                Korisnik kor = (Korisnik) user.getAttribute("ulogovaniKorisnik");

                String tip = (String) user.getAttribute("tip");
                switch (tip) {
                    case ("startap"):
                        upit += ", " + Vidljivost.STARTAPI.getSifra();
                        break;
                    case ("investitor"):
                        upit += ", " + Vidljivost.INVESTITORI.getSifra();
                        break;
                }
                upit += ") or " + kor.getId() + " = kk.id)";
            }

            if (!tekstPretrage.equals("")) {
       

                String[] parametri = splitString(tekstPretrage);
                upit += " and (";
                if (parametri != null) {
                    for (int i = 0; i < parametri.length; i++) {
                        upit += " v.tekst like :parametri" + i + " or v.naziv like :parametri" + i + " or";
                        unosi.put("parametri" + i, "%" + parametri[i] + "%");
                    }
                    upit = upit.substring(0, upit.length() - 2);
                    upit += ")";
                }
            }
            if (!autor.equals("")) {
                upit += " and v.korisnikId in (SELECT k.id from Korisnik k WHERE k.punNaziv like :autor)";
                unosi.put("autor", "%" + autor + "%");
            }

            if (datumObjaveOd != null && datumObjaveDo == null) {
                datumObjaveOd = sqlDate(datumObjaveOd);

                upit += " and v.vremeKreiranja >= :datumObjaveOd";
                unosi.put("datumObjaveOd", datumObjaveOd);

            }
            if (datumObjaveDo != null) {
                if (datumObjaveOd != null) {
                    datumObjaveOd = sqlDate(datumObjaveOd);
                    datumObjaveDo = sqlDate(datumObjaveDo);

                    upit += " and v.vremeKreiranja between :datumObjaveOd and :datumObjaveDo";
                    unosi.put("datumObjaveOd", datumObjaveOd);
                    unosi.put("datumObjaveDo", datumObjaveDo);

                } else {
                    datumObjaveDo = sqlDate(datumObjaveDo);
                    unosi.put("datumObjaveDo", datumObjaveDo);
                    upit += " and v.vremeKreiranja <= :datumObjaveDo";

                }
            }

            if (!izabraneKategorije.isEmpty()) {

                upit += " and v.kategorijaVestiId.naslov in (";
                for (int i = 0; i < izabraneKategorije.size(); i++) {
                    upit += ":izabraneKategorije" + i + ",";
                    unosi.put("izabraneKategorije" + i, izabraneKategorije.get(i));
                }
                upit = upit.substring(0, upit.length() - 1) + ")";
            }

            Transaction t = null;
            Session s = HibernateUtil.createSessionFactory().openSession();
            try {
                Query q = s.createQuery(upit);
                for (String param : q.getNamedParameters()) {
                    q.setParameter(param, unosi.get(param));
                }

                rezultat = (ArrayList<Vest>) q.list();

                //return rezultat;
            } catch (HibernateException e) {
                if (t != null) {
                    t.rollback();
                }
                System.out.println(e);
                // return null;
            } finally {
                if (s != null) {
                    try {
                        s.close();
                    } catch (HibernateException ignored) {
                        System.out.println("Couldn't close Session " + ignored);
                    }
                }
            }

        }
    }
}

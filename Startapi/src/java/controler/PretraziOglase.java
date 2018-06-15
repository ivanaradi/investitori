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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Korisnik;
import model.Oglas;
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
public class PretraziOglase implements Serializable {

    private boolean napredno = false;
    private String tekstPretrage = "";
    private String autor = "";
    private Date datumObjaveOd;
    private Date datumObjaveDo;
    private ArrayList<Oglas> rezultat;
    private Date datumIstekaOd;
    private Date datumIstekaDo;
    private boolean nisuUnetiParametri = false;

    public boolean isNisuUnetiParametri() {
        return nisuUnetiParametri;
    }

    public void setNisuUnetiParametri(boolean nisuUnetiParametri) {
        this.nisuUnetiParametri = nisuUnetiParametri;
    }

    public Date getDatumIstekaOd() {
        return datumIstekaOd;
    }

    public void setDatumIstekaOd(Date datumIstekaOd) {
        this.datumIstekaOd = datumIstekaOd;
    }

    public Date getDatumIstekaDo() {
        return datumIstekaDo;
    }

    public void setDatumIstekaDo(Date datumIstekaDo) {
        this.datumIstekaDo = datumIstekaDo;
    }

    public String getTekstPretrage() {
        return tekstPretrage;
    }

    public void setTekstPretrage(String tekstPretrage) {
        this.tekstPretrage = tekstPretrage;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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

    public ArrayList<Oglas> getRezultat() {
        return rezultat;
    }

    public void setRezultat(ArrayList<Oglas> rezultat) {
        this.rezultat = rezultat;
    }

    public boolean isNapredno() {
        return napredno;
    }

    public void setNapredno(boolean napredno) {
        this.napredno = napredno;
    }

    /*
    pomocna metoda, odvaja reci unesene u polja za pretragu
     */
    private String[] splitString(String tekst) {
        String[] parametri = tekst.split("\\s+");
        return parametri;
    }

    /*
    pomocna metoda, pretvara java.util.Date u java.sql.Date
     */
    private java.sql.Date sqlDate(java.util.Date datum) {
        return new java.sql.Date(datum.getTime());
    }

    public void Pretrazi() {
        if (tekstPretrage.isEmpty() && autor.isEmpty() && datumObjaveOd == null && datumObjaveDo == null && datumIstekaOd == null && datumIstekaDo == null) {
            nisuUnetiParametri = true;
        } else {
            nisuUnetiParametri = false;

            String upit;
            HttpSession user = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            Map<String, Object> unosi = new HashMap<>();

            upit = "select distinct o from Oglas o left join o.korisnikCollection kk where (o.vidljivost in (" + Vidljivost.SVI.getSifra() + ", " + Vidljivost.KORISNICI.getSifra();
            if (user.getAttribute("ulogovaniKorisnik") != null) {
                Korisnik kor = (Korisnik) user.getAttribute("ulogovaniKorisnik");
                String tip = (String) user.getAttribute("tip");
                switch (tip) {
                    case ("startap"):
                        upit += ", " + Vidljivost.STARTAPI.getSifra();
                        break;
                    case ("investitor"):
                        break;
                }
                upit += ") or " + kor.getId() + " = kk.id)";
            } else {
                upit += "))";
            }
            if (!tekstPretrage.equals("")) {
                String[] parametri = splitString(tekstPretrage);
                if (parametri != null) {
                    upit += " and (";
                    for (int i = 0; i < parametri.length; i++) {
                        upit = upit + " o.tekst like :p" + i + " or o.naslov like :p" + i + " or";
                        unosi.put("p" + i, "%" + parametri[i] + "%");
                    }
                    upit = upit.substring(0, upit.length() - 2);
                    upit += ")";

                }
            }
            if (!autor.equals("")) {
                upit += " and o.investitorId in (SELECT k.id from Korisnik k WHERE k.punNaziv like :autor)";
                unosi.put("autor", autor);
            }

            if (datumObjaveOd != null && datumObjaveDo == null) {
                datumObjaveOd = sqlDate(datumObjaveOd);
                upit += " and o.datumIVremePostavljanja >= :datumObjaveOd";
                unosi.put("datumObjaveOd", datumObjaveOd);

            }
            if (datumObjaveDo != null) {
                if (datumObjaveOd != null) {
                    datumObjaveOd = sqlDate(datumObjaveOd);
                    datumObjaveDo = sqlDate(datumObjaveDo);

                    upit += " and o.datumIVremePostavljanja between :datumObjaveOd and :datumObjaveDo";
                    unosi.put("datumObjaveOd", datumObjaveOd);
                    unosi.put("datumObjaveDo", datumObjaveDo);

                } else {
                    datumObjaveDo = sqlDate(datumObjaveDo);

                    upit += " and o.datumIVremePostavljanja <= :datumObjaveDo";
                    unosi.put("datumObjaveDo", datumObjaveDo);

                }
            }
            if (datumIstekaOd != null && datumIstekaDo == null) {
                datumIstekaOd = sqlDate(datumIstekaOd);

                upit += " and o.datumIVremeIsteka >= :datumIstekaOd";
                unosi.put("datumIstekaOd", datumIstekaOd);

            }
            if (datumObjaveDo != null) {
                if (datumIstekaOd != null) {
                    datumIstekaOd = sqlDate(datumIstekaOd);
                    datumIstekaDo = sqlDate(datumIstekaDo);

                    upit += " and o.datumIVremeIsteka between :datumIstekaOd and :datumIstekaDo";
                    unosi.put("datumIstekaOd", datumIstekaOd);
                    unosi.put("datumIstekaDo", datumIstekaDo);

                } else {
                    datumIstekaDo = sqlDate(datumIstekaDo);

                    upit += " and o.datumIVremeIsteka <= :datumIstekaDo";
                    unosi.put("datumIstekaDo", datumIstekaDo);
                }
            }

            Transaction t = null;
            Session s = HibernateUtil.createSessionFactory().openSession();
            try {
                Query q = s.createQuery(upit);

                for (String param : q.getNamedParameters()) {
                    q.setParameter(param, unosi.get(param));
                }
                rezultat = (ArrayList<Oglas>) q.list();
                //   return rezultat;
            } catch (HibernateException e) {
                if (t != null) {
                    t.rollback();
                }
                System.out.println(e);
                //   return null;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "korisnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k")
    , @NamedQuery(name = "Korisnik.findById", query = "SELECT k FROM Korisnik k WHERE k.id = :id")
    , @NamedQuery(name = "Korisnik.findByKorisnickoIme", query = "SELECT k FROM Korisnik k WHERE k.korisnickoIme = :korisnickoIme")
    , @NamedQuery(name = "Korisnik.findByLozinka", query = "SELECT k FROM Korisnik k WHERE k.lozinka = :lozinka")
    , @NamedQuery(name = "Korisnik.findByPunNaziv", query = "SELECT k FROM Korisnik k WHERE k.punNaziv = :punNaziv")
    , @NamedQuery(name = "Korisnik.findByDatumOsnivanja", query = "SELECT k FROM Korisnik k WHERE k.datumOsnivanja = :datumOsnivanja")
    , @NamedQuery(name = "Korisnik.findByRegistarskiBroj", query = "SELECT k FROM Korisnik k WHERE k.registarskiBroj = :registarskiBroj")
    , @NamedQuery(name = "Korisnik.findByPib", query = "SELECT k FROM Korisnik k WHERE k.pib = :pib")
    , @NamedQuery(name = "Korisnik.findByIme", query = "SELECT k FROM Korisnik k WHERE k.ime = :ime")
    , @NamedQuery(name = "Korisnik.findByPrezime", query = "SELECT k FROM Korisnik k WHERE k.prezime = :prezime")
    , @NamedQuery(name = "Korisnik.findBySrednjeIme", query = "SELECT k FROM Korisnik k WHERE k.srednjeIme = :srednjeIme")
    , @NamedQuery(name = "Korisnik.findByTelefon", query = "SELECT k FROM Korisnik k WHERE k.telefon = :telefon")
    , @NamedQuery(name = "Korisnik.findByMail", query = "SELECT k FROM Korisnik k WHERE k.mail = :mail")
    , @NamedQuery(name = "Korisnik.findByWebSite", query = "SELECT k FROM Korisnik k WHERE k.webSite = :webSite")
    , @NamedQuery(name = "Korisnik.findByDrustveneMrezeId", query = "SELECT k FROM Korisnik k WHERE k.drustveneMrezeId = :drustveneMrezeId")
    , @NamedQuery(name = "Korisnik.findByOblastiPoslovanja", query = "SELECT k FROM Korisnik k WHERE k.oblastiPoslovanja = :oblastiPoslovanja")
    , @NamedQuery(name = "Korisnik.findByBrojZaposlenih", query = "SELECT k FROM Korisnik k WHERE k.brojZaposlenih = :brojZaposlenih")
    , @NamedQuery(name = "Korisnik.findByPrihodUPoslednjeTriGodine", query = "SELECT k FROM Korisnik k WHERE k.prihodUPoslednjeTriGodine = :prihodUPoslednjeTriGodine")
    , @NamedQuery(name = "Korisnik.findByProfitUPoslednjeTriGodine", query = "SELECT k FROM Korisnik k WHERE k.profitUPoslednjeTriGodine = :profitUPoslednjeTriGodine")})
@Inheritance(strategy=InheritanceType.JOINED)  
public abstract class Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "korisnickoIme")
    private String korisnickoIme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "lozinka")
    private String lozinka;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "punNaziv")
    private String punNaziv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumOsnivanja")
    @Temporal(TemporalType.DATE)
    private Date datumOsnivanja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registarskiBroj")
    private int registarskiBroj;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PIB")
    private long pib;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "prezime")
    private String prezime;
    @Size(max = 50)
    @Column(name = "srednjeIme")
    private String srednjeIme;
    @Column(name = "telefon")
    private BigInteger telefon;
    @Size(max = 50)
    @Column(name = "mail")
    private String mail;
    @Size(max = 100)
    @Column(name = "webSite")
    private String webSite;
    @Size(max = 40)
    @Column(name = "drustveneMrezeId")
    private String drustveneMrezeId;
    @Size(max = 50)
    @Column(name = "oblastiPoslovanja")
    private String oblastiPoslovanja;
    @Column(name = "brojZaposlenih")
    private Short brojZaposlenih;
    @Column(name = "prihodUPoslednjeTriGodine")
    private Integer prihodUPoslednjeTriGodine;
    @Column(name = "profitUPoslednjeTriGodine")
    private Integer profitUPoslednjeTriGodine;
    @ManyToMany(mappedBy = "korisnikCollection")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Vest> vestCollection;
    @ManyToMany(mappedBy = "korisnikCollection")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Obavestenje> obavestenjeCollection1;
    @ManyToMany(mappedBy = "korisnikCollection")
    private Collection<Interesovanje> interesovanjeCollection;
    @JoinTable(name = "korisnik_has_profesionalnevestine", joinColumns = {
        @JoinColumn(name = "korisnikId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "profesionalneVestineId", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Profesionalnevestine> profesionalnevestineCollection;
    @ManyToMany(mappedBy = "korisnikCollection")
    private Collection<Temadiskusija> temadiskusijaCollection;
    @ManyToMany(mappedBy = "korisnikCollection")
    private Collection<Grupa> grupaCollection;
    @OneToMany(mappedBy = "korisnikId")
    private Collection<Diskusija> diskusijaCollection;
    @OneToMany(mappedBy = "korisnikId")
    private Collection<Temadiskusija> temadiskusijaCollection1;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "korisnik")
    private Statistike statistike;
    @OneToMany(mappedBy = "korisnikId")
    
    private Collection<Vest> vestCollection1;
    @JoinColumn(name = "delatnostId", referencedColumnName = "id")
    @ManyToOne
    private Delatnost delatnostId;
    @JoinColumn(name = "ulicaId", referencedColumnName = "id")
    @ManyToOne
    private Ulica ulicaId;
    @OneToMany(mappedBy = "korisnikId")
    private Collection<Grupa> grupaCollection1;
    @ManyToMany(mappedBy = "korisnikCollection")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Oglas> oglasCollection1;
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "korisnik")
//    private Startap startap;
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "korisnik")
//    private Investitor investitor;
    
     @Column(name = "brojUlice")
    private Integer brojUlice;

    public Korisnik() {
    }

    public Korisnik(Integer id) {
        this.id = id;
    }
     public Korisnik(Integer id, String korisnickoIme) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
    }

    public Korisnik(Integer id, String korisnickoIme, String lozinka, String punNaziv, Date datumOsnivanja, int registarskiBroj, long pib, String ime, String prezime) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.punNaziv = punNaziv;
        this.datumOsnivanja = datumOsnivanja;
        this.registarskiBroj = registarskiBroj;
        this.pib = pib;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getPunNaziv() {
        return punNaziv;
    }

    public void setPunNaziv(String punNaziv) {
        this.punNaziv = punNaziv;
    }

    public Date getDatumOsnivanja() {
        return datumOsnivanja;
    }

    public void setDatumOsnivanja(Date datumOsnivanja) {
        this.datumOsnivanja = datumOsnivanja;
    }

    public int getRegistarskiBroj() {
        return registarskiBroj;
    }

    public void setRegistarskiBroj(int registarskiBroj) {
        this.registarskiBroj = registarskiBroj;
    }

    public long getPib() {
        return pib;
    }

    public void setPib(long pib) {
        this.pib = pib;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getSrednjeIme() {
        return srednjeIme;
    }

    public void setSrednjeIme(String srednjeIme) {
        this.srednjeIme = srednjeIme;
    }

    public BigInteger getTelefon() {
        return telefon;
    }

    public void setTelefon(BigInteger telefon) {
        this.telefon = telefon;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getDrustveneMrezeId() {
        return drustveneMrezeId;
    }

    public void setDrustveneMrezeId(String drustveneMrezeId) {
        this.drustveneMrezeId = drustveneMrezeId;
    }

    public String getOblastiPoslovanja() {
        return oblastiPoslovanja;
    }

    public void setOblastiPoslovanja(String oblastiPoslovanja) {
        this.oblastiPoslovanja = oblastiPoslovanja;
    }

    public Short getBrojZaposlenih() {
        return brojZaposlenih;
    }

    public void setBrojZaposlenih(Short brojZaposlenih) {
        this.brojZaposlenih = brojZaposlenih;
    }

    public Integer getPrihodUPoslednjeTriGodine() {
        return prihodUPoslednjeTriGodine;
    }

    public void setPrihodUPoslednjeTriGodine(Integer prihodUPoslednjeTriGodine) {
        this.prihodUPoslednjeTriGodine = prihodUPoslednjeTriGodine;
    }

    public Integer getProfitUPoslednjeTriGodine() {
        return profitUPoslednjeTriGodine;
    }

    public void setProfitUPoslednjeTriGodine(Integer profitUPoslednjeTriGodine) {
        this.profitUPoslednjeTriGodine = profitUPoslednjeTriGodine;
    }

    public Collection<Oglas> getOglasCollection1() {
        return oglasCollection1;
    }

    public void setOglasCollection1(Collection<Oglas> oglasCollection) {
        this.oglasCollection1 = oglasCollection;
    }

    public Integer getBrojUlice() {
        return brojUlice;
    }

    public void setBrojUlice(Integer brojUlice) {
        this.brojUlice = brojUlice;
    }

    public Collection<Obavestenje> getObavestenjeCollection1() {
        return obavestenjeCollection1;
    }

    public void setObavestenjeCollection1(Collection<Obavestenje> obavestenjeCollection) {
        this.obavestenjeCollection1 = obavestenjeCollection;
    }
  
    
    @XmlTransient
    public Collection<Vest> getVestCollection() {
        return vestCollection;
    }

    public void setVestCollection(Collection<Vest> vestCollection) {
        this.vestCollection = vestCollection;
    }

    @XmlTransient
    public Collection<Interesovanje> getInteresovanjeCollection() {
        return interesovanjeCollection;
    }

    public void setInteresovanjeCollection(Collection<Interesovanje> interesovanjeCollection) {
        this.interesovanjeCollection = interesovanjeCollection;
    }

    @XmlTransient
    public Collection<Profesionalnevestine> getProfesionalnevestineCollection() {
        return profesionalnevestineCollection;
    }

    public void setProfesionalnevestineCollection(Collection<Profesionalnevestine> profesionalnevestineCollection) {
        this.profesionalnevestineCollection = profesionalnevestineCollection;
    }

    @XmlTransient
    public Collection<Temadiskusija> getTemadiskusijaCollection() {
        return temadiskusijaCollection;
    }

    public void setTemadiskusijaCollection(Collection<Temadiskusija> temadiskusijaCollection) {
        this.temadiskusijaCollection = temadiskusijaCollection;
    }

    @XmlTransient
    public Collection<Grupa> getGrupaCollection() {
        return grupaCollection;
    }

    public void setGrupaCollection(Collection<Grupa> grupaCollection) {
        this.grupaCollection = grupaCollection;
    }

    @XmlTransient
    public Collection<Diskusija> getDiskusijaCollection() {
        return diskusijaCollection;
    }

    public void setDiskusijaCollection(Collection<Diskusija> diskusijaCollection) {
        this.diskusijaCollection = diskusijaCollection;
    }

    @XmlTransient
    public Collection<Temadiskusija> getTemadiskusijaCollection1() {
        return temadiskusijaCollection1;
    }

    public void setTemadiskusijaCollection1(Collection<Temadiskusija> temadiskusijaCollection1) {
        this.temadiskusijaCollection1 = temadiskusijaCollection1;
    }

    public Statistike getStatistike() {
        return statistike;
    }

    public void setStatistike(Statistike statistike) {
        this.statistike = statistike;
    }

    @XmlTransient
    public Collection<Vest> getVestCollection1() {
        return vestCollection1;
    }

    public void setVestCollection1(Collection<Vest> vestCollection1) {
        this.vestCollection1 = vestCollection1;
    }

    public Delatnost getDelatnostId() {
        return delatnostId;
    }

    public void setDelatnostId(Delatnost delatnostId) {
        this.delatnostId = delatnostId;
    }

    public Ulica getUlicaId() {
        return ulicaId;
    }

    public void setUlicaId(Ulica ulicaId) {
        this.ulicaId = ulicaId;
    }

    @XmlTransient
    public Collection<Grupa> getGrupaCollection1() {
        return grupaCollection1;
    }

    public void setGrupaCollection1(Collection<Grupa> grupaCollection1) {
        this.grupaCollection1 = grupaCollection1;
    }

//    public Startap getStartap() {
//        return startap;
//    }
//
//    public void setStartap(Startap startap) {
//        this.startap = startap;
//    }
//
//    public Investitor getInvestitor() {
//        return investitor;
//    }
//
//    public void setInvestitor(Investitor investitor) {
//        this.investitor = investitor;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Korisnik[ id=" + id + " ]";
    }
    
}

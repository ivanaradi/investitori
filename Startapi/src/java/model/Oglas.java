/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "oglas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oglas.findAll", query = "SELECT o FROM Oglas o")
    , @NamedQuery(name = "Oglas.findById", query = "SELECT o FROM Oglas o WHERE o.id = :id")
    , @NamedQuery(name = "Oglas.findByNaslov", query = "SELECT o FROM Oglas o WHERE o.naslov = :naslov")
    , @NamedQuery(name = "Oglas.findByTekst", query = "SELECT o FROM Oglas o WHERE o.tekst = :tekst")
    , @NamedQuery(name = "Oglas.findByDatumIVremePostavljanja", query = "SELECT o FROM Oglas o WHERE o.datumIVremePostavljanja = :datumIVremePostavljanja")
    , @NamedQuery(name = "Oglas.findByDatumIVremeIsteka", query = "SELECT o FROM Oglas o WHERE o.datumIVremeIsteka = :datumIVremeIsteka")
    , @NamedQuery(name = "Oglas.findByTipOglasa", query = "SELECT o FROM Oglas o WHERE o.tipOglasa = :tipOglasa")})
public class Oglas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "naslov")
    private String naslov;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "tekst")
    private String tekst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumIVremePostavljanja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumIVremePostavljanja = new Date();
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumIVremeIsteka")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumIVremeIsteka;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipOglasa")
    private String tipOglasa;
    @JoinColumn(name = "investitorId", referencedColumnName = "Id")
    @ManyToOne
    private Investitor investitorId;
     //novo ubaceno polje 
    @Column(name = "vidljivost")
    private short vidljivost = 1;
    @JoinTable(name = "vidljivostoglasa", joinColumns = {
        @JoinColumn(name = "oglas_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "korisnik_id", referencedColumnName = "id")})
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Korisnik> korisnikCollection;

    public Oglas() {
    }

    public Oglas(Integer id) {
        this.id = id;
    }

    public Oglas(Integer id, String naslov, String tekst, Date datumIVremePostavljanja, Date datumIVremeIsteka, String tipOglasa) {
        this.id = id;
        this.naslov = naslov;
        this.tekst = tekst;
        this.datumIVremePostavljanja = datumIVremePostavljanja;
        this.datumIVremeIsteka = datumIVremeIsteka;
        this.tipOglasa = tipOglasa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getDatumIVremePostavljanja() {
        return datumIVremePostavljanja;
    }

    public void setDatumIVremePostavljanja(Date datumIVremePostavljanja) {
        this.datumIVremePostavljanja = datumIVremePostavljanja;
    }

    public Date getDatumIVremeIsteka() {
        return datumIVremeIsteka;
    }

    public void setDatumIVremeIsteka(Date datumIVremeIsteka) {
        this.datumIVremeIsteka = datumIVremeIsteka;
    }

    public String getTipOglasa() {
        return tipOglasa;
    }

    public void setTipOglasa(String tipOglasa) {
        this.tipOglasa = tipOglasa;
    }

    public Investitor getInvestitorId() {
        return investitorId;
    }

    public void setInvestitorId(Investitor investitorId) {
        this.investitorId = investitorId;
    }

    public short getVidljivost() {
        return vidljivost;
    }

    public void setVidljivost(short vidljivost) {
        this.vidljivost = vidljivost;
    }

    public Collection<Korisnik> getKorisnikCollection() {
        return korisnikCollection;
    }

    public void setKorisnikCollection(Collection<Korisnik> korisnikCollection) {
        this.korisnikCollection = korisnikCollection;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oglas)) {
            return false;
        }
        Oglas other = (Oglas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Oglas[ id=" + id + " ]";
    }
    
}

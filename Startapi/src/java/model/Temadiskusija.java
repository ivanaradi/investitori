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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "temadiskusija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Temadiskusija.findAll", query = "SELECT t FROM Temadiskusija t")
    , @NamedQuery(name = "Temadiskusija.findById", query = "SELECT t FROM Temadiskusija t WHERE t.id = :id")
    , @NamedQuery(name = "Temadiskusija.findByNaziv", query = "SELECT t FROM Temadiskusija t WHERE t.naziv = :naziv")
    , @NamedQuery(name = "Temadiskusija.findByTekst", query = "SELECT t FROM Temadiskusija t WHERE t.tekst = :tekst")
    , @NamedQuery(name = "Temadiskusija.findByKategorija", query = "SELECT t FROM Temadiskusija t WHERE t.kategorija = :kategorija")
    , @NamedQuery(name = "Temadiskusija.findByVidljivost", query = "SELECT t FROM Temadiskusija t WHERE t.vidljivost = :vidljivost")
    , @NamedQuery(name = "Temadiskusija.findByDatumKreiranja", query = "SELECT t FROM Temadiskusija t WHERE t.datumKreiranja = :datumKreiranja")})
public class Temadiskusija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "naziv")
    private String naziv;
    @Size(max = 5000)
    @Column(name = "tekst")
    private String tekst;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "kategorija")
    private String kategorija;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "vidljivost")
    private String vidljivost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumKreiranja")
    @Temporal(TemporalType.DATE)
    private Date datumKreiranja;
    @JoinTable(name = "vidljivostdiskusije", joinColumns = {
        @JoinColumn(name = "TemaDiskusijId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "korisnikId", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Korisnik> korisnikCollection;
    @OneToMany(mappedBy = "temaDiskusijaId")
    private Collection<Diskusija> diskusijaCollection;
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id")
    @ManyToOne
    private Korisnik korisnikId;

    public Temadiskusija() {
    }

    public Temadiskusija(Integer id) {
        this.id = id;
    }

    public Temadiskusija(Integer id, String naziv, String kategorija, String vidljivost, Date datumKreiranja) {
        this.id = id;
        this.naziv = naziv;
        this.kategorija = kategorija;
        this.vidljivost = vidljivost;
        this.datumKreiranja = datumKreiranja;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getVidljivost() {
        return vidljivost;
    }

    public void setVidljivost(String vidljivost) {
        this.vidljivost = vidljivost;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    @XmlTransient
    public Collection<Korisnik> getKorisnikCollection() {
        return korisnikCollection;
    }

    public void setKorisnikCollection(Collection<Korisnik> korisnikCollection) {
        this.korisnikCollection = korisnikCollection;
    }

    @XmlTransient
    public Collection<Diskusija> getDiskusijaCollection() {
        return diskusijaCollection;
    }

    public void setDiskusijaCollection(Collection<Diskusija> diskusijaCollection) {
        this.diskusijaCollection = diskusijaCollection;
    }

    public Korisnik getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Korisnik korisnikId) {
        this.korisnikId = korisnikId;
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
        if (!(object instanceof Temadiskusija)) {
            return false;
        }
        Temadiskusija other = (Temadiskusija) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Temadiskusija[ id=" + id + " ]";
    }
    
}

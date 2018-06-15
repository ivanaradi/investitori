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
@Table(name = "kategorijavesti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kategorijavesti.findAll", query = "SELECT k FROM Kategorijavesti k")
    , @NamedQuery(name = "Kategorijavesti.findById", query = "SELECT k FROM Kategorijavesti k WHERE k.id = :id")
    , @NamedQuery(name = "Kategorijavesti.findByNaslov", query = "SELECT k FROM Kategorijavesti k WHERE k.naslov = :naslov")
    , @NamedQuery(name = "Kategorijavesti.findByDatumPocetka", query = "SELECT k FROM Kategorijavesti k WHERE k.datumPocetka = :datumPocetka")
    , @NamedQuery(name = "Kategorijavesti.findByDatumKraja", query = "SELECT k FROM Kategorijavesti k WHERE k.datumKraja = :datumKraja")})
public class Kategorijavesti implements Serializable {

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
    @Column(name = "datumPocetka")
    @Temporal(TemporalType.DATE)
    private Date datumPocetka;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumKraja")
    @Temporal(TemporalType.DATE)
    private Date datumKraja;
    @OneToMany(mappedBy = "kategorijaVestiId")
    private Collection<Vest> vestCollection;

    public Kategorijavesti() {
    }

    public Kategorijavesti(Integer id) {
        this.id = id;
    }

    public Kategorijavesti(Integer id, String naslov, Date datumPocetka, Date datumKraja) {
        this.id = id;
        this.naslov = naslov;
        this.datumPocetka = datumPocetka;
        this.datumKraja = datumKraja;
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

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public Date getDatumKraja() {
        return datumKraja;
    }

    public void setDatumKraja(Date datumKraja) {
        this.datumKraja = datumKraja;
    }

    @XmlTransient
    public Collection<Vest> getVestCollection() {
        return vestCollection;
    }

    public void setVestCollection(Collection<Vest> vestCollection) {
        this.vestCollection = vestCollection;
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
        if (!(object instanceof Kategorijavesti)) {
            return false;
        }
        Kategorijavesti other = (Kategorijavesti) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Kategorijavesti[ id=" + id + " ]";
    }
    
}

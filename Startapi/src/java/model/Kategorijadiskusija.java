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
@Table(name = "kategorijadiskusija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kategorijadiskusija.findAll", query = "SELECT k FROM Kategorijadiskusija k")
    , @NamedQuery(name = "Kategorijadiskusija.findById", query = "SELECT k FROM Kategorijadiskusija k WHERE k.id = :id")
    , @NamedQuery(name = "Kategorijadiskusija.findByNaslov", query = "SELECT k FROM Kategorijadiskusija k WHERE k.naslov = :naslov")
    , @NamedQuery(name = "Kategorijadiskusija.findByDatumPocetka", query = "SELECT k FROM Kategorijadiskusija k WHERE k.datumPocetka = :datumPocetka")
    , @NamedQuery(name = "Kategorijadiskusija.findByDatumKraja", query = "SELECT k FROM Kategorijadiskusija k WHERE k.datumKraja = :datumKraja")})
public class Kategorijadiskusija implements Serializable {

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
  //  @Size(min = 1, max = 50)
    @Column(name = "datumKraja")
     @Temporal(TemporalType.DATE)
    private Date datumKraja;
    //private String datumKraja;
    @OneToMany(mappedBy = "kategorijaDiskusijaId")
    private Collection<Diskusija> diskusijaCollection;

    public Kategorijadiskusija() {
    }

    public Kategorijadiskusija(Integer id) {
        this.id = id;
    }

//    public Kategorijadiskusija(Integer id, String naslov, Date datumPocetka, String datumKraja) {
//        this.id = id;
//        this.naslov = naslov;
//        this.datumPocetka = datumPocetka;
//        this.datumKraja = datumKraja;
//    }

    public Kategorijadiskusija(Integer id, String naslov, Date datumPocetka, Date datumKraja) {
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

//    public String getDatumKraja() {
//        return datumKraja;
//    }
//
//    public void setDatumKraja(String datumKraja) {
//        this.datumKraja = datumKraja;
//    }

    public Date getDatumKraja() {
        return datumKraja;
    }

    public void setDatumKraja(Date datumKraja) {
        this.datumKraja = datumKraja;
    }

    
    
    @XmlTransient
    public Collection<Diskusija> getDiskusijaCollection() {
        return diskusijaCollection;
    }

    public void setDiskusijaCollection(Collection<Diskusija> diskusijaCollection) {
        this.diskusijaCollection = diskusijaCollection;
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
        if (!(object instanceof Kategorijadiskusija)) {
            return false;
        }
        Kategorijadiskusija other = (Kategorijadiskusija) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Kategorijadiskusija[ id=" + id + " ]";
    }
    
}

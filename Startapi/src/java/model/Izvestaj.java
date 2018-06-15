/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "izvestaj")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Izvestaj.findAll", query = "SELECT i FROM Izvestaj i")
    , @NamedQuery(name = "Izvestaj.findById", query = "SELECT i FROM Izvestaj i WHERE i.id = :id")
    , @NamedQuery(name = "Izvestaj.findByNaziv", query = "SELECT i FROM Izvestaj i WHERE i.naziv = :naziv")
    , @NamedQuery(name = "Izvestaj.findByStavke", query = "SELECT i FROM Izvestaj i WHERE i.stavke = :stavke")
    , @NamedQuery(name = "Izvestaj.findByDatumPocetka", query = "SELECT i FROM Izvestaj i WHERE i.datumPocetka = :datumPocetka")
    , @NamedQuery(name = "Izvestaj.findByDatumKraja", query = "SELECT i FROM Izvestaj i WHERE i.datumKraja = :datumKraja")})
public class Izvestaj implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "stavke")
    private String stavke;
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

    public Izvestaj() {
    }

    public Izvestaj(Integer id) {
        this.id = id;
    }

    public Izvestaj(Integer id, String naziv, String stavke, Date datumPocetka, Date datumKraja) {
        this.id = id;
        this.naziv = naziv;
        this.stavke = stavke;
        this.datumPocetka = datumPocetka;
        this.datumKraja = datumKraja;
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

    public String getStavke() {
        return stavke;
    }

    public void setStavke(String stavke) {
        this.stavke = stavke;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Izvestaj)) {
            return false;
        }
        Izvestaj other = (Izvestaj) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Izvestaj[ id=" + id + " ]";
    }
    
}

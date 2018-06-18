/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "projekat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projekat.findAll", query = "SELECT p FROM Projekat p")
    , @NamedQuery(name = "Projekat.findById", query = "SELECT p FROM Projekat p WHERE p.id = :id")
    , @NamedQuery(name = "Projekat.findByNaziv", query = "SELECT p FROM Projekat p WHERE p.naziv = :naziv")
    , @NamedQuery(name = "Projekat.findByTrazeniIznos", query = "SELECT p FROM Projekat p WHERE p.trazeniIznos = :trazeniIznos")
    , @NamedQuery(name = "Projekat.findByStatusIntelektualneSvoine", query = "SELECT p FROM Projekat p WHERE p.statusIntelektualneSvoine = :statusIntelektualneSvoine")
    , @NamedQuery(name = "Projekat.findByPodaci", query = "SELECT p FROM Projekat p WHERE p.podaci = :podaci")})
public class Projekat implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "trazeniIznos")
    private int trazeniIznos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "statusIntelektualneSvoine")
    private short statusIntelektualneSvoine;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "podaci")
    private String podaci;  
    @JoinColumn(name = "startapId", referencedColumnName = "id")
    @ManyToOne
    private Startap startapId;

    public Projekat() {
    }

    public Projekat(Integer id) {
        this.id = id;
    }

    public Projekat(Integer id, String naziv, int trazeniIznos, short statusIntelektualneSvoine, String podaci) {
        this.id = id;
        this.naziv = naziv;
        this.trazeniIznos = trazeniIznos;
        this.statusIntelektualneSvoine = statusIntelektualneSvoine;
        this.podaci = podaci;
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

    public int getTrazeniIznos() {
        return trazeniIznos;
    }

    public void setTrazeniIznos(int trazeniIznos) {
        this.trazeniIznos = trazeniIznos;
    }

    public short getStatusIntelektualneSvoine() {
        return statusIntelektualneSvoine;
    }

    public void setStatusIntelektualneSvoine(short statusIntelektualneSvoine) {
        this.statusIntelektualneSvoine = statusIntelektualneSvoine;
    }

    public String getPodaci() {
        return podaci;
    }

    public void setPodaci(String podaci) {
        this.podaci = podaci;
    }

 

    public Startap getStartapId() {
        return startapId;
    }

    public void setStartapId(Startap startapId) {
        this.startapId = startapId;
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
        if (!(object instanceof Projekat)) {
            return false;
        }
        Projekat other = (Projekat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Projekat[ id=" + id + " ]";
    }
    
}

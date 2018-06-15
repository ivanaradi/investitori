/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "interesovanje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Interesovanje.findAll", query = "SELECT i FROM Interesovanje i")
    , @NamedQuery(name = "Interesovanje.findById", query = "SELECT i FROM Interesovanje i WHERE i.id = :id")
    , @NamedQuery(name = "Interesovanje.findByNaziv", query = "SELECT i FROM Interesovanje i WHERE i.naziv = :naziv")})
public class Interesovanje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "naziv")
    private String naziv;
    @JoinTable(name = "interesovanje_has_korisnik", joinColumns = {
        @JoinColumn(name = "interesovanjeId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "korisnikId", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Korisnik> korisnikCollection;
    @JoinColumn(name = "delatnost_id", referencedColumnName = "id")
    @ManyToOne
    private Delatnost delatnostId;

    public Interesovanje() {
    }

    public Interesovanje(Integer id) {
        this.id = id;
    }

    public Interesovanje(Integer id, String naziv) {
        this.id = id;
        this.naziv = naziv;
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

    @XmlTransient
    public Collection<Korisnik> getKorisnikCollection() {
        return korisnikCollection;
    }

    public void setKorisnikCollection(Collection<Korisnik> korisnikCollection) {
        this.korisnikCollection = korisnikCollection;
    }

    public Delatnost getDelatnostId() {
        return delatnostId;
    }

    public void setDelatnostId(Delatnost delatnostId) {
        this.delatnostId = delatnostId;
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
        if (!(object instanceof Interesovanje)) {
            return false;
        }
        Interesovanje other = (Interesovanje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Interesovanje[ id=" + id + " ]";
    }
    
}

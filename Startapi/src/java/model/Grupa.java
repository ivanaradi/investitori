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
import javax.persistence.OneToMany;
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
@Table(name = "grupa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupa.findAll", query = "SELECT g FROM Grupa g")
    , @NamedQuery(name = "Grupa.findById", query = "SELECT g FROM Grupa g WHERE g.id = :id")
    , @NamedQuery(name = "Grupa.findByNaziv", query = "SELECT g FROM Grupa g WHERE g.naziv = :naziv")
    , @NamedQuery(name = "Grupa.findByOpis", query = "SELECT g FROM Grupa g WHERE g.opis = :opis")})
public class Grupa implements Serializable {

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
    @Size(min = 1, max = 5000)
    @Column(name = "opis")
    private String opis;
    @JoinTable(name = "clanovigrupa", joinColumns = {
        @JoinColumn(name = "GrupaId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "korisnikId", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Korisnik> korisnikCollection;
    @JoinColumn(name = "korisnikId", referencedColumnName = "id")
    @ManyToOne
    private Korisnik korisnikId;
    @OneToMany(mappedBy = "grupa")
    private Collection<Obavestenje> obavestenjeCollection;
    

    public Grupa() {
    }

    public Grupa(Integer id) {
        this.id = id;
    }

    public Grupa(Integer id, String naziv, String opis) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
    }
//g.id, g.naziv, g.opis, g.korisnikId.punNaziv, g.korisnikId.id
    public Grupa(Integer id, String naziv, String opis, String punNaziv, Integer idKor){
        this.id=id;
        this.naziv= naziv;
        this.opis = opis;
        this.korisnikId.setPunNaziv(punNaziv);
        this.korisnikId.setId(idKor);
    }
    
    public Integer getId() {
        return id;
    }

    public Collection<Obavestenje> getObavestenjeCollection() {
        return obavestenjeCollection;
    }

    public void setObavestenjeCollection(Collection<Obavestenje> obavestenjeCollection) {
        this.obavestenjeCollection = obavestenjeCollection;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @XmlTransient
    public Collection<Korisnik> getKorisnikCollection() {
        return korisnikCollection;
    }

    public void setKorisnikCollection(Collection<Korisnik> korisnikCollection) {
        this.korisnikCollection = korisnikCollection;
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
        if (!(object instanceof Grupa)) {
            return false;
        }
        Grupa other = (Grupa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Grupa[ id=" + id + " ]";
    }
    
}

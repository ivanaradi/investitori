/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "pitanje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pitanje.findAll", query = "SELECT p FROM Pitanje p")
    , @NamedQuery(name = "Pitanje.findById", query = "SELECT p FROM Pitanje p WHERE p.id = :id")
    , @NamedQuery(name = "Pitanje.findByTekst", query = "SELECT p FROM Pitanje p WHERE p.tekst = :tekst")})
public class Pitanje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tekst")
    private String tekst;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pitanjeId")
    private List<Odgovor> odgovorList;
    @JoinColumn(name = "anketaId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Anketa anketaId;

    public Pitanje() {
    }

    public Pitanje(Integer id) {
        this.id = id;
    }

    public Pitanje(Integer id, String tekst) {
        this.id = id;
        this.tekst = tekst;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTekst() {
        return tekst;
    }
//a
    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    @XmlTransient
    public List<Odgovor> getOdgovorList() {
        return odgovorList;
    }

    public void setOdgovorList(List<Odgovor> odgovorList) {
        this.odgovorList = odgovorList;
    }

    public Anketa getAnketaId() {
        return anketaId;
    }

    public void setAnketaId(Anketa anketaId) {
        this.anketaId = anketaId;
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
        if (!(object instanceof Pitanje)) {
            return false;
        }
        Pitanje other = (Pitanje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paket.Pitanje[ id=" + id + " ]";
    }
    
}

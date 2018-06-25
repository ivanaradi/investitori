/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "odgovor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Odgovor.findAll", query = "SELECT o FROM Odgovor o")
    , @NamedQuery(name = "Odgovor.findById", query = "SELECT o FROM Odgovor o WHERE o.id = :id")
    , @NamedQuery(name = "Odgovor.findByTekst", query = "SELECT o FROM Odgovor o WHERE o.tekst = :tekst")})
public class Odgovor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(max = 100)
    @Column(name = "tekst")
    private String tekst;
    @JoinTable(name = "odgovor_has_startap", joinColumns = {
        @JoinColumn(name = "odgovorId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "startapId", referencedColumnName = "id")})
    @ManyToMany
    private List<Startap> startapList;
    @JoinColumn(name = "pitanjeId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pitanje pitanjeId;

    public Odgovor() {
    }

    public Odgovor(Integer id) {
        this.id = id;
    }

    public Odgovor(Integer id, String tekst) {
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

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    @XmlTransient
    public List<Startap> getStartapList() {
        return startapList;
    }

    public void setStartapList(List<Startap> startapList) {
        this.startapList = startapList;
    }

    public Pitanje getPitanjeId() {
        return pitanjeId;
    }

    public void setPitanjeId(Pitanje pitanjeId) {
        this.pitanjeId = pitanjeId;
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
        if (!(object instanceof Odgovor)) {
            return false;
        }
        Odgovor other = (Odgovor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paket.Odgovor[ id=" + id + " ]";
    }
    
}

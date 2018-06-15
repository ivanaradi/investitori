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
@Table(name = "ponudjeniodgovori")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ponudjeniodgovori.findAll", query = "SELECT p FROM Ponudjeniodgovori p")
    , @NamedQuery(name = "Ponudjeniodgovori.findByIdni", query = "SELECT p FROM Ponudjeniodgovori p WHERE p.idni = :idni")
    , @NamedQuery(name = "Ponudjeniodgovori.findByTekst", query = "SELECT p FROM Ponudjeniodgovori p WHERE p.tekst = :tekst")})
public class Ponudjeniodgovori implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idni")
    private Integer idni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tekst")
    private String tekst;

    public Ponudjeniodgovori() {
    }

    public Ponudjeniodgovori(Integer idni) {
        this.idni = idni;
    }

    public Ponudjeniodgovori(Integer idni, String tekst) {
        this.idni = idni;
        this.tekst = tekst;
    }

    public Integer getIdni() {
        return idni;
    }

    public void setIdni(Integer idni) {
        this.idni = idni;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idni != null ? idni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ponudjeniodgovori)) {
            return false;
        }
        Ponudjeniodgovori other = (Ponudjeniodgovori) object;
        if ((this.idni == null && other.idni != null) || (this.idni != null && !this.idni.equals(other.idni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Ponudjeniodgovori[ idni=" + idni + " ]";
    }
    
}

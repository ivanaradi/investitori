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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "statistike")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statistike.findAll", query = "SELECT s FROM Statistike s")
    , @NamedQuery(name = "Statistike.findById", query = "SELECT s FROM Statistike s WHERE s.id = :id")
    , @NamedQuery(name = "Statistike.findByNemamPojmaStaOvdeTreba", query = "SELECT s FROM Statistike s WHERE s.nemamPojmaStaOvdeTreba = :nemamPojmaStaOvdeTreba")})
public class Statistike implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nemamPojmaStaOvdeTreba")
    private String nemamPojmaStaOvdeTreba;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Korisnik korisnik;

    public Statistike() {
    }

    public Statistike(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNemamPojmaStaOvdeTreba() {
        return nemamPojmaStaOvdeTreba;
    }

    public void setNemamPojmaStaOvdeTreba(String nemamPojmaStaOvdeTreba) {
        this.nemamPojmaStaOvdeTreba = nemamPojmaStaOvdeTreba;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
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
        if (!(object instanceof Statistike)) {
            return false;
        }
        Statistike other = (Statistike) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Statistike[ id=" + id + " ]";
    }
    
}

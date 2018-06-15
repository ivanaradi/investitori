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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "diskusija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diskusija.findAll", query = "SELECT d FROM Diskusija d")
    , @NamedQuery(name = "Diskusija.findById", query = "SELECT d FROM Diskusija d WHERE d.id = :id")
    , @NamedQuery(name = "Diskusija.findByTekst", query = "SELECT d FROM Diskusija d WHERE d.tekst = :tekst")
    , @NamedQuery(name = "Diskusija.findByVremeObjave", query = "SELECT d FROM Diskusija d WHERE d.vremeObjave = :vremeObjave")})
public class Diskusija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 5000)
    @Column(name = "tekst")
    private String tekst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vremeObjave")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremeObjave;
    @JoinColumn(name = "TemaDiskusijaId", referencedColumnName = "id")
    @ManyToOne
    private Temadiskusija temaDiskusijaId;
    @JoinColumn(name = "kategorijaDiskusijaId", referencedColumnName = "id")
    @ManyToOne
    private Kategorijadiskusija kategorijaDiskusijaId;
    @JoinColumn(name = "korisnikId", referencedColumnName = "id")
    @ManyToOne
    private Korisnik korisnikId;

    public Diskusija() {
    }

    public Diskusija(Integer id) {
        this.id = id;
    }

    public Diskusija(Integer id, Date vremeObjave) {
        this.id = id;
        this.vremeObjave = vremeObjave;
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

    public Date getVremeObjave() {
        return vremeObjave;
    }

    public void setVremeObjave(Date vremeObjave) {
        this.vremeObjave = vremeObjave;
    }

    public Temadiskusija getTemaDiskusijaId() {
        return temaDiskusijaId;
    }

    public void setTemaDiskusijaId(Temadiskusija temaDiskusijaId) {
        this.temaDiskusijaId = temaDiskusijaId;
    }

    public Kategorijadiskusija getKategorijaDiskusijaId() {
        return kategorijaDiskusijaId;
    }

    public void setKategorijaDiskusijaId(Kategorijadiskusija kategorijaDiskusijaId) {
        this.kategorijaDiskusijaId = kategorijaDiskusijaId;
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
        if (!(object instanceof Diskusija)) {
            return false;
        }
        Diskusija other = (Diskusija) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Diskusija[ id=" + id + " ]";
    }
    
}

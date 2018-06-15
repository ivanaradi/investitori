/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "pitanja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pitanja.findAll", query = "SELECT p FROM Pitanja p")
    , @NamedQuery(name = "Pitanja.findByIdpitanja", query = "SELECT p FROM Pitanja p WHERE p.idpitanja = :idpitanja")
    , @NamedQuery(name = "Pitanja.findByTekset", query = "SELECT p FROM Pitanja p WHERE p.tekset = :tekset")})
public class Pitanja implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pitanjaIdpitanja")
    private List<Odgovor> odgovorList;

    @Size(max = 45)
    @Column(name = "ponudjeniOdgovor1")
    private String ponudjeniOdgovor1;
    @Size(max = 45)
    @Column(name = "ponudjeniOdgovor2")
    private String ponudjeniOdgovor2;
    @Size(max = 45)
    @Column(name = "ponudjeniOdgovor3")
    private String ponudjeniOdgovor3;
    @Size(max = 45)
    @Column(name = "ponudjeniOdgovor4")
    private String ponudjeniOdgovor4;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpitanja")
    private Integer idpitanja;
    @Size(max = 45)
    @Column(name = "tekset")
    private String tekset;
    @JoinColumn(name = "anketaId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Anketa anketaId;
    

    public Pitanja() {
    }

    public Pitanja(Integer idpitanja) {
        this.idpitanja = idpitanja;
    }

    public Integer getIdpitanja() {
        return idpitanja;
    }

    public void setIdpitanja(Integer idpitanja) {
        this.idpitanja = idpitanja;
    }

    public String getTekset() {
        return tekset;
    }

    public void setTekset(String tekset) {
        this.tekset = tekset;
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
        hash += (idpitanja != null ? idpitanja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pitanja)) {
            return false;
        }
        Pitanja other = (Pitanja) object;
        if ((this.idpitanja == null && other.idpitanja != null) || (this.idpitanja != null && !this.idpitanja.equals(other.idpitanja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pitanja[ idpitanja=" + idpitanja + " ]";
    }

    public String getPonudjeniOdgovor1() {
        return ponudjeniOdgovor1;
    }

    public void setPonudjeniOdgovor1(String ponudjeniOdgovor1) {
        this.ponudjeniOdgovor1 = ponudjeniOdgovor1;
    }

    public String getPonudjeniOdgovor2() {
        return ponudjeniOdgovor2;
    }

    public void setPonudjeniOdgovor2(String ponudjeniOdgovor2) {
        this.ponudjeniOdgovor2 = ponudjeniOdgovor2;
    }

    public String getPonudjeniOdgovor3() {
        return ponudjeniOdgovor3;
    }

    public void setPonudjeniOdgovor3(String ponudjeniOdgovor3) {
        this.ponudjeniOdgovor3 = ponudjeniOdgovor3;
    }

    public String getPonudjeniOdgovor4() {
        return ponudjeniOdgovor4;
    }

    public void setPonudjeniOdgovor4(String ponudjeniOdgovor4) {
        this.ponudjeniOdgovor4 = ponudjeniOdgovor4;
    }

    @XmlTransient
    public List<Odgovor> getOdgovorList() {
        return odgovorList;
    }

    public void setOdgovorList(List<Odgovor> odgovorList) {
        this.odgovorList = odgovorList;
    }
    
}

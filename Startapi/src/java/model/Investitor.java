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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "investitor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Investitor.findAll", query = "SELECT i FROM Investitor i")
    , @NamedQuery(name = "Investitor.findByLogo", query = "SELECT i FROM Investitor i WHERE i.logo = :logo")
    , @NamedQuery(name = "Investitor.findByTipInvestitora", query = "SELECT i FROM Investitor i WHERE i.tipInvestitora = :tipInvestitora")
    , @NamedQuery(name = "Investitor.findByUsluge", query = "SELECT i FROM Investitor i WHERE i.usluge = :usluge")
    , @NamedQuery(name = "Investitor.findByMinimalanIznos", query = "SELECT i FROM Investitor i WHERE i.minimalanIznos = :minimalanIznos")
    , @NamedQuery(name = "Investitor.findByMaksimalanIznos", query = "SELECT i FROM Investitor i WHERE i.maksimalanIznos = :maksimalanIznos")
    , @NamedQuery(name = "Investitor.findById", query = "SELECT i FROM Investitor i WHERE i.id = :id")})
@PrimaryKeyJoinColumn(name="id")  
public class Investitor extends Korisnik implements Serializable {
 
    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "logo")
    private String logo;
    @Size(max = 45)
    @Column(name = "tipInvestitora")
    private String tipInvestitora;
    @Size(max = 45)
    @Column(name = "usluge")
    private String usluge;
    @Column(name = "minimalanIznos")
    private Integer minimalanIznos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MaksimalanIznos")
    private int maksimalanIznos;
     @OneToMany(mappedBy = "investitorId")
    private Collection<Obavestenje> obavestenjeCollection;
    @OneToMany(mappedBy = "investitorId")
    private Collection<Oglas> oglasCollection;
    @OneToMany(mappedBy = "investitorIdId")
    private Collection<Anketa> anketaCollection;
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "Id")
//    private Integer id;
   
   
    
//    @JoinColumn(name = "Id", referencedColumnName = "id", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    private Korisnik korisnik;

    public Investitor() {
    }
    public Investitor(int id, String naziv, String logo ){
        this.setId(id);
        this.setPunNaziv(naziv);
        this.logo = logo;
    }

//    public Investitor(Integer id) {
//        this.id = id;
//    }
//
//    public Investitor(Integer id, int maksimalanIznos) {
//        this.id = id;
//        this.maksimalanIznos = maksimalanIznos;
//    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTipInvestitora() {
        return tipInvestitora;
    }

    public void setTipInvestitora(String tipInvestitora) {
        this.tipInvestitora = tipInvestitora;
    }

    public String getUsluge() {
        return usluge;
    }

    public void setUsluge(String usluge) {
        this.usluge = usluge;
    }

    public Integer getMinimalanIznos() {
        return minimalanIznos;
    }

    public void setMinimalanIznos(Integer minimalanIznos) {
        this.minimalanIznos = minimalanIznos;
    }

    public int getMaksimalanIznos() {
        return maksimalanIznos;
    }

    public void setMaksimalanIznos(int maksimalanIznos) {
        this.maksimalanIznos = maksimalanIznos;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    @XmlTransient
    public Collection<Obavestenje> getObavestenjeCollection() {
        return obavestenjeCollection;
    }

    public void setObavestenjeCollection(Collection<Obavestenje> obavestenjeCollection) {
        this.obavestenjeCollection = obavestenjeCollection;
    }

    @XmlTransient
    public Collection<Oglas> getOglasCollection() {
        return oglasCollection;
    }

    public void setOglasCollection(Collection<Oglas> oglasCollection) {
        this.oglasCollection = oglasCollection;
    }

    @XmlTransient
    public Collection<Anketa> getAnketaCollection() {
        return anketaCollection;
    }

    public void setAnketaCollection(Collection<Anketa> anketaCollection) {
        this.anketaCollection = anketaCollection;
    }

//    public Korisnik getKorisnik() {
//        return korisnik;
//    }
//
//    public void setKorisnik(Korisnik korisnik) {
//        this.korisnik = korisnik;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Investitor)) {
//            return false;
//        }
//        Investitor other = (Investitor) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "model.Investitor[ id=" + id + " ]";
//    }
//    
}

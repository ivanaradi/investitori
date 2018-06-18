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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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


@Entity
@Table(name = "startap")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Startap.findAll", query = "SELECT s FROM Startap s")
    , @NamedQuery(name = "Startap.findById", query = "SELECT s FROM Startap s WHERE s.id = :id")
    , @NamedQuery(name = "Startap.findByTrenutnaFazaFirme", query = "SELECT s FROM Startap s WHERE s.trenutnaFazaFirme = :trenutnaFazaFirme")
    , @NamedQuery(name = "Startap.findByPrethodnaInvestiranja", query = "SELECT s FROM Startap s WHERE s.prethodnaInvestiranja = :prethodnaInvestiranja")
    , @NamedQuery(name = "Startap.findByNagrade", query = "SELECT s FROM Startap s WHERE s.nagrade = :nagrade")
    , @NamedQuery(name = "Startap.findByReference", query = "SELECT s FROM Startap s WHERE s.reference = :reference")})
   @PrimaryKeyJoinColumn(name="id")  
public class Startap  extends Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "id")
//    private Integer id;
    @Size(max = 50)
    @Column(name = "trenutnaFazaFirme")
    private String trenutnaFazaFirme;
    @Size(max = 5000)
    @Column(name = "PrethodnaInvestiranja")
    private String prethodnaInvestiranja;
    @Size(max = 5000)
    @Column(name = "nagrade")
    private String nagrade;
    @Size(max = 5000)
    @Column(name = "reference")
    private String reference;
    @OneToMany(mappedBy = "startapId")
    private Collection<Projekat> projekatCollection;    
    @ManyToMany(mappedBy = "startapList")
    private List<Odgovor> odgovorList;
//    @OneToMany(mappedBy = "startapId")
//    private Collection<Odgovor> odgovorCollection;
//    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    private Korisnik korisnik;

    public Startap() {
    }

    public Startap(int id, String naziv){
        this.setId(id);
        this.setPunNaziv(naziv);
    }
     public Startap(int id, String naziv, Collection<Vest> vestKol){
        this.setId(id);
        this.setPunNaziv(naziv);
        this.setVestCollection(vestKol);
    }
    
//    public Startap(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getId() {
//        return id;
//    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getTrenutnaFazaFirme() {
        return trenutnaFazaFirme;
    }

    public void setTrenutnaFazaFirme(String trenutnaFazaFirme) {
        this.trenutnaFazaFirme = trenutnaFazaFirme;
    }

    public String getPrethodnaInvestiranja() {
        return prethodnaInvestiranja;
    }

    public void setPrethodnaInvestiranja(String prethodnaInvestiranja) {
        this.prethodnaInvestiranja = prethodnaInvestiranja;
    }

    public String getNagrade() {
        return nagrade;
    }

    public void setNagrade(String nagrade) {
        this.nagrade = nagrade;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @XmlTransient
    public Collection<Projekat> getProjekatCollection() {
        return projekatCollection;
    }

    public void setProjekatCollection(Collection<Projekat> projekatCollection) {
        this.projekatCollection = projekatCollection;
    }
    
    
    @XmlTransient
    public List<Odgovor> getOdgovorList() {
        return odgovorList;
    }

    public void setOdgovorList(List<Odgovor> odgovorList) {
        this.odgovorList = odgovorList;
    }
    

//    @XmlTransient
//    public Collection<Odgovor> getOdgovorCollection() {
//        return odgovorCollection;
//    }
//
//    public void setOdgovorCollection(Collection<Odgovor> odgovorCollection) {
//        this.odgovorCollection = odgovorCollection;
//    }

//    public Korisnik getKorisnik() {
//        return korisnik;
//    }
//
//    public void setKorisnik(Korisnik korisnik) {
//        this.korisnik = korisnik;
//    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }

//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Startap)) {
//            return false;
//        }
//        Startap other = (Startap) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "model.Startap[ id=" + id + " ]";
//    }
//    
}

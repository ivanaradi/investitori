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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "opstina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opstina.findAll", query = "SELECT o FROM Opstina o")
    , @NamedQuery(name = "Opstina.findByIdopstina", query = "SELECT o FROM Opstina o WHERE o.idopstina = :idopstina")
    , @NamedQuery(name = "Opstina.findByNaziv", query = "SELECT o FROM Opstina o WHERE o.naziv = :naziv")})
public class Opstina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idopstina")
    private Integer idopstina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "naziv")
    private String naziv;
    @JoinColumn(name = "gradid", referencedColumnName = "id")
    @ManyToOne
    private Grad gradid;
    @OneToMany(mappedBy = "opstinaIdopstina")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Ulica> ulicaCollection;

    public Opstina() {
    }

    public Opstina(Integer idopstina) {
        this.idopstina = idopstina;
    }

    public Opstina(Integer idopstina, String naziv) {
        this.idopstina = idopstina;
        this.naziv = naziv;
    }

    public Integer getIdopstina() {
        return idopstina;
    }

    public void setIdopstina(Integer idopstina) {
        this.idopstina = idopstina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Grad getGradid() {
        return gradid;
    }

    public void setGradid(Grad gradid) {
        this.gradid = gradid;
    }

    @XmlTransient
    public Collection<Ulica> getUlicaCollection() {
        return ulicaCollection;
    }

    public void setUlicaCollection(Collection<Ulica> ulicaCollection) {
        this.ulicaCollection = ulicaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idopstina != null ? idopstina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opstina)) {
            return false;
        }
        Opstina other = (Opstina) object;
        if ((this.idopstina == null && other.idopstina != null) || (this.idopstina != null && !this.idopstina.equals(other.idopstina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Opstina[ idopstina=" + idopstina + " ]";
    }
    
}

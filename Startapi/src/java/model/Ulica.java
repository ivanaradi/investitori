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

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "ulica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ulica.findAll", query = "SELECT u FROM Ulica u")
    , @NamedQuery(name = "Ulica.findById", query = "SELECT u FROM Ulica u WHERE u.id = :id")
    , @NamedQuery(name = "Ulica.findByNaziv", query = "SELECT u FROM Ulica u WHERE u.naziv = :naziv")
})
public class Ulica implements Serializable {

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
//    @Size(max = 45)
//    @Column(name = "ulicacol")
//    private String ulicacol;
    @OneToMany(mappedBy = "ulicaId")
    private Collection<Korisnik> korisnikCollection;
    @JoinColumn(name = "opstina_idopstina", referencedColumnName = "idopstina")
    @ManyToOne
    private Opstina opstinaIdopstina;

    public Ulica() {
    }

    public Ulica(String naziv) {
        this.naziv = naziv;
    }

    public Ulica(Integer id) {
        this.id = id;
    }

    public Ulica(Integer id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Integer getId() {
        return id;
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

//    public String getUlicacol() {
//        return ulicacol;
//    }
//
//    public void setUlicacol(String ulicacol) {
//        this.ulicacol = ulicacol;
//    }
    @XmlTransient
    public Collection<Korisnik> getKorisnikCollection() {
        return korisnikCollection;
    }

    public void setKorisnikCollection(Collection<Korisnik> korisnikCollection) {
        this.korisnikCollection = korisnikCollection;
    }

    public Opstina getOpstinaIdopstina() {
        return opstinaIdopstina;
    }

    public void setOpstinaIdopstina(Opstina opstinaIdopstina) {
        this.opstinaIdopstina = opstinaIdopstina;
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
        if (!(object instanceof Ulica)) {
            return false;
        }
        Ulica other = (Ulica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Ulica[ id=" + id + " ]";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@ViewScoped
public class Podatak implements Serializable {
    private int id;
    private String naziv;
    private String sediste;
    private int pib;
    private int telefon;
    private String mail;
    private String oKompaniji;
    private String oblastDelovanja;
    private String webAdresaSajta;
    private String korisnickoIme;
    private String lozinka;
    
    private boolean editMode;
    
    public boolean isEditMode(){
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSediste() {
        return sediste;
    }

    public void setSediste(String sediste) {
        this.sediste = sediste;
    }

    public int getPib() {
        return pib;
    }

    public void setPib(int pib) {
        this.pib = pib;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOKompaniji() {
        return oKompaniji;
    }

    public void setOKompaniji(String oKompaniji) {
        this.oKompaniji = oKompaniji;
    }

    public String getOblastDelovanja() {
        return oblastDelovanja;
    }

    public void setOblastDelovanja(String oblastDelovanja) {
        this.oblastDelovanja = oblastDelovanja;
    }

    public String getWebAdresaSajta() {
        return webAdresaSajta;
    }

    public void setWebAdresaSajta(String webAdresaSajta) {
        this.webAdresaSajta = webAdresaSajta;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    } 
    
    
}

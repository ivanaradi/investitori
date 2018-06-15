/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Korisnik
 */
public enum Vidljivost {
    ADMIN (1),
    SVI (2),
    INVESTITORI (3),
    STARTAPI (4),
    CUSTOM (5),
    KORISNICI (6);
    
  
    private final int sifra;

    Vidljivost(int sifra) {
        this.sifra = sifra;
    }
    
    public int getSifra() {
        return this.sifra;
    }
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@RequestScoped
public class PrebaciDatumUString {
    
    private final  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
     public  String dateToString(Date date) {
        String datum = sdf.format(date);
        return datum;
    }
}

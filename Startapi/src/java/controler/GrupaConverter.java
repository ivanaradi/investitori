/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Grupa;
import model.GrupaDAO;

/**
 *
 * @author Korisnik
 */
@FacesConverter(value = "grupaconverter")
public class GrupaConverter implements Converter{

     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String newValue) {
        Grupa grupa = GrupaDAO.dohvatiGrupuPoID(Integer.parseInt(newValue));
       
        return grupa;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String val = null;
       
            Grupa gr = (Grupa) value;
            val = Integer.toString(gr.getId());
     
        return val;
    }
    
    
    
    
}

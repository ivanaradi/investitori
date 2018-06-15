/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import static com.sun.faces.util.MessageUtils.CONVERSION_ERROR_MESSAGE_ID;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import model.Grupa;

/**
 *
 * @author Korisnik
 */
@FacesConverter(value = "grupaconverter")
public class GrupaConverter implements Converter {

//     @Override
//    public Object getAsObject(FacesContext context, UIComponent component, String newValue) {
//        Grupa grupa = GrupaDAO.dohvatiGrupuPoID(Integer.parseInt(newValue));
//       
//        return grupa;
//    }
//
//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        String val = null;
//       
//            Grupa gr = (Grupa) value;
//            val = Integer.toString(gr.getId());
//     
//        return val;
//    }
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String[] param = value.split(";");
        Grupa grupa = new Grupa(Integer.parseInt(param[0]), param[1], param[2]);

        return grupa;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String val = null;
        if (value == null) {
            return null;
        }

        try {
            Grupa gr = (Grupa) value;
            val = "" + gr.getId() + ";" + gr.getNaziv() + ";" + gr.getOpis();

        } catch (ClassCastException ce) {
            FacesMessage errMsg = new FacesMessage(CONVERSION_ERROR_MESSAGE_ID);
            FacesContext.getCurrentInstance().addMessage(null, errMsg);
            throw new ConverterException(errMsg.getSummary());
        }

        return val;
    }

}

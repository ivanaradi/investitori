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
import model.Investitor;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Korisnik
 */
@FacesConverter("investitorCon")
public class InvestitorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        Object inv = null;
        if (arg1 instanceof PickList) {
            Object dualList = ((PickList) arg1).getValue();
            DualListModel dl = (DualListModel) dualList;
            for (Object o : dl.getSource()) {
                String id = "" + ((Investitor) o).getId();
                if (arg2.equals(id)) {
                    inv = o;
                    break;
                }
            }
            if (inv == null) {
                for (Object o : dl.getTarget()) {
                    String id = "" + ((Investitor) o).getId();
                    if (arg2.equals(id)) {
                        inv = o;
                        break;
                    }
                }
            }
        }
        return inv;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        String str = "";
        if (arg2 instanceof Investitor) {
            str = "" + ((Investitor) arg2).getId();
        }
        return str;
    }
}

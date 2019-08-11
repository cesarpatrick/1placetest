package demo.application.web.convertes;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import demo.application.web.dao.PassegerDaoImpl;
import demo.application.web.entities.Passeger;

@FacesConverter(value="passegerConverter")
public class PassegerConverter  implements Converter, Serializable {

	    private static final long serialVersionUID = 1L;

	    public PassegerConverter() {
	        // TODO Auto-generated constructor stub
	    }

	    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
	        if (value != null && value.trim().length() > 0) {
	            try {
	                return new PassegerDaoImpl().findPassegerById(Integer.valueOf(value));

	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	                throw new ConverterException(
	                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Invalid passeger."));
	            }
	        } else {
	            return null;
	        }
	    }

	    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
	        if (object != null) {
	            return ((Passeger) object).getId().toString();
	        } else {
	            return null;
	        }
	    }	
}

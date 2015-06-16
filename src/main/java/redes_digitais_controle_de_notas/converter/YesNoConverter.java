package redes_digitais_controle_de_notas.converter;

import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.frameworkdemoiselle.annotation.Name;

@FacesConverter(forClass = Boolean.class)
@ManagedBean(name = "yesNoConverter")
@Named
public class YesNoConverter implements Converter {

  @Inject
  @Name("enum.yesno")
  private ResourceBundle bundle;

  @Inject
  @Name("enum.yesnoboolean")
  private ResourceBundle bundleBoolean;

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    Object result = null;
    if (value != null && value.length()>0) {
    	if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
      	result = Boolean.valueOf(bundleBoolean.getString(value.toLowerCase()));
    	} else {
    		Enumeration<String> keys = bundle.getKeys();
    		if (keys != null) {
    			while (keys.hasMoreElements()) {
    				String key = keys.nextElement();
    				String label = bundle.getString(key);
    				if (value.equals(label)) {
    	    		result = key;
    	    		break;
    				}
    			}
    		}
    	}
    }
    return result;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    String result = null;
    if (value != null) {
    	if (value instanceof Boolean) {
        try {
          result = bundleBoolean.getString(((Boolean) value).toString()); 
        } catch (Exception e) {
          result = "";
        }
    	} else {
      	result = bundle.getString(value.toString());
    	}
    }
    return result;
  }

}

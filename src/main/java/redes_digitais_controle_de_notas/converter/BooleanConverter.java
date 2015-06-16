package redes_digitais_controle_de_notas.converter;

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
@ManagedBean(name = "booleanConverter")
@Named
public class BooleanConverter implements Converter {

  @Inject
  @Name("enum.truefalse")
  private ResourceBundle bundle;

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    Object result = null;
    if (value != null && value.length()>0) {
    	result = Boolean.valueOf(bundle.getString(value));
    }
    return result;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    String result = null;
    if (value != null && value instanceof Boolean) {
      try {
        result = bundle.getString(((Boolean) value).toString()); 
      } catch (Exception e) {
        result = "";
      }
    }
    return result;
  }

}

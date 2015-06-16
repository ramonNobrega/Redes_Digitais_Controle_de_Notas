package redes_digitais_controle_de_notas.converter;

import javax.faces.convert.FacesConverter;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.Name;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import redes_digitais_controle_de_notas.domain.entity.Role;
import br.gov.frameworkdemoiselle.util.Beans;
import redes_digitais_controle_de_notas.business.entity.RoleBC;

@FacesConverter(forClass = Role.class)
@ManagedBean(name = "roleConverter")
@Named
public class RoleConverter implements Converter {

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Object result = null;
		if (value != null && value.length()>0) {
			RoleBC roleBC = Beans.getReference(RoleBC.class);
			result = roleBC.load(value);
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String result = null;
		if (value != null && value instanceof Role) {
			result = ((Role)value).getName().toString();
		}
		return result;
	}

}


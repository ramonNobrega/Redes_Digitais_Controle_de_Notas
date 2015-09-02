package redes_digitais_controle_de_notas.converter;

/* block[extraImport]*/
import javax.faces.convert.FacesConverter;
/* block[extraImport]*/
/* block[extraImport_1]*/
import javax.faces.bean.ManagedBean;
/* block[extraImport_1]*/
/* block[extraImport_2]*/
import javax.inject.Named;
/* block[extraImport_2]*/
/* block[extraImport_3]*/
import javax.faces.convert.Converter;
/* block[extraImport_3]*/
/* block[extraImport_4]*/
import javax.inject.Inject;
/* block[extraImport_4]*/
/* block[extraImport_5]*/
import br.gov.frameworkdemoiselle.annotation.Name;
/* block[extraImport_5]*/
/* block[extraImport_6]*/
import java.util.ResourceBundle;
/* block[extraImport_6]*/
/* block[extraImport_7]*/
import javax.faces.component.UIComponent;
/* block[extraImport_7]*/
/* block[extraImport_8]*/
import javax.faces.context.FacesContext;
/* block[extraImport_8]*/
/* block[extraImport_9]*/
import redes_digitais_controle_de_notas.domain.entity.Aluno;
/* block[extraImport_9]*/
/* block[extraImport_10]*/
import br.gov.frameworkdemoiselle.util.Beans;
/* block[extraImport_10]*/
/* block[extraImport_11]*/
import redes_digitais_controle_de_notas.business.entity.AlunoBC;
/* block[extraImport_11]*/

@FacesConverter(forClass = Aluno.class)
@ManagedBean(name = "alunoConverter")
@Named
public class AlunoConverter implements Converter {

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Object result = null;
		if (value != null && value.length()>0) {
			AlunoBC alunoBC = Beans.getReference(AlunoBC.class);
			result = alunoBC.load(new Long(value));
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String result = null;
		if (value != null && value instanceof Aluno) {
			java.lang.Long user = ((Aluno) value).getUser().getId();
			if(user != null){
				result = user.toString();
			}
		}
		return result;
	}

}


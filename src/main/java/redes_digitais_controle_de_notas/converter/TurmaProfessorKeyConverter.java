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
import redes_digitais_controle_de_notas.domain.entity.TurmaProfessorKey;

@FacesConverter(forClass = TurmaProfessorKey.class)
@ManagedBean(name = "turmaProfessorKeyConverter")
@Named
public class TurmaProfessorKeyConverter implements Converter {

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Object result = null;
		if (value != null && value.length()>0) {
			String[] pk = value.split(",");
			Integer turma = Integer.parseInt(pk[0]);
			Long professor = Long.parseLong(pk[1]);
			TurmaProfessorKey turmaProfessorKey = new TurmaProfessorKey(turma, professor);
			result = turmaProfessorKey;
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String result = null;
		if (value != null && value instanceof TurmaProfessorKey) {
			TurmaProfessorKey turmaProfessorKey= (TurmaProfessorKey) value;
			Integer turma = turmaProfessorKey.getTurma();
			Long professor = turmaProfessorKey.getProfessor();
			result = turma+","+professor;
		}
		return result;
	}

}


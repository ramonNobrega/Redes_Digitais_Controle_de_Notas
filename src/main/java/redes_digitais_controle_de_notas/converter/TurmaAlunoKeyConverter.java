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
import redes_digitais_controle_de_notas.domain.entity.TurmaAlunoKey;

@FacesConverter(forClass = TurmaAlunoKey.class)
@ManagedBean(name = "turmaAlunoKeyConverter")
@Named
public class TurmaAlunoKeyConverter implements Converter {

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Object result = null;
		if (value != null && value.length()>0) {
			String[] pk = value.split(",");
			Integer turma = Integer.parseInt(pk[0]);
			Long aluno = Long.parseLong(pk[1]);
			TurmaAlunoKey turmaAlunoKey = new TurmaAlunoKey(turma, aluno);
			result = turmaAlunoKey;
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String result = null;
		if (value != null && value instanceof TurmaAlunoKey) {
			TurmaAlunoKey turmaAlunoKey= (TurmaAlunoKey) value;
			Integer turma = turmaAlunoKey.getTurma();
			Long aluno = turmaAlunoKey.getAluno();
			result = turma+","+aluno;
		}
		return result;
	}

}


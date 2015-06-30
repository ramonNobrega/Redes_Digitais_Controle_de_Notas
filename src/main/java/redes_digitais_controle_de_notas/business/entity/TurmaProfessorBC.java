package redes_digitais_controle_de_notas.business.entity;

import redes_digitais_controle_de_notas.domain.entity.TurmaProfessor;
import redes_digitais_controle_de_notas.domain.entity.TurmaProfessorKey;
import redes_digitais_controle_de_notas.persistence.entity.TurmaProfessorDAO;
import java.util.*;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class TurmaProfessorBC extends DelegateCrud<TurmaProfessor, TurmaProfessorKey, TurmaProfessorDAO> {

	private static final long serialVersionUID = 1L;

	public List<TurmaProfessor> findByExample(TurmaProfessor example) {
		return getDelegate().findByExample(example);
	}

	public List<TurmaProfessor> findByCriteria(java.util.Map<String, Object> parameters) {
		return getDelegate().findByCriteria(parameters);
	}

}


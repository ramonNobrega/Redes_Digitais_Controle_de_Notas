package redes_digitais_controle_de_notas.business.entity;

import redes_digitais_controle_de_notas.domain.entity.TurmaAluno;
import redes_digitais_controle_de_notas.domain.entity.TurmaAlunoKey;
import redes_digitais_controle_de_notas.persistence.entity.TurmaAlunoDAO;
import java.util.*;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class TurmaAlunoBC extends DelegateCrud<TurmaAluno, TurmaAlunoKey, TurmaAlunoDAO> {

	private static final long serialVersionUID = 1L;

	public List<TurmaAluno> findByExample(TurmaAluno example) {
		return getDelegate().findByExample(example);
	}

	public List<TurmaAluno> findByCriteria(java.util.Map<String, Object> parameters) {
		return getDelegate().findByCriteria(parameters);
	}

}


package redes_digitais_controle_de_notas.business.entity;

import redes_digitais_controle_de_notas.domain.entity.Aluno;
import redes_digitais_controle_de_notas.persistence.entity.AlunoDAO;
import java.util.*;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class AlunoBC extends DelegateCrud<Aluno, Long, AlunoDAO> {

	private static final long serialVersionUID = 1L;

	public List<Aluno> findByExample(Aluno example) {
		return getDelegate().findByExample(example);
	}

	public List<Aluno> findByCriteria(java.util.Map<String, Object> parameters) {
		return getDelegate().findByCriteria(parameters);
	}

}


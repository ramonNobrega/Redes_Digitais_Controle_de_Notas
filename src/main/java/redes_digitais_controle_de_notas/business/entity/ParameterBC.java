package redes_digitais_controle_de_notas.business.entity;

import redes_digitais_controle_de_notas.domain.entity.Parameter;
import redes_digitais_controle_de_notas.persistence.entity.ParameterDAO;
import java.util.*;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ParameterBC extends DelegateCrud<Parameter, Long, ParameterDAO> {

	private static final long serialVersionUID = 1L;

	public List<Parameter> findByExample(Parameter example) {
		return getDelegate().findByExample(example);
	}

	public List<Parameter> findByCriteria(java.util.Map<String, Object> parameters) {
		return getDelegate().findByCriteria(parameters);
	}

	@Override
	@Transactional
	public Parameter load(Long id) {
		List<Parameter> parameters = findAll();
		Parameter parameter = new Parameter();
		if(parameters != null && ! parameters.isEmpty()){
			parameter = parameters.get(0);
		}
		return parameter;
	}

}


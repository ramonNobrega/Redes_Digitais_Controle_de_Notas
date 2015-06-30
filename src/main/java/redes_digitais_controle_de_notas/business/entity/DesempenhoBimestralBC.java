package redes_digitais_controle_de_notas.business.entity;

import redes_digitais_controle_de_notas.domain.entity.DesempenhoBimestral;
import redes_digitais_controle_de_notas.persistence.entity.DesempenhoBimestralDAO;
import java.util.*;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class DesempenhoBimestralBC extends DelegateCrud<DesempenhoBimestral, Integer, DesempenhoBimestralDAO> {

	private static final long serialVersionUID = 1L;

	public List<DesempenhoBimestral> findByExample(DesempenhoBimestral example) {
		return getDelegate().findByExample(example);
	}

	public List<DesempenhoBimestral> findByCriteria(java.util.Map<String, Object> parameters) {
		return getDelegate().findByCriteria(parameters);
	}

}


package redes_digitais_controle_de_notas.persistence.entity;

import java.util.*;
import javax.inject.Inject;
import javax.persistence.*;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

import redes_digitais_controle_de_notas.domain.entity.Role;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class RoleDAO extends JPACrud<Role, String> {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Role> findByExample(Role example) {
		return super.findByExample(example);
	}

}


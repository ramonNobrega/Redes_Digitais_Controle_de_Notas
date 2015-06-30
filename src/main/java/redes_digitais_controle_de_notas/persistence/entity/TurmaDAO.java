package redes_digitais_controle_de_notas.persistence.entity;

import java.util.*;
import javax.inject.Inject;
import javax.persistence.*;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

import redes_digitais_controle_de_notas.domain.entity.Turma;
import br.gov.frameworkdemoiselle.template.JPACrud;
import javax.persistence.criteria.*;

@PersistenceController
public class TurmaDAO extends JPACrud<Turma, Integer> {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Turma> findByExample(Turma example) {
		if (example == null) {
			example = new Turma();
		}
		return super.findByExample(example);
	}

	public List<Turma> findByCriteria(java.util.Map<String, Object> parameters) {
		if (parameters == null) {
			parameters = new java.util.HashMap<String, Object>();
		}
		CriteriaBuilder cb = super.getCriteriaBuilder();
		CriteriaQuery<Turma> q = cb.createQuery(Turma.class);
		Root<Turma> r = q.from(Turma.class);
		Predicate where = null;
		Object parameter = null; 
		parameter = parameters.get("idTurma");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<Integer> collection = (List<Integer>)parameter;
				expression = r.get("idTurma").in(collection);
			} else if (parameter instanceof Integer) {
				expression = cb.equal(r.get("idTurma"), parameter);
			}
			if (expression != null) {
				where = expression;
			}
		}
		parameter = parameters.get("nomeTurma");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<String> collection = (List<String>)parameter;
				expression = r.get("nomeTurma").in(collection);
			} else if (parameter instanceof String && ((String)parameter).length()>0) {
				expression = cb.like(r.<String>get("nomeTurma"), "%" + parameter + "%");
			}
			if (expression != null) {
				if (where == null) {
					where = expression;
				} else {
					where = cb.and(where, expression);
				}
			}
		}
		List<Turma> results = new ArrayList<Turma>();
		if (where != null) {
			q.where(where);
			results =  getEntityManager().createQuery(q).getResultList();
		}
		return results;
	}

}


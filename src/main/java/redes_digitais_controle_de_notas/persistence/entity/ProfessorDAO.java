package redes_digitais_controle_de_notas.persistence.entity;

import java.util.*;
import javax.inject.Inject;
import javax.persistence.*;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

import redes_digitais_controle_de_notas.domain.entity.Professor;
import br.gov.frameworkdemoiselle.template.JPACrud;
import javax.persistence.criteria.*;

@PersistenceController
public class ProfessorDAO extends JPACrud<Professor, Long> {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Professor> findByExample(Professor example) {
		if (example == null) {
			example = new Professor();
		}
		return super.findByExample(example);
	}

	public List<Professor> findByCriteria(java.util.Map<String, Object> parameters) {
		if (parameters == null) {
			parameters = new java.util.HashMap<String, Object>();
		}
		CriteriaBuilder cb = super.getCriteriaBuilder();
		CriteriaQuery<Professor> q = cb.createQuery(Professor.class);
		Root<Professor> r = q.from(Professor.class);
		Predicate where = null;
		Object parameter = null; 
		parameter = parameters.get("user");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<Long> collection = (List<Long>)parameter;
				expression = r.get("user").get("id").in(collection);
			} else if (parameter instanceof Long) {
				expression = cb.equal(r.get("user").get("id"), parameter);
			} else if (parameter instanceof redes_digitais_controle_de_notas.domain.entity.User) {
				expression = cb.equal(r.get("user"), parameter);
			}
			if (expression != null) {
				where = expression;
			}
		}
		parameter = parameters.get("disciplina");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<String> collection = (List<String>)parameter;
				expression = r.get("disciplina").in(collection);
			} else if (parameter instanceof String && ((String)parameter).length()>0) {
				expression = cb.like(r.<String>get("disciplina"), "%" + parameter + "%");
			}
			if (expression != null) {
				if (where == null) {
					where = expression;
				} else {
					where = cb.and(where, expression);
				}
			}
		}
		List<Professor> results = new ArrayList<Professor>();
		if (where != null) {
			q.where(where);
			results =  getEntityManager().createQuery(q).getResultList();
		}
		return results;
	}

}


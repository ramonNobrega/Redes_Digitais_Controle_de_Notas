package redes_digitais_controle_de_notas.persistence.entity;

import java.util.*;
import javax.inject.Inject;
import javax.persistence.*;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

import redes_digitais_controle_de_notas.domain.entity.TurmaProfessor;
import redes_digitais_controle_de_notas.domain.entity.TurmaProfessorKey;
import br.gov.frameworkdemoiselle.template.JPACrud;
import javax.persistence.criteria.*;

@PersistenceController
public class TurmaProfessorDAO extends JPACrud<TurmaProfessor, TurmaProfessorKey> {

	private static final long serialVersionUID = 1L;

	@Override
	public List<TurmaProfessor> findByExample(TurmaProfessor example) {
		if (example == null) {
			example = new TurmaProfessor();
		}
		return super.findByExample(example);
	}

	public List<TurmaProfessor> findByCriteria(java.util.Map<String, Object> parameters) {
		if (parameters == null) {
			parameters = new java.util.HashMap<String, Object>();
		}
		CriteriaBuilder cb = super.getCriteriaBuilder();
		CriteriaQuery<TurmaProfessor> q = cb.createQuery(TurmaProfessor.class);
		Root<TurmaProfessor> r = q.from(TurmaProfessor.class);
		Predicate where = null;
		Object parameter = null; 
		parameter = parameters.get("turma");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<Integer> collection = (List<Integer>)parameter;
				expression = r.get("turma").get("idTurma").in(collection);
			} else if (parameter instanceof Integer) {
				expression = cb.equal(r.get("turma").get("idTurma"), parameter);
			} else if (parameter instanceof redes_digitais_controle_de_notas.domain.entity.Turma) {
				expression = cb.equal(r.get("turma"), parameter);
			}
			if (expression != null) {
				where = expression;
			}
		}
		parameter = parameters.get("professor");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<Long> collection = (List<Long>)parameter;
				expression = r.get("professor").get("user").get("id").in(collection);
			} else if (parameter instanceof Long) {
				expression = cb.equal(r.get("professor").get("user").get("id"), parameter);
			} else if (parameter instanceof redes_digitais_controle_de_notas.domain.entity.Professor) {
				expression = cb.equal(r.get("professor"), parameter);
			}
			if (expression != null) {
				if (where == null) {
					where = expression;
				} else {
					where = cb.and(where, expression);
				}
			}
		}
		List<TurmaProfessor> results = new ArrayList<TurmaProfessor>();
		if (where != null) {
			q.where(where);
			results =  getEntityManager().createQuery(q).getResultList();
		}
		return results;
	}

}


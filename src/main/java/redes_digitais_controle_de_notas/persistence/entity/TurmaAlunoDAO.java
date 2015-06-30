package redes_digitais_controle_de_notas.persistence.entity;

import java.util.*;
import javax.inject.Inject;
import javax.persistence.*;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

import redes_digitais_controle_de_notas.domain.entity.TurmaAluno;
import redes_digitais_controle_de_notas.domain.entity.TurmaAlunoKey;
import br.gov.frameworkdemoiselle.template.JPACrud;
import javax.persistence.criteria.*;

@PersistenceController
public class TurmaAlunoDAO extends JPACrud<TurmaAluno, TurmaAlunoKey> {

	private static final long serialVersionUID = 1L;

	@Override
	public List<TurmaAluno> findByExample(TurmaAluno example) {
		if (example == null) {
			example = new TurmaAluno();
		}
		return super.findByExample(example);
	}

	public List<TurmaAluno> findByCriteria(java.util.Map<String, Object> parameters) {
		if (parameters == null) {
			parameters = new java.util.HashMap<String, Object>();
		}
		CriteriaBuilder cb = super.getCriteriaBuilder();
		CriteriaQuery<TurmaAluno> q = cb.createQuery(TurmaAluno.class);
		Root<TurmaAluno> r = q.from(TurmaAluno.class);
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
		parameter = parameters.get("aluno");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<Long> collection = (List<Long>)parameter;
				expression = r.get("aluno").get("user").get("id").in(collection);
			} else if (parameter instanceof Long) {
				expression = cb.equal(r.get("aluno").get("user").get("id"), parameter);
			} else if (parameter instanceof redes_digitais_controle_de_notas.domain.entity.Aluno) {
				expression = cb.equal(r.get("aluno"), parameter);
			}
			if (expression != null) {
				if (where == null) {
					where = expression;
				} else {
					where = cb.and(where, expression);
				}
			}
		}
		List<TurmaAluno> results = new ArrayList<TurmaAluno>();
		if (where != null) {
			q.where(where);
			results =  getEntityManager().createQuery(q).getResultList();
		}
		return results;
	}

}


package redes_digitais_controle_de_notas.persistence.entity;

import java.util.*;
import javax.inject.Inject;
import javax.persistence.*;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

import redes_digitais_controle_de_notas.domain.entity.Desempenho;
import br.gov.frameworkdemoiselle.template.JPACrud;
import javax.persistence.criteria.*;

@PersistenceController
public class DesempenhoDAO extends JPACrud<Desempenho, Integer> {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Desempenho> findByExample(Desempenho example) {
		if (example == null) {
			example = new Desempenho();
		}
		return super.findByExample(example);
	}

	public List<Desempenho> findByCriteria(java.util.Map<String, Object> parameters) {
		if (parameters == null) {
			parameters = new java.util.HashMap<String, Object>();
		}
		CriteriaBuilder cb = super.getCriteriaBuilder();
		CriteriaQuery<Desempenho> q = cb.createQuery(Desempenho.class);
		Root<Desempenho> r = q.from(Desempenho.class);
		Predicate where = null;
		Object parameter = null; 
		parameter = parameters.get("idDesempenho");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<Integer> collection = (List<Integer>)parameter;
				expression = r.get("idDesempenho").in(collection);
			} else if (parameter instanceof Integer) {
				expression = cb.equal(r.get("idDesempenho"), parameter);
			}
			if (expression != null) {
				where = expression;
			}
		}
		parameter = parameters.get("mediaParcial");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<Double> collection = (List<Double>)parameter;
				expression = r.get("mediaParcial").in(collection);
			} else if (parameter instanceof Double) {
				expression = cb.equal(r.get("mediaParcial"), parameter);
			}
			if (expression != null) {
				if (where == null) {
					where = expression;
				} else {
					where = cb.and(where, expression);
				}
			}
		}
		parameter = parameters.get("provaFinal");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<Double> collection = (List<Double>)parameter;
				expression = r.get("provaFinal").in(collection);
			} else if (parameter instanceof Double) {
				expression = cb.equal(r.get("provaFinal"), parameter);
			}
			if (expression != null) {
				if (where == null) {
					where = expression;
				} else {
					where = cb.and(where, expression);
				}
			}
		}
		parameter = parameters.get("mediaFinal");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<Double> collection = (List<Double>)parameter;
				expression = r.get("mediaFinal").in(collection);
			} else if (parameter instanceof Double) {
				expression = cb.equal(r.get("mediaFinal"), parameter);
			}
			if (expression != null) {
				if (where == null) {
					where = expression;
				} else {
					where = cb.and(where, expression);
				}
			}
		}
		parameter = parameters.get("situacao");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<String> collection = (List<String>)parameter;
				expression = r.get("situacao").in(collection);
			} else if (parameter instanceof String && ((String)parameter).length()>0) {
				expression = cb.like(r.<String>get("situacao"), "%" + parameter + "%");
			}
			if (expression != null) {
				if (where == null) {
					where = expression;
				} else {
					where = cb.and(where, expression);
				}
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
		List<Desempenho> results = new ArrayList<Desempenho>();
		if (where != null) {
			q.where(where);
			results =  getEntityManager().createQuery(q).getResultList();
		}
		return results;
	}

}


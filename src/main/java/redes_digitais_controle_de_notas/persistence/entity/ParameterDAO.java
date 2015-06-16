package redes_digitais_controle_de_notas.persistence.entity;

import java.util.*;
import javax.inject.Inject;
import javax.persistence.*;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

import redes_digitais_controle_de_notas.domain.entity.Parameter;
import br.gov.frameworkdemoiselle.template.JPACrud;
import javax.persistence.criteria.*;

@PersistenceController
public class ParameterDAO extends JPACrud<Parameter, Long> {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Parameter> findByExample(Parameter example) {
		if (example == null) {
			example = new Parameter();
		}
		return super.findByExample(example);
	}

	public List<Parameter> findByCriteria(java.util.Map<String, Object> parameters) {
		if (parameters == null) {
			parameters = new java.util.HashMap<String, Object>();
		}
		CriteriaBuilder cb = super.getCriteriaBuilder();
		CriteriaQuery<Parameter> q = cb.createQuery(Parameter.class);
		Root<Parameter> r = q.from(Parameter.class);
		Predicate where = null;
		Object parameter = null; 
		parameter = parameters.get("id");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<Long> collection = (List<Long>)parameter;
				expression = r.get("id").in(collection);
			} else if (parameter instanceof Long) {
				expression = cb.equal(r.get("id"), parameter);
			}
			if (expression != null) {
				where = expression;
			}
		}
		parameter = parameters.get("adminEmail");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<String> collection = (List<String>)parameter;
				expression = r.get("adminEmail").in(collection);
			} else if (parameter instanceof String) {
				expression = cb.equal(r.get("adminEmail"), parameter);
			}
			if (expression != null) {
				if (where == null) {
					where = expression;
				} else {
					where = cb.and(where, expression);
				}
			}
		}
		parameter = parameters.get("lastUpdate");
		if (parameter != null) {
			Predicate expression = null;
			if (parameter instanceof java.util.List<?>) {
				@SuppressWarnings("unchecked")
				List<Date> collection = (List<Date>)parameter;
				expression = r.get("lastUpdate").in(collection);
			} else if (parameter instanceof Date) {
				expression = cb.equal(r.get("lastUpdate"), parameter);
			}
			if (expression != null) {
				if (where == null) {
					where = expression;
				} else {
					where = cb.and(where, expression);
				}
			}
		}
		if (where != null) {
			q.where(where);
		}
		List<Parameter> results =  getEntityManager().createQuery(q).getResultList();
		return results;
	}

}


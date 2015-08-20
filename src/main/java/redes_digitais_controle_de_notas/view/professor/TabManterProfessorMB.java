package redes_digitais_controle_de_notas.view.professor;

import java.util.*;

import javax.annotation.*;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.*;
import br.gov.frameworkdemoiselle.exception.*;
import br.gov.frameworkdemoiselle.message.*;
import br.gov.frameworkdemoiselle.stereotype.*;
import br.gov.frameworkdemoiselle.template.*;
import br.gov.frameworkdemoiselle.transaction.*;
import redes_digitais_controle_de_notas.domain.entity.*;
import redes_digitais_controle_de_notas.domain.enumeration.*;
import redes_digitais_controle_de_notas.domain.view.*;
import redes_digitais_controle_de_notas.business.entity.*;
import redes_digitais_controle_de_notas.business.process.*;
import redes_digitais_controle_de_notas.constant.*;
import redes_digitais_controle_de_notas.exception.*;
import redes_digitais_controle_de_notas.security.ContextMB;
import redes_digitais_controle_de_notas.business.entity.ProfessorBC;import redes_digitais_controle_de_notas.domain.entity.Professor;


@ViewController
@PreviousView("/professor/tabManterProfessor.xhtml")
@NextView("/professor/tabManterProfessorDetail.xhtml")
public class TabManterProfessorMB extends AbstractListPageBean<Professor, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private ProfessorBC professorBC;
	
	@Inject
	private ContextMB context;
	
	private List<Professor> professorResultList;

	@Override
	protected List<Professor> handleResultList() {
		professorResultList = new ArrayList<Professor>();
		Professor professor = this.professorBC.load(new Long(context.getUser().getId()));
		professorResultList.add(professor);
		return professorResultList;
	}

	public void setProfessorResultList(List<Professor> professorResultList) {
		this.professorResultList = professorResultList;
	}
}
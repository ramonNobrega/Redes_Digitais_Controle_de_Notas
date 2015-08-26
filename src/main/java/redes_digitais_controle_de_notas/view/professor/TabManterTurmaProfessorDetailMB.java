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

import redes_digitais_controle_de_notas.business.entity.TurmaProfessorBC;
import redes_digitais_controle_de_notas.domain.entity.TurmaProfessor;
import redes_digitais_controle_de_notas.security.ContextMB;

@ViewController
@PreviousView("/professor/tabManterTurmaProfessor.xhtml")
@NextView("/professor/tabManterTurmaProfessorDetail.xhtml")
public class TabManterTurmaProfessorDetailMB extends AbstractEditPageBean<TurmaProfessor, TurmaProfessorKey> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private TurmaProfessorBC turmaProfessorBC;
	
		@Inject
		private ContextMB context;
	
	
	@Override
	@Transactional
	public String insert() {
		/* TriggerCall[edit.insert.cadastrarTurmaProfessor] */
		cadastrarTurmaProfessor();
		/* TriggerCall[edit.insert.cadastrarTurmaProfessor] */
		this.turmaProfessorBC.insert(getBean());
		messageContext.add(new DefaultMessage("{pages.msg.insertsuccess}"));
		return getPreviousView();
	}
	
	/* Trigger[edit.insert.cadastrarTurmaProfessor] */
	private void cadastrarTurmaProfessor(){
		ProfessorBC professorBC = new ProfessorBC();
		Professor professor = professorBC.load(new Long(context.getUser().getId()));
		getBean().setProfessor(professor);
		professor.getTurmaProfessores().add(getBean());
		professorBC.update(professor);
	}
	/* Trigger[edit.insert.cadastrarTurmaProfessor] */
	
	@Override
	@Transactional
	public String update() {
		this.turmaProfessorBC.update(getBean());
		messageContext.add(new DefaultMessage("{pages.msg.updatesuccess}"));
		return getPreviousView();
	}
	
	/* Button[edit.delete] */
	@Override
	@Transactional
	public String delete() {
		
		return getPreviousView();
	}
	
	/* Button[edit.delete] */
	
	@Override
	protected void handleLoad() {
		setBean(this.turmaProfessorBC.load(getId()));
	}

}

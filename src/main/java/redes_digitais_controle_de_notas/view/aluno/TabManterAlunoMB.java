package redes_digitais_controle_de_notas.view.aluno;

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

import redes_digitais_controle_de_notas.business.entity.AlunoBC;
import redes_digitais_controle_de_notas.domain.entity.Aluno;

@ViewController
@PreviousView("/aluno/tabManterAluno.xhtml")
@NextView("/aluno/tabManterAlunoDetail.xhtml")
public class TabManterAlunoMB extends AbstractListPageBean<Aluno, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private AlunoBC alunoBC;
	
	public String newRecord() {
		return getNextView();
	}
	
	@Transactional
	public String delete() {
		boolean delete = false;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long selectedId = iter.next();
			delete = getSelection().get(selectedId);
			if (delete) {
				alunoBC.delete(selectedId);
				iter.remove();
			}
		}
		if (delete) {
			messageContext.add(new DefaultMessage("{pages.msg.deletesuccess}"));
		}
		return getCurrentView();
	}
	
	@Override
	protected List<Aluno> handleResultList() {
		return this.alunoBC.findAll();
	}

}

package redes_digitais_controle_de_notas.view.system;

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
import redes_digitais_controle_de_notas.persistence.entity.RoleDAO;
import redes_digitais_controle_de_notas.domain.entity.Aluno;
import redes_digitais_controle_de_notas.business.entity.AlunoBC;

@ViewController
@PreviousView("/system/login.xhtml")
@NextView("/system/tabAluno.xhtml")
public class TabAlunoMB extends AbstractPageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	private Aluno aluno = new Aluno();
	
	public Aluno getAluno() {
		return this.aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	@Inject
	private AlunoBC alunoBC;
	
	@Transactional
	public String save() {
		/* TriggerCall[blocoAluno.save.cadastraPapel] */
		cadastraPapel();
		/* TriggerCall[blocoAluno.save.cadastraPapel] */
		this.alunoBC.update(getAluno());
		messageContext.add(new DefaultMessage("{pages.msg.updatesuccess}"));
		return getCurrentView();
	}
	
	/* Trigger[blocoAluno.save.cadastraPapel] */
	private void cadastraPapel(){
		RoleDAO roleDAO = new RoleDAO();
		//Caso o papel n\\\\\\\\\\\\\\\\u00e3o exista, crie
		Role papel = roleDAO.load("aluno");
		if(papel == null){
			papel = new Role();
			papel.setName("aluno");
		}
		getAluno().getUser().getRoles().add(papel);
	}
	
	/* Trigger[blocoAluno.save.cadastraPapel] */

}

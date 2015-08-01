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
		/* TriggerCall[blocoAluno.save.cadastraDesempenho] */
		cadastraDesempenho();
		/* TriggerCall[blocoAluno.save.cadastraDesempenho] */
		/* TriggerCall[blocoAluno.save.cadastraPapel] */
		cadastraPapel();
		/* TriggerCall[blocoAluno.save.cadastraPapel] */
		this.alunoBC.update(getAluno());
		messageContext.add(new DefaultMessage("{pages.msg.updatesuccess}"));
		return getCurrentView();
	}
	
	/* Trigger[blocoAluno.save.cadastraDesempenho] */
	private void cadastraDesempenho(){
	for (int i = 1; i <= 4; i++) {
				// Botao de insert
				DesempenhoBimestral bimestral = new DesempenhoBimestral();
				bimestral.setNumBimestre(i);
				bimestral.setAluno(getAluno());
				bimestral.setNota1(0.0);
				bimestral.setNota2(0.0);
				bimestral.setNota3(0.0);
				getAluno().getDesempenhoBimestrais().add(bimestral);
			}
			
			Desempenho desempenho = new Desempenho();
			desempenho.setMediaFinal(0.0);
			desempenho.setMediaParcial(0.0);
			desempenho.setSituacao("REPROVADO");
			desempenho.setAluno(getAluno());
			aluno.getDesempenhos().add(desempenho);
	}
	
	/* Trigger[blocoAluno.save.cadastraDesempenho] */
	
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

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
import redes_digitais_controle_de_notas.security.ContextMB;
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
	
	@Inject
	private ContextMB context;
	
	private List<Aluno> alunoResultList;

	@Override
	protected List<Aluno> handleResultList() {
		alunoResultList = new ArrayList<Aluno>();
		Aluno aluno = this.alunoBC.load(new Long(context.getUser().getId()));
		alunoResultList.add(aluno);
		return alunoResultList;
	}
	
	public void setAlunoResultList(List<Aluno> alunoResultList) {
		this.alunoResultList = alunoResultList;
	}

public String carregaNomeTurmas(Aluno aluno){
		
		String turmasDoAluno = new String();
		List<TurmaAluno> turmaAlunos = aluno.getTurmaAlunos();
		for (TurmaAluno turmaAluno : turmaAlunos) {
			if(turmasDoAluno.length()>0){
				turmasDoAluno += ", ";
			}
			turmasDoAluno += turmaAluno.getTurma().getNomeTurma();
		}
		return turmasDoAluno;
		
	}
}

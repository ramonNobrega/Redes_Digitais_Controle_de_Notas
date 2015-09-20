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

import redes_digitais_controle_de_notas.business.entity.TurmaAlunoBC;
import redes_digitais_controle_de_notas.domain.entity.TurmaAluno;
import redes_digitais_controle_de_notas.security.ContextMB;import redes_digitais_controle_de_notas.business.entity.AlunoBC;
import redes_digitais_controle_de_notas.business.entity.TurmaBC;

@ViewController
@PreviousView("/professor/tabManterTurmaAluno.xhtml")
@NextView("/professor/tabManterTurmaAlunoDetail.xhtml")
public class TabManterTurmaAlunoDetailMB extends AbstractEditPageBean<TurmaAluno, TurmaAlunoKey> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private TurmaAlunoBC turmaAlunoBC;
	
	@Inject
	private AlunoBC alunoBC;
	
	public List<Aluno> getAlunoList() {
		List<Aluno> list = alunoBC.findAll();
		if (list == null) {
			list = new ArrayList<Aluno>();
		}
		return list;
	}
	
	/* Options[edit.turma] */
	@Inject
	private TurmaBC turmaBC;
	
	public List<Turma> getTurmaList() {
			
			ProfessorBC professorBC = new ProfessorBC();
			Professor professor = professorBC.load(new Long(context.getUser().getId()));
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("professor", professor);
			List<Turma> turmaList = new ArrayList<Turma>();
			for (Turma turma : turmaBC.findAll()) {
				for (TurmaProfessor turmaProfessor : turma.getTurmaProfessores())
					if (turmaProfessor.getProfessor().getUser().getId().equals(professor.getUser().getId())){
						turmaList.add(turma);
					}
				
			}
			
			return turmaList;
	}
	/* Options[edit.turma] */
		@Inject
		private ContextMB context;
	
		@Inject
		private DesempenhoBC desempenhoBC;private TurmaAluno antigoRegistro;
	
	
	@Override
	@Transactional
	public String insert() {
		/* TriggerCall[edit.insert.cadastraDesempenho] */
		cadastraDesempenho();
		/* TriggerCall[edit.insert.cadastraDesempenho] */
		this.turmaAlunoBC.insert(getBean());
		messageContext.add(new DefaultMessage("{pages.msg.insertsuccess}"));
		return getPreviousView();
	}
	
	/* Trigger[edit.insert.cadastraDesempenho] */
	private void cadastraDesempenho(){
		ProfessorBC professorBC = new ProfessorBC();
		Professor professor = professorBC.load(new Long(context.getUser().getId()));
		boolean naoTemProf = true;
		for(Desempenho desempenho : desempenhoBC.findAll()){
					if(desempenho.getAluno().equals(getBean().getAluno()) &&
							desempenho.getProfessor().equals(professor)){
				naoTemProf = false;
			}
		}
				
		if(naoTemProf){
			for (int i = 1; i <= 4; i++) {
							
				DesempenhoBimestral bimestral = new DesempenhoBimestral();
				bimestral.setNumBimestre(i);
				bimestral.setAluno(getBean().getAluno());
				bimestral.setNota1(0.0);
				bimestral.setNota2(0.0);
				bimestral.setNota3(0.0);
				bimestral.setProfessor(professor);			getBean().getAluno().getDesempenhoBimestrais().add(bimestral);
			}
						
			Desempenho desempenho = new Desempenho();
			desempenho.setMediaFinal(0.0);
			desempenho.setMediaParcial(0.0);
			desempenho.setSituacao("REPROVADO");
			desempenho.setAluno(getBean().getAluno());
			desempenho.setProfessor(professor);
				getBean().getAluno().getDesempenhos().add(desempenho);
		}
				
	}
	
	/* Trigger[edit.insert.cadastraDesempenho] */
	
	/* Button[edit.update] */
	@Override
		@Transactional
		public String update() {
			TurmaAlunoKey turmaAlunoKey = new TurmaAlunoKey(antigoRegistro.getTurma().getIdTurma(), antigoRegistro.getAluno().getUser().getId());
			this.turmaAlunoBC.delete(turmaAlunoKey);
			this.turmaAlunoBC.update(getBean());
			messageContext.add(new DefaultMessage("{pages.msg.updatesuccess}"));
			return getPreviousView();
	}/* Button[edit.update] */
	
	@Override
	@Transactional
	public String delete() {
		TurmaAlunoKey turmaAlunoKey = new TurmaAlunoKey(getBean().getTurma().getIdTurma(), getBean().getAluno().getUser().getId());
		this.turmaAlunoBC.delete(turmaAlunoKey);
		messageContext.add(new DefaultMessage("{pages.msg.deletesuccess}"));
		return getPreviousView();
	}
	
	/* Button[edit.handleLoad] */
	@Override
		protected void handleLoad() {
			antigoRegistro = this.turmaAlunoBC.load(getId());
			setBean(new TurmaAluno());
			getBean().setAluno(antigoRegistro.getAluno());
			getBean().setTurma(antigoRegistro.getTurma());
		}
	/* Button[edit.handleLoad] */

}

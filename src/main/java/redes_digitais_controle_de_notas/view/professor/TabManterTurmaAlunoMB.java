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
import redes_digitais_controle_de_notas.security.ContextMB;
@ViewController
@PreviousView("/professor/tabManterTurmaAluno.xhtml")
@NextView("/professor/tabManterTurmaAlunoDetail.xhtml")
public class TabManterTurmaAlunoMB extends AbstractListPageBean<TurmaAluno, Object> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private TurmaAlunoBC turmaAlunoBC;
	
		@Inject
		private DesempenhoBimestralBC desempenhoBimestralBC;
		
		@Inject
		private DesempenhoBC desempenhoBC;
		
		@Inject
		private ContextMB context;
	public String newRecord() {
		return getNextView();
	}
	
	/* Button[list.delete] */
	@Transactional
	public String delete() {
		boolean delete = false;
		ProfessorBC professorBC = new ProfessorBC();
		Professor professor = professorBC.load(new Long(context.getUser().getId()));
			
		for (Iterator<Object> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			TurmaAluno turmaAlunoSelected = (TurmaAluno) iter.next();
			delete = getSelection().get(turmaAlunoSelected);
			if (delete) {
				TurmaAlunoKey turmaAlunoKey= new TurmaAlunoKey(turmaAlunoSelected.getTurma().getIdTurma(), turmaAlunoSelected.getAluno().getUser().getId());
					
				List<DesempenhoBimestral> dbList = turmaAlunoSelected.getAluno().getDesempenhoBimestrais();
				for(DesempenhoBimestral desempenho : dbList){		if(desempenho.getProfessor().getUser().getId().equals(professor.getUser().getId())){					desempenhoBimestralBC.delete(desempenho.getIdBimestre());
						}
					}
				List<Desempenho> desempenhoList = turmaAlunoSelected.getAluno().getDesempenhos();
				for(Desempenho desempenho : desempenhoList){
						if(desempenho.getProfessor().getUser().getId().equals(professor.getUser().getId())){
							desempenhoBC.delete(desempenho.getIdDesempenho());
						}
					}
					turmaAlunoBC.delete(turmaAlunoKey);
					iter.remove();
				}
			}
			if (delete) {
				messageContext.add(new DefaultMessage("{pages.msg.deletesuccess}"));
			}
			return getCurrentView();
	
	}
	/* Button[list.delete] */
	
	/* Button[list.handleResultList] */
	@Override
	protected List<TurmaAluno> handleResultList() {
		ProfessorBC professorBC = new ProfessorBC();
		Professor professor = professorBC.load(new Long(context.getUser().getId()));
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("professor", professor);
		List<TurmaAluno> turmaList = new ArrayList<TurmaAluno>();
		for (TurmaAluno turma : turmaAlunoBC.findAll()) {
			for (TurmaProfessor turmaProfessor : turma.getTurma().getTurmaProfessores()) {
				if (turmaProfessor.getProfessor().equals(professor)){
						turmaList.add(turma);
					}
				}
			}
			return turmaList;
		}
	/* Button[list.handleResultList] */

}

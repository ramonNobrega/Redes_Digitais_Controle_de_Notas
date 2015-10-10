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
public class TabManterTurmaProfessorMB extends AbstractListPageBean<TurmaProfessor, Object> {

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
	
	@Inject
	private DesempenhoBimestralBC desempenhoBimestralBC;
	
	@Inject
	private DesempenhoBC desempenhoBC;
	
	public String newRecord() {
		return getNextView();
	}
	
	/* Button[list.delete] */
	@Transactional
		public String delete() {
			boolean delete = false;
			for (Iterator<Object> iter = getSelection().keySet().iterator(); iter.hasNext();) {
				TurmaProfessor turmaProfessorSelected = (TurmaProfessor) iter.next();
				delete = getSelection().get(turmaProfessorSelected);
				if (delete) {
					TurmaProfessorKey turmaProfessorKey= new TurmaProfessorKey(turmaProfessorSelected.getTurma().getIdTurma(), turmaProfessorSelected.getProfessor().getUser().getId());
					
					List<TurmaAluno> turmaAluno = turmaProfessorSelected.getTurma().getTurmaAlunos();
					for(TurmaAluno turma : turmaAluno){
						for (DesempenhoBimestral desempenhoBimestral : turma.getAluno().getDesempenhoBimestrais()){
							if(desempenhoBimestral.getAluno().equals(turma.getAluno())){
								desempenhoBimestralBC.delete(desempenhoBimestral.getIdBimestre());
							}
						}
						for(Desempenho desempenho : turma.getAluno().getDesempenhos()){
							if(desempenho.getAluno().equals(turma.getAluno())){
								desempenhoBC.delete(desempenho.getIdDesempenho());
							}
						}
					}
					turmaProfessorBC.delete(turmaProfessorKey);
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
		protected List<TurmaProfessor> handleResultList() {
			ProfessorBC professorBC = new ProfessorBC();
			Professor professor = professorBC.load(new Long(context.getUser().getId()));
			List<TurmaProfessor> turmaList = new ArrayList<TurmaProfessor>();
			for (TurmaProfessor turma : turmaProfessorBC.findAll()) {
				if (turma.getProfessor().equals(professor))
					turmaList.add(turma);
			}
				return turmaList;
		}/* Button[list.handleResultList] */

}

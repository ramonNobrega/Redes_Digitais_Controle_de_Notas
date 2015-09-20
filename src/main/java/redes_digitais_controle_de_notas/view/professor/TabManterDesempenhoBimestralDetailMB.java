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
import redes_digitais_controle_de_notas.business.entity.DesempenhoBimestralBC;
import redes_digitais_controle_de_notas.domain.entity.DesempenhoBimestral;

@ViewController
@PreviousView("/professor/tabManterDesempenhoBimestral.xhtml")
@NextView("/professor/tabManterDesempenhoBimestralDetail.xhtml")
public class TabManterDesempenhoBimestralDetailMB extends AbstractEditPageBean<DesempenhoBimestral, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private DesempenhoBimestralBC desempenhoBimestralBC;
	
		@Inject
		private DesempenhoBC desempenhoBC;
		
		@Inject
		private ContextMB context;@Override
	@Transactional
	public String insert() {
		this.desempenhoBimestralBC.insert(getBean());
		messageContext.add(new DefaultMessage("{pages.msg.insertsuccess}"));
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		/* TriggerCall[edit.update.calculaMediaBimestral] */
		calculaMediaBimestral();
		/* TriggerCall[edit.update.calculaMediaBimestral] */
		/* TriggerCall[edit.update.calculaMediaFinal] */
		calculaMediaFinal();
		/* TriggerCall[edit.update.calculaMediaFinal] */
		this.desempenhoBimestralBC.update(getBean());
		messageContext.add(new DefaultMessage("{pages.msg.updatesuccess}"));
		return getPreviousView();
	}
	
	/* Trigger[edit.update.calculaMediaBimestral] */
	public void calculaMediaBimestral() {
				DesempenhoBimestral item = getBean();
				item.setMediaBimestre((item.getNota1() + item.getNota2() + item.getNota3())/3);
				this.desempenhoBimestralBC.update(getBean());
		}
	/* Trigger[edit.update.calculaMediaBimestral] */
	
	/* Trigger[edit.update.calculaMediaFinal] */
	public void calculaMediaFinal() {
					
					ProfessorBC professorBC = new ProfessorBC();
					Professor professor = professorBC.load(new Long(context.getUser().getId()));
					Aluno aluno = getBean().getAluno();
					HashMap<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("aluno", aluno);
					Double mediaParcial = 0.0;
					
					for (DesempenhoBimestral bimestre : desempenhoBimestralBC.findByCriteria(parameters)) {
						if(bimestre.getMediaBimestre() != null && bimestre.getProfessor().equals(professor)){
							mediaParcial += bimestre.getMediaBimestre();
						}
					}
					mediaParcial = mediaParcial / 4.0;
					for(Desempenho desempenho : desempenhoBC.findByCriteria(parameters)){
						
						if(desempenho.getProfessor().equals(professor)){	
							desempenho.setMediaParcial(mediaParcial);
							if (mediaParcial >= 7) {
								desempenho.setSituacao("APROVADO");
								desempenho.setMediaFinal(mediaParcial);
							} else if( mediaParcial < 7 && mediaParcial >= 4){
								desempenho.setSituacao("FINAL");
							}else {
								desempenho.setSituacao("REPROVADO");
								desempenho.setMediaFinal(mediaParcial);
							}
							desempenhoBC.update(desempenho);
							
						}
					}
				}
	/* Trigger[edit.update.calculaMediaFinal] */
	
	@Transactional
	public String delete() {
		this.desempenhoBimestralBC.delete(getBean().getIdBimestre());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.desempenhoBimestralBC.load(getId()));
	}

}

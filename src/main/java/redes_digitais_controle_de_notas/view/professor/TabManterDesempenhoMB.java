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

import redes_digitais_controle_de_notas.business.entity.DesempenhoBC;
import redes_digitais_controle_de_notas.domain.entity.Desempenho;
import redes_digitais_controle_de_notas.security.ContextMB;
@ViewController
@PreviousView("/professor/tabManterDesempenho.xhtml")
@NextView("/professor/tabManterDesempenhoDetail.xhtml")
public class TabManterDesempenhoMB extends AbstractListPageBean<Desempenho, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private DesempenhoBC desempenhoBC;
	
		@Inject
		private ProfessorBC professorBC;
		
		@Inject
		private ContextMB context;
	
	        private List<Desempenho> desempenhoResultList;
	/* Button[list.handleResultList] */
			@Override
			protected List<Desempenho> handleResultList() {
				
				calculaMediaParcial();
				calculaMediaFinal();
				calculaSituacao();
			
				Professor professor = professorBC.load(new Long(context.getUser().getId()));
				desempenhoResultList = new ArrayList<Desempenho>();
				for (Desempenho db : desempenhoBC.findAll()){
					if(db.getProfessor().equals(professor)){
						desempenhoResultList.add(db);					
					}
				}
					
				return desempenhoResultList;
			
			}/* Button[list.handleResultList] */
	
	/* Trigger[list.handleResultList.calculaMediaParcial] */
	public void calculaMediaParcial() {
		for (Desempenho item : desempenhoBC.findAll()) {
			desempenhoBC.load(item.getIdDesempenho()
	).getMediaParcial();
		}
	}
	
	/* Trigger[list.handleResultList.calculaMediaParcial] */
	
	/* Trigger[list.handleResultList.calculaMediaFinal] */
	public void calculaMediaFinal() {
		for (Desempenho item : desempenhoBC.findAll()) {
				
			if(item.getMediaParcial() != null && item.getProvaFinal() != null){
				if(item.getMediaParcial() >= 7){
					item.setMediaFinal(item.getMediaParcial());
					item.setProvaFinal(0.0);
				} else{
							item.setMediaFinal(item.getMediaParcial()*(0.6) + item.getProvaFinal()*(0.4));
				}
			}else{
				if(item.getMediaParcial() >= 7 || item.getMediaParcial() < 5){
					item.setMediaFinal(item.getMediaParcial());
						
				} else{
							item.setMediaFinal(item.getMediaParcial()*(0.6));
					}
				}
					
				desempenhoBC.load(item.getIdDesempenho()
	).getMediaFinal();
			}
		}
	
	/* Trigger[list.handleResultList.calculaMediaFinal] */
	
	/* Trigger[list.handleResultList.calculaSituacao] */
	public void calculaSituacao() {
		for (Desempenho item : desempenhoBC.findAll()) {
			if ((item.getMediaParcial() >= 7.0)
						|| (item.getProvaFinal() != null && item.getMediaFinal() >= 5.0)) {
				item.setSituacao("APROVADO");
			} else if ((item.getMediaParcial() >= 4.0 && item.getMediaParcial() < 7.0)
						&& item.getProvaFinal() == null) {
				item.setSituacao("FINAL");
			} else if (item.getMediaParcial() < 4.0) {
				item.setSituacao("REPROVADO");
			}
			desempenhoBC.update(item);
		}
	}
	
	/* Trigger[list.handleResultList.calculaSituacao] */

}

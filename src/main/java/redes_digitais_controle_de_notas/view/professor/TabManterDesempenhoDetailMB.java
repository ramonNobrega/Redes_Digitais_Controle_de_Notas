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

@ViewController
@PreviousView("/professor/tabManterDesempenho.xhtml")
@NextView("/professor/tabManterDesempenhoDetail.xhtml")
public class TabManterDesempenhoDetailMB extends AbstractEditPageBean<Desempenho, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private DesempenhoBC desempenhoBC;
	
	@Override
	@Transactional
	public String insert() {
		this.desempenhoBC.insert(getBean());
		messageContext.add(new DefaultMessage("{pages.msg.insertsuccess}"));
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		/* TriggerCall[edit.update.calculaMediaFinal] */
		calculaMediaFinal();
		/* TriggerCall[edit.update.calculaMediaFinal] */
		/* TriggerCall[edit.update.calculaSituacao] */
		calculaSituacao();
		/* TriggerCall[edit.update.calculaSituacao] */
		this.desempenhoBC.update(getBean());
		messageContext.add(new DefaultMessage("{pages.msg.updatesuccess}"));
		return getPreviousView();
	}
	
	/* Trigger[edit.update.calculaMediaFinal] */
	public void calculaMediaFinal() {
			
		Double media;
		Desempenho desempenho = getBean().getAluno().getDesempenhos().get(0);
		if(getBean().getMediaParcial() >= 5){
			media = getBean().getMediaParcial()*(0.6) + getBean().getProvaFinal()*(0.4);
		}else{
			media = getBean().getMediaParcial();
		}
					
		desempenho.setMediaFinal(media);
		desempenhoBC.update(desempenho);
	
	}
	/* Trigger[edit.update.calculaMediaFinal] */
	
	/* Trigger[edit.update.calculaSituacao] */
	public void calculaSituacao() {
		Desempenho desempenho = getBean().getAluno().getDesempenhos().get(0);
		String situacao = "";
		if ((getBean().getMediaFinal() >= 5.0)) {
			situacao = "APROVADO";
		}else {
			situacao = "REPROVADO";
		}
			
		desempenho.setSituacao(situacao);
		desempenhoBC.update(desempenho);
	}
	/* Trigger[edit.update.calculaSituacao] */
	
	@Override
	@Transactional
	public String delete() {
		this.desempenhoBC.delete(getBean().getIdDesempenho());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.desempenhoBC.load(getId()));
	}

}

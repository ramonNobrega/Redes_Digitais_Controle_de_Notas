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

import redes_digitais_controle_de_notas.business.entity.DesempenhoBC;

@ViewController
@PreviousView("/aluno/tabDesempenhoAluno.xhtml")
@NextView("/aluno/tabDesempenhoAluno.xhtml")
public class TabDesempenhoAlunoMB extends AbstractPageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private DesempenhoBC desempenhoBC;
	
	private List<Desempenho> desempenhoResultList;
	
	public List<Desempenho> getDesempenhoResultList() {
		desempenhoResultList = this.desempenhoBC.findAll();
		return desempenhoResultList;
	}
	
	public void setDesempenhoResultList(List<Desempenho> desempenhoResultList) {
		this.desempenhoResultList = desempenhoResultList;
	}

}

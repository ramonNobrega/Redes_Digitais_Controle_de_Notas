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

import redes_digitais_controle_de_notas.security.ContextMB;import redes_digitais_controle_de_notas.business.entity.DesempenhoBimestralBC;

@ViewController
@PreviousView("/aluno/tabDesempenhoBimestralAluno.xhtml")
@NextView("/aluno/tabDesempenhoBimestralAluno.xhtml")
public class TabDesempenhoBimestralAlunoMB extends AbstractPageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	/* DatasetCode[desempenhoBimestralblock] */
	@Inject
	private DesempenhoBimestralBC desempenhoBimestralBC;
	
	private List<DesempenhoBimestral> desempenhoBimestralResultList;
	
	@Inject
	private ContextMB context;
	
	public List<DesempenhoBimestral> getDesempenhoBimestralResultList() {
		AlunoBC alunoBc = new AlunoBC();
		Aluno aluno = alunoBc.load(new Long(context.getUser().getId()));
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("aluno", aluno);
		desempenhoBimestralResultList = desempenhoBimestralBC.findByCriteria(parameters);
		return desempenhoBimestralResultList;
	}
	
	public void calculaMediaBimestral() {
		for (DesempenhoBimestral item : desempenhoBimestralBC.findAll()) {
			item.setMediaBimestre((item.getNota1() + item.getNota2() + item.getNota3())/3);
		}
	}
	
	public void setDesempenhoBimestralResultList(List<DesempenhoBimestral> desempenhoBimestralResultList) {
		this.desempenhoBimestralResultList = desempenhoBimestralResultList;
	}
	/* DatasetCode[desempenhoBimestralblock] */

}

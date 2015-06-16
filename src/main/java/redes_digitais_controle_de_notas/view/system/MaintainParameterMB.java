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

import br.gov.frameworkdemoiselle.security.RequiredRole;
import redes_digitais_controle_de_notas.domain.entity.Parameter;
import redes_digitais_controle_de_notas.business.entity.ParameterBC;

@ViewController
@PreviousView("/system/home.xhtml")
@NextView("/system/maintainParameter.xhtml")
@RequiredRole({"admin","auditor"})
public class MaintainParameterMB extends AbstractPageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	private Parameter parameter = new Parameter();
	
	public Parameter getParameter() {
		return this.parameter;
	}
	
	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
	
	@Inject
	private ParameterBC parameterBC;
	
	@PostConstruct
	public void load() {
		/* TriggerCall[edit.load.tg_load] */
		setParameter(this.parameterBC.load(1L));
		/* TriggerCall[edit.load.tg_load] */
	}
	
	@RequiredRole("admin")
	@Transactional
	public String update() {
		this.parameterBC.update(getParameter());
		messageContext.add(new DefaultMessage("{pages.msg.updatesuccess}"));
		return getCurrentView();
	}

}

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

import redes_digitais_controle_de_notas.business.entity.RoleBC;
import redes_digitais_controle_de_notas.domain.entity.Role;

@ViewController
@PreviousView("/system/maintainRole.xhtml")
@NextView("/system/maintainRoleEdit.xhtml")
public class MaintainRoleEditMB extends AbstractEditPageBean<Role, String> {
 
	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private RoleBC roleBC;
	
	@Override
	@Transactional
	public String insert() {
		this.roleBC.insert(getBean());
		messageContext.add(new DefaultMessage("{pages.msg.insertsuccess}"));
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.roleBC.update(getBean());
		messageContext.add(new DefaultMessage("{pages.msg.updatesuccess}"));
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String delete() {
		this.roleBC.delete(getBean().getName());
		messageContext.add(new DefaultMessage("{pages.msg.deletesuccess}"));
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.roleBC.load(getId()));
	}

}

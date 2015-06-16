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

import redes_digitais_controle_de_notas.security.Credential;
import br.gov.frameworkdemoiselle.security.SecurityContext;

@ViewController
@PreviousView("/system/login.xhtml")
@NextView("/system/home.xhtml")
public class LoginMB extends AbstractPageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private Credential credential;
	
	public Credential getCredential() {
		return this.credential;
	}
	
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	
	@Inject
	private SecurityContext context;
	
	public SecurityContext getContext() {
		return this.context;
	}
	
	public void setContext(SecurityContext context) {
		this.context = context;
	}
	
	/* Button[login.login] */
	public String login() {
	  context.login();
	  if (!context.isLoggedIn()) {
	    messageContext.add("{login.app.loginfail}", SeverityType.ERROR);
	    return getCurrentView();
	  }
	  return getNextView();
	}
	/* Button[login.login] */
	
	public void logout() {
		/* TriggerCall[login.logout.tg_logout] */
		context.logout();
		/* TriggerCall[login.logout.tg_logout] */
	}

}

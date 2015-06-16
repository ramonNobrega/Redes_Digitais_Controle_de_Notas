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

import redes_digitais_controle_de_notas.business.process.ResetPasswordBC;
import br.gov.frameworkdemoiselle.util.Parameter;

@ViewController
@PreviousView("/index.xhtml")
@NextView("/index.xhtml")
public class ResetPasswordMB extends AbstractPageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private ResetPasswordBC resetPasswordBC;
	
	@Inject
	private Parameter<String> email;
	
	public String getEmail() {
		String value = null;
		if (email != null) {
			value = email.getValue();
		}
		return value;
	}
	
	public void setEmail(String email) {
		if (email != null) {
			this.email.setValue(email);
		}
	}
	
	@Inject
	private Parameter<String> token;
	
	public String getToken() {
		String value = null;
		if (token != null) {
			value = token.getValue();
		}
		return value;
	}
	
	public void setToken(String token) {
		if (token != null) {
			this.token.setValue(token);
		}
	}
	
	/* Button[edit.sendResetPasswordConfirmation] */
	public String sendResetPasswordConfirmation() {
		try {
			javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String protocol = request.getProtocol();
			if (protocol.indexOf("HTTPS") > -1) {
				protocol = "https";
			} else {
				protocol = "http";
			}
			String contextPath = protocol + ":////" + request.getServerName() + ":" + request.getLocalPort() + request.getServletContext().getContextPath();
			this.resetPasswordBC.sendResetPasswordConfirmation(getEmail(), contextPath);
		} catch (Exception e) {
			messageContext.add("{pages.msg.error}", SeverityType.ERROR, e.getMessage());
			return getCurrentView();
		}
		return getNextView();
	}
	/* Button[edit.sendResetPasswordConfirmation] */
	
	/* Button[edit.confirmResetPassword] */
	public String confirmResetPassword() {
		try {
			if (getToken().equals(getEmail().hashCode() + "")) {
				this.resetPasswordBC.resetPassword(getEmail());
			} else {
				messageContext.add("{validation.invalidemail}", SeverityType.ERROR);
			}
		} catch (Exception e) {
			messageContext.add("{pages.msg.error}", SeverityType.ERROR, e.getMessage());
		}
		return getNextView();
	}
	/* Button[edit.confirmResetPassword] */

}

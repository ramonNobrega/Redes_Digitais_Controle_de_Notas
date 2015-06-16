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

import redes_digitais_controle_de_notas.domain.entity.User;
import redes_digitais_controle_de_notas.business.entity.UserBC;

@ViewController
@PreviousView("/system/home.xhtml")
@NextView("/system/home.xhtml")
public class RegisterUserMB extends AbstractPageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	private User user = new User();
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Inject
	private UserBC userBC;
	
	private String email;
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	private String password;
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	private String confirmPassword;
	
	public String getConfirmPassword() {
		return this.confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	/* Button[edit.registerNewUser] */
	
		@Transactional
		public String registerNewUser() {
			try {
				User user = new User();
				user.setEmail(email);
				List<User> userList = userBC.findByExample(user);
				if (userList != null && userList.size() > 0) {
					messageContext.add("{system.maintainuser.validation.duplicatedemail}", SeverityType.ERROR);
					return getCurrentView();
				}
				userBC.registerNewUser(getUser(), email, password);
				messageContext.add(new DefaultMessage("{pages.msg.insertsuccess}"));
			} catch (Exception e) {
				messageContext.add("{pages.msg.error}", SeverityType.ERROR, e.getMessage());
			}
			return getCurrentView();
		}/* Button[edit.registerNewUser] */

}

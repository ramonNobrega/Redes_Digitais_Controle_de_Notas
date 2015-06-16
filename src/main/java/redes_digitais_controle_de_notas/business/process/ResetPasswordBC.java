package redes_digitais_controle_de_notas.business.process;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import java.io.Serializable;
import redes_digitais_controle_de_notas.domain.entity.User;
import redes_digitais_controle_de_notas.business.entity.UserBC;
import redes_digitais_controle_de_notas.domain.entity.Parameter;
import redes_digitais_controle_de_notas.business.entity.ParameterBC;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.Name;
import java.util.ResourceBundle;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.message.SeverityType;
import java.util.List;
import java.io.File;
import br.gov.frameworkdemoiselle.mail.Mail;
import br.gov.frameworkdemoiselle.mail.MailException;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import javax.mail.SendFailedException;

@BusinessController
public class ResetPasswordBC implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	@Inject
	private MessageContext messageContext;

	@Inject
	private Mail mail;

	@ExceptionHandler
	public void exceptionHandler(MailException exception) {
		messageContext.add(new DefaultMessage(bundle.getString("infra.msg.sendmailerror"), SeverityType.ERROR, exception));
	}

	@ExceptionHandler
	public void exceptionHandler(SendFailedException exception) {
		messageContext.add(new DefaultMessage(bundle.getString("infra.msg.sendmailerror"), SeverityType.ERROR, exception));
	}

	public void sendConfirmationEmail(String to, String from, String bodyText, String bodyHtml, String subject, String bcc, String cc, String deliveryReceipt, String readReceipt, List<File> files) {
		mail.to(to);
		mail.from(from);
		if (bodyText != null) {
			mail.body().text(bodyText);
		}
		if (bodyHtml != null) {
			mail.body().html(bodyHtml);
		}
		mail.subject(subject);
		if (bcc != null) {
			mail.bcc(bcc);
		}
		if (cc != null) {
			mail.cc(cc);
		}
		if (deliveryReceipt != null) {
			mail.deliveryReceipt(deliveryReceipt);
		}
		if (readReceipt != null) {
			mail.readReceipt(readReceipt);
		}
		if (files != null) {
			for (File file: files) {
				mail.attach().file(file).attachment();
			}
		}
		mail.importance().high();
		mail.send();
	}

	@Inject
	private UserBC userBC;

	@Inject
	private ParameterBC parameterBC;

	@Transactional
	public void sendResetPasswordConfirmation(String email, String contextPath) {
		if (email == null) {
			messageContext.add(bundle.getString("system.resetpasswordbc.msg.emailnotinformed"), SeverityType.ERROR);
		}
		User userExample = new User();
		userExample.setEmail(email);
		List<User> userList = userBC.findByExample(userExample);
		if (userList == null || userList.size()==0) {
			messageContext.add(bundle.getString("system.resetpasswordbc.msg.emailvalidation"), SeverityType.ERROR);
			return;
		}
		Parameter parameter = null;
		List<Parameter> listaParameters = parameterBC.findAll();
		if (listaParameters != null && listaParameters.size()>0) {
			parameter = listaParameters.get(0);
		}
		String subject = bundle.getString("system.resetpasswordbc.msg.confirm");
		String href = contextPath + "/system/resetPassword.jsf?email=" + email + "&token=" + email.hashCode(); 
		String bodyHtml = bundle.getString("system.resetpasswordbc.msg.requestconfirmation") + " " + email + "\".</br></br></p><a href=\"" + href + "\">" + bundle.getString("system.resetpasswordbc.msg.clickforconfirming") + "</a>";
		sendConfirmationEmail(email, parameter.getAdminEmail(), null, bodyHtml, subject, null, null, null, null, null);
		messageContext.add(new DefaultMessage(bundle.getString("system.resetpasswordbc.msg.successrequest")));
	}

	@Transactional
	public void resetPassword(String email) throws Exception {
		if (email == null) {
			messageContext.add(bundle.getString("system.resetpasswordbc.msg.emailnotinformed"), SeverityType.ERROR);
		}
		User userExample = new User();
		userExample.setEmail(email);
		List<User> userList = userBC.findByExample(userExample);
		if (userList == null || userList.size()==0) {
			messageContext.add(bundle.getString("system.resetpasswordbc.msg.emailvalidation"), SeverityType.ERROR);
			return;
		}
		User user = userList.get(0);
		if (user != null) {
			String newPassword = generateRandomPassword();
			user.setPassword(newPassword);
			userBC.update(user);
			Parameter parameter = null;
			List<Parameter> listaParameters = parameterBC.findAll();
			if (listaParameters != null && listaParameters.size()>0) {
				parameter = listaParameters.get(0);
			}
			String subject = bundle.getString("system.resetpasswordbc.msg.newpassword");
			String bodyHtml = bundle.getString("system.resetpasswordbc.msg.newpasswordmessage") + " \"" + email + "\" &#233; \"" + newPassword + "\".";
			sendConfirmationEmail(email, parameter.getAdminEmail(), null, bodyHtml, subject, null, null, null, null, null);
		}
	}

	private String generateRandomPassword() {
		StringBuffer sb = new StringBuffer();
		int random;
		boolean v = false;
		for(int i = 0; i<6; i++) {
			v = false;
			do {
				random = (int) (Math.random() * 122);
				if (random >= 48 && random <= 57) {
					v = true;
				} else if (random >= 97 && random <= 122) {
					v = true;
				} else if (random >= 65 && random <= 90) {
					v = true;
				}
			} while (!v);
			sb.append((char) random);
		}
		return sb.toString().toLowerCase();
	}

}


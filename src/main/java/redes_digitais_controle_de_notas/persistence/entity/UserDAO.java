package redes_digitais_controle_de_notas.persistence.entity;

import java.util.*;
import javax.inject.Inject;
import javax.persistence.*;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

import redes_digitais_controle_de_notas.domain.entity.User;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.message.SeverityType;

@PersistenceController
public class UserDAO extends JPACrud<User, Long> {

	private static final long serialVersionUID = 1L;

	@Override
	public List<User> findByExample(User example) {
		return super.findByExample(example);
	}

	@Inject
	private MessageContext messageContext;

	@Override
	@br.gov.frameworkdemoiselle.transaction.Transactional
	public void insert(User entity) {
		User userExample = new User();
		userExample.setEmail(entity.getEmail());
		List<User> userList = this.findByExample(userExample);
		if (userList != null && userList.size()>0) {
			messageContext.add("{system.maintainuser.validation.duplicatedemail}", SeverityType.ERROR);
			return;
		}
		String password = entity.getPassword();
		if (password != null && password.length() < 16) {
			entity.setPassword(redes_digitais_controle_de_notas.security.SecurityHelper.cryptMD5(password));
		}
		super.insert(entity);
	}
	

	@Override
	@br.gov.frameworkdemoiselle.transaction.Transactional
	public void update(User entity) {
		User userExample = new User();
		userExample.setEmail(entity.getEmail());
		List<User> userList = this.findByExample(userExample);
		if (userList != null && userList.size()>0 && !userList.get(0).getId().equals(entity.getId())) {
			messageContext.add("{system.maintainuser.validation.duplicatedemail}", SeverityType.ERROR);
			return;
		}
		String password = entity.getPassword();
		if (password != null && password.length() < 16) {
			entity.setPassword(redes_digitais_controle_de_notas.security.SecurityHelper.cryptMD5(password));
		}
		super.update(entity);
	}
	
}


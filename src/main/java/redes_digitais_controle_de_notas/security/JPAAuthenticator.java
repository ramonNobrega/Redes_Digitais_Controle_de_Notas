package redes_digitais_controle_de_notas.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import redes_digitais_controle_de_notas.domain.entity.Role;
import redes_digitais_controle_de_notas.persistence.entity.UserDAO;
import br.gov.frameworkdemoiselle.security.User;

public class JPAAuthenticator implements br.gov.frameworkdemoiselle.security.Authenticator {

  private static final long serialVersionUID = 1L;

  @Inject
  private Credential credential;

  @Inject
  private UserDAO userDAO;

  @Override
  public boolean authenticate() {
    String username = credential.getUsername();
    String password = credential.getPassword();
    redes_digitais_controle_de_notas.domain.entity.User userEntity = authenticate(username, password);
    if (userEntity == null || userEntity.getId() == null) {
      return false;
    }
    User user = credential.getUser();
    if (user == null) {
	    user = new redes_digitais_controle_de_notas.security.User();
      credential.setUser(user);
    }
    ((redes_digitais_controle_de_notas.security.User)user).setId(userEntity.getId().toString());
    ((redes_digitais_controle_de_notas.security.User)user).setLogin(credential.getUsername());
    user.setAttribute(redes_digitais_controle_de_notas.security.User.NAME_ATTRIBUTE, userEntity.getName());
    Set<String> roles = new HashSet<String>();
    List<Role> rolesEntity = userEntity.getRoles();
    if (rolesEntity != null) {
      for (Role role: rolesEntity) {
        roles.add(role.getName());
      }
    }
    user.setAttribute(redes_digitais_controle_de_notas.security.User.ROLES_ATTRIBUTE, roles);
    /*
    Map<String, String> resourceMap = new HashMap<String, String>();
    resourceMap.put("role", "find");
    user.putAllAttribute(redes_digitais_controle_de_notas.security.User.RESOURCES_ATTRIBUTE, resourceMap);
    */
    return true;
  }

  private redes_digitais_controle_de_notas.domain.entity.User authenticate(String username, String password) {
    redes_digitais_controle_de_notas.domain.entity.User user = null;
    redes_digitais_controle_de_notas.domain.entity.User userExample = new redes_digitais_controle_de_notas.domain.entity.User();
    userExample.setEmail(username);
    List<redes_digitais_controle_de_notas.domain.entity.User> userList = userDAO.findByExample(userExample);
    if (userList == null || userList.size()==0) {
      return null;
    }
    user = userList.get(0);
    String currentPassword = user.getPassword();
    if (currentPassword != null) {
      if (!currentPassword.equals(redes_digitais_controle_de_notas.security.SecurityHelper.cryptMD5(password))) {
        return null;
      }
    }
    return user;
  }

  @Override
  public void unAuthenticate() {
    credential.clear();
  }

  @Override
  public User getUser() {
		return credential.getUser();
  }

}

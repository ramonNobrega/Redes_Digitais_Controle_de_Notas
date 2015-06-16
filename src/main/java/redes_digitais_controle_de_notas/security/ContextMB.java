package redes_digitais_controle_de_notas.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.frameworkdemoiselle.security.SecurityContext;

@Named
@SessionScoped
public class ContextMB implements Serializable {

	private static final long serialVersionUID = 1L;

  @Inject
	private SecurityContext securityContext;
	
	private User user;

	public User getUser() {
    if (securityContext.isLoggedIn() && securityContext.getUser() != null && securityContext.getUser() instanceof User) {
      user = ((User)(securityContext.getUser()));
    }
		return user;
	}

	public String getUsername() {
		try {
			return getUser().getId();
		} catch (Exception e) {
			return null;
		}
	}
	
	private Map<String, Object> session = new HashMap<String, Object>();

  public void put(String key, Object value) {
      session.put(key, value);
  }

  public Object get(String key) {
    return session.get(key);
  }
	
  public Object remove(String key) {
    return session.remove(key);
  }
	
  public void clear() {
    session.clear();
  }
  

}

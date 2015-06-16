package redes_digitais_controle_de_notas.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.gov.frameworkdemoiselle.security.User;

@SessionScoped
@Named
public class Credential implements Serializable {

  private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private User user;

	public void clear() {
		this.username = null;
		this.password = null;
		this.user = null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username != null)
			username = username.toLowerCase();
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

package redes_digitais_controle_de_notas.security;

import java.io.Serializable;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(prefix = "frameworkdemoiselle.security")
public class AuthFilterConfig implements Serializable {

  private static final long serialVersionUID = 1L;

  @Name("enabled")
  private boolean enabled = true;

  @Name("login.page")
  private String loginPage = "/login";

  @Name("publicresources")
  private String publicResources = "";

  public String getLoginPage() {
    return loginPage;
  }

  public boolean getEnabled() {
    return enabled;
  }

  public String getPublicResources() {
    return publicResources;
  }

  public void setPublicResources(String publicResources) {
    this.publicResources = publicResources;
  }

}
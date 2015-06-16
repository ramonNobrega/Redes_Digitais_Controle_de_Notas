package redes_digitais_controle_de_notas.security;

import java.util.Map;
import java.util.Set;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.SecurityContext;

public class Authorizer implements br.gov.frameworkdemoiselle.security.Authorizer {

  private static final long serialVersionUID = 1L;
  
  @Inject
  private SecurityContext securityContext;
  
  @SuppressWarnings("unchecked")
  @Override
  public boolean hasRole(String roles) {
    try {
      Set<String> roleSet = ((Set<String>) securityContext.getUser().getAttribute(redes_digitais_controle_de_notas.security.User.ROLES_ATTRIBUTE));
      if (roleSet != null) {
        String[] rolesArray = new String[]{roles};
        if (roles.indexOf(",")!=-1) {
          rolesArray = roles.split(",");
        }
        for (String role : rolesArray) {
          if (roleSet.contains(role)) {
            return true;
          }
        }
      }
    } catch (Exception e) {
      // Ignore
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean hasPermission(String resource, String operation) {
    try {
      Map<String, String> resourceMap = ((Map<String, String>) securityContext.getUser().getAttribute(redes_digitais_controle_de_notas.security.User.RESOURCES_ATTRIBUTE));
      if (resource.equals(resourceMap.get(operation))) {
        return true;
      }
    } catch (Exception e) {
      // Ignore
    }
    return false;
  }

}

package redes_digitais_controle_de_notas.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.gov.frameworkdemoiselle.internal.configuration.SecurityConfig;
import br.gov.frameworkdemoiselle.security.SecurityContext;

@WebFilter(urlPatterns = { "*.jsf" }, dispatcherTypes = { DispatcherType.FORWARD, DispatcherType.REQUEST })
public class AuthFilter implements Filter {

  @Inject
  private SecurityConfig securityConfig;

  @Inject
	private SecurityContext securityContext;

  @Inject
  private AuthFilterConfig config;

	private HttpServletRequest request;

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (!securityConfig.isEnabled()) {
			chain.doFilter(request, response);
			return;
		}
		this.request = (HttpServletRequest) request;
		String url = this.request.getRequestURI().replaceAll("^/.+?/", "/");
		if (securityContext.isLoggedIn() || isPublicURL(url)) {
			chain.doFilter(request, response);
		} else {
			redirect(response, getContext() + config.getLoginPage());
		}
	}

	private boolean isPublicURL(String url) {
	  if (url.indexOf("javax.faces.resource")!=-1) {
      return true;
	  }
	  if (url.equals(config.getLoginPage())) {
      return true;
	  }
	  String publicResources = config.getPublicResources();
    if (publicResources.indexOf(url)!=-1) {
      return true;
    }
		return false;
	}

	private void redirect(ServletResponse response, String url) throws IOException {
		((HttpServletResponse) response).sendRedirect(url);
	}

	private String getContext() {
		if (request.getServletContext() == null) {
			return null;
		}
		return request.getServletContext().getContextPath();
	}

}

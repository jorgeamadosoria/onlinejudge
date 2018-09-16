package cu.uci.coj.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import cu.uci.coj.utils.COJContextLoaderListener;

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addListener(new COJContextLoaderListener());
		servletContext.addListener(new HttpSessionEventPublisher());
		super.onStartup(servletContext);
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfiguration.class,SecurityConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfiguration.class};
	}

	@Override
	protected Filter[] getServletFilters() {
		DelegatingFilterProxy bean = new DelegatingFilterProxy("springSecurityFilterChain");
		CharacterEncodingFilter bean2 = new CharacterEncodingFilter("UTF-8",true);
		UrlRewriteFilter bean3 = new UrlRewriteFilter();
		return new Filter[] { bean,bean2,bean3};
	}
	
	@Override
	protected String[] getServletMappings() {
		return new String[] {"*.xhtml","*.json","*.xml"};
	}
	
}

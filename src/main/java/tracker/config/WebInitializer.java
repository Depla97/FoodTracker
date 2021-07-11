package tracker.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import tracker.security.SecurityConfiguration;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {
			PersistenceConfiguration.class,
			SecurityConfiguration.class,
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {
			WebConfiguration.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/FoodTracker"};//Invoca il dispatcher su questo url
	}

}

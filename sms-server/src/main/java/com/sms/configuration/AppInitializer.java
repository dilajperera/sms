package com.sms.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * This class is responsible for 
 * web application initialization related com.sms.configuration .
 * @author Dilaj
 *
 */
public class AppInitializer implements WebApplicationInitializer {
	
	private static final String DISPATCHER = "dispatcher";
	//private static final String URL_SLASH = "/";
    private static final String URL_SLASH = "/*";
	
	/**
	 * configures the servlet mapping for the web application initialization. 
	 */
	@Override
	public void onStartup(ServletContext servletContext)throws ServletException {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfiguration.class);

        FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", CORSFilter.class);
        corsFilter.addMappingForUrlPatterns(null, false, "/*");

        ctx.setServletContext(servletContext);
		Dynamic dynamic = servletContext.addServlet(DISPATCHER,new DispatcherServlet(ctx));
		dynamic.addMapping(URL_SLASH);
		dynamic.setLoadOnStartup(1);
	}
	
}

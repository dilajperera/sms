package com.sms.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
/**
 * This class configure the web application.
 * spring internalviewresolver is used to resolve index.html
 * view. Jsonview resolver is used for REST API.
 * 
 * @author Dilaj
 *
 */
@Configuration
@ComponentScan("com.sms")
@EnableWebMvc
public class AppConfiguration extends WebMvcConfigurerAdapter {
	
	private static final String VIEW_INDEX = "index";
	private static final String URL_SLASH = "/";
	private static final String VIEWS_PREFIX = "/views/";
	private static final String VIEWS_SUFFIX = ".html";


   /* @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*");
    }*/

	/**
	 * view and controller for url (/) mapping. 
	 */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(URL_SLASH).setViewName(VIEW_INDEX);
    }
    
    /**
     * to resolve index.html
     * @return
     */
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
    	InternalResourceViewResolver iviewResolver = new InternalResourceViewResolver();
         iviewResolver.setPrefix(VIEWS_PREFIX);
         iviewResolver.setSuffix(VIEWS_SUFFIX);
         return iviewResolver;
    }

    /**
     * enables default servelet handler
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
	/**
	 * Configure ContentNegotiatingViewResolver
	 * 	internal view resolver for .html
	 * 	json view resolver for rest api
	 * @param manager
	 * @return configured contentNegotiatingViewResolver instance
	 */
    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
        resolvers.add(jsonViewResolver());
        resolvers.add(internalResourceViewResolver());
        resolver.setViewResolvers(resolvers);
        return resolver;
    }
    
    
    
    /**
   * Configure View resolver to provide JSON output using JACKSON library to
   * convert object in JSON format.
   * @return JsonViewResolver
   */
    @Bean
    public ViewResolver jsonViewResolver() {
        return new JsonViewResolver();
    }
}

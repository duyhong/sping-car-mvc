package com.spring.web.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * 
 * @author Nagendra
 *  This is acting as spring web application context
 *  profile-servlet.xml   
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.spring.web.mvc.controller","com.spring.web.mvc.exception"})
public class WebApplicationIntializer implements WebMvcConfigurer {
	
	//<bean>
	   @Bean
	   public InternalResourceViewResolver resolver() {
	      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	      resolver.setViewClass(JstlView.class);
	      resolver.setPrefix("/WEB-INF/jsps/");
	      resolver.setSuffix(".jsp");
	      return resolver;
	   }

	   @Override
	   public void addResourceHandlers(ResourceHandlerRegistry registry) {
	      // Register resource handler for images
	      registry.addResourceHandler("/images/**").addResourceLocations("/images/")
	            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	   // Register resource handler for images
	      registry.addResourceHandler("/static/**").addResourceLocations("/static/")
	            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());   
	  }
}

package org.app.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class SpringWebAppInitializer implements WebApplicationInitializer
{
	@Override
	public void onStartup(ServletContext servletContainer) throws ServletException
	{
		// manage life-cycle of the application context
		AnnotationConfigWebApplicationContext applicationContext = getApplicationContext();
		servletContainer.addListener(new ContextLoaderListener(applicationContext));

        // Create and configure dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext servletContext = getServletContext();
        ServletRegistration.Dynamic dispatcher = servletContainer.addServlet("dispatcher", new DispatcherServlet(servletContext));
		dispatcher.addMapping("/");
		dispatcher.setLoadOnStartup(1);
	}

	/** Creates root spring application context */
	private AnnotationConfigWebApplicationContext getApplicationContext()
	{
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringApplicationConfig.class);
		return context;
	}

	/** Creates dispatcher servlet's Spring application context */
	private AnnotationConfigWebApplicationContext getServletContext()
	{
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringWebConfig.class);
		return context;
	}
}

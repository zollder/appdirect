package org.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("org.app.web.controllers")
public class SpringWebConfig extends WebMvcConfigurerAdapter
{
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		super.addViewControllers(registry);
		registry.addViewController("/**").setViewName("index");
	}

	/** Static resources from both, WEB-INF and webjars (n/a yet). */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/** Serves static files using Servlet container's default Servlet. */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}

	@Bean
	public ViewResolver getViewResolver()
	{
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".html");
		return resolver;
	}
}

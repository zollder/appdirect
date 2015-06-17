package org.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@Import({SpringDatabaseConfig.class, SpringWebAppInitializer.class, SpringSecurityConfig.class})
@ComponentScan("org.app")
public class SpringApplicationConfig
{
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static LocalValidatorFactoryBean localValidatorFactoryBean()
    {
    	return new LocalValidatorFactoryBean();
    }
}

package org.app.config;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import org.app.security.OpenIdUserSecurityService;
import org.openid4java.consumer.ConsumerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.openid.AxFetchListFactory;
import org.springframework.security.openid.OpenID4JavaConsumer;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;

import com.google.inject.internal.Lists;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:/props/application.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Value("${oauth.consumer.key}")
    private String key;

    @Value("${oauth.consumer.secret}")
    private String secret;

    @Bean
    public OAuthConsumer oAuthConsumer()
    {
    	return new DefaultOAuthConsumer(key, secret);
    }

	@Bean
	public AuthenticationUserDetailsService<OpenIDAuthenticationToken> openIdUserDetailsService()
	{
		return new OpenIdUserSecurityService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			.authorizeRequests()
					.anyRequest().permitAll()
					.and()
			.csrf().disable()
			.logout()
					.logoutSuccessUrl("/login")
					.and()
			.openidLogin()
					.loginPage("/login").permitAll()
					.loginProcessingUrl("/openid")
					.authenticationUserDetailsService(openIdUserDetailsService())
					.consumer(openIdConsumer());
	}

	@Bean
	public OpenID4JavaConsumer openIdConsumer() throws ConsumerException
	{
		AxFetchListFactory attributesToFetchFactory = identifier -> Lists.newArrayList(
				new OpenIDAttribute("email", "http://axschema.org/contact/email"),
				new OpenIDAttribute("firstName", "http://axschema.org/namePerson/first"),
				new OpenIDAttribute("lastName", "http://axschema.org/namePerson/last"),
				new OpenIDAttribute("country", "http://axschema.org/contact/country/home")
		);

		return new OpenID4JavaConsumer(attributesToFetchFactory);
	}
}

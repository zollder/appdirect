package org.app.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import org.app.security.CustomResourceProcessingFilter;
import org.app.security.OpenIdUserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.InMemoryConsumerDetailsService;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.token.InMemoryProviderTokenServices;
import org.springframework.security.oauth.provider.token.OAuthProviderTokenServices;
import org.springframework.security.openid.OpenIDAuthenticationFilter;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:/props/application.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private Environment env;

    /** Enable secured access to appdirect/** and /welcome. Keep /login unsecured.
     * 	/welcome needed an authenticated user with the role USER
     * 	@param http
     * 	@throws Exception */
    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {
        http.csrf().disable();

        http.authorizeRequests().antMatchers("/subscription/**").permitAll().anyRequest().authenticated();
        http
        	.authorizeRequests()
    			.antMatchers("/login/", "/openid").permitAll()
    			.antMatchers("/welcome", "/logout").access("hasRole('ROLE_USER')")
    			.and()
            .openidLogin()
            	.loginPage("/openid")
            	.loginProcessingUrl("/login")
            	.authenticationUserDetailsService(openIdUserDetailsService())
                .defaultSuccessUrl("/welcome");

        http.addFilterAfter(oAuthProviderProcessingFilter(), OpenIDAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationUserDetailsService<OpenIDAuthenticationToken> openIdUserDetailsService()
    {
    	return new OpenIdUserSecurityService();
    }

    @Bean
    OAuthProviderProcessingFilter oAuthProviderProcessingFilter()
    {
        List<RequestMatcher> requestMatchers = new ArrayList<>();
        requestMatchers.add(new AntPathRequestMatcher("/subscription/**"));
        ProtectedResourceProcessingFilter filter = new CustomResourceProcessingFilter(requestMatchers);

        filter.setConsumerDetailsService(consumerDetailsService());
        filter.setTokenServices(providerTokenServices());

        return filter;
    }

    @Bean
    public ConsumerDetailsService consumerDetailsService()
    {
        InMemoryConsumerDetailsService consumerDetailsService = new InMemoryConsumerDetailsService();

        BaseConsumerDetails consumerDetails = new BaseConsumerDetails();
        consumerDetails.setConsumerKey(env.getProperty("oauth.consumer.key"));
        consumerDetails.setSignatureSecret(new SharedConsumerSecretImpl(env.getProperty("oauth.consumer.secret")));
        consumerDetails.setRequiredToObtainAuthenticatedToken(false);

        Map<String, BaseConsumerDetails> consumerDetailsStore = new HashMap<>();
        consumerDetailsStore.put(env.getProperty("oauth.consumer.key"), consumerDetails);

        consumerDetailsService.setConsumerDetailsStore(consumerDetailsStore);

        return consumerDetailsService;
    }

    @Bean
    public OAuthProviderTokenServices providerTokenServices()
    {
        return new InMemoryProviderTokenServices();
    }

    @Bean
    public OAuthConsumer oAuthConsumer()
    {
    	return new DefaultOAuthConsumer(env.getProperty("oauth.consumer.key"), env.getProperty("oauth.consumer.secret"));
    }
}

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.InMemoryConsumerDetailsService;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.token.InMemoryProviderTokenServices;
import org.springframework.security.oauth.provider.token.OAuthProviderTokenServices;
import org.springframework.security.openid.OpenIDAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:/props/application.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Value("${oauth.consumer.key}")
    private String key;

    @Value("${oauth.consumer.secret}")
    private String secret;

    @Autowired
    private OpenIdUserSecurityService openIdUserSecurityService;

    @Bean
    public OAuthConsumer oAuthConsumer()
    { return new DefaultOAuthConsumer(key, secret); }

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated();
        http.authorizeRequests().antMatchers("/login/", "/myapplogin").permitAll()
        .antMatchers("/welcome", "/logout").access("hasRole('ROLE_USER')")
                .and()
                .openidLogin().authenticationUserDetailsService(openIdUserSecurityService)
                .loginProcessingUrl("/login")
                .loginPage("/myapplogin")
                .defaultSuccessUrl("/welcome");

		http.addFilterAfter(oAuthProviderProcessingFilter(), OpenIDAuthenticationFilter.class);
	}

    @Bean
    OAuthProviderProcessingFilter oAuthProviderProcessingFilter()
    {
        List<RequestMatcher> requestMatchers = new ArrayList<>();
        requestMatchers.add(new AntPathRequestMatcher("/"));
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
        consumerDetails.setConsumerKey(key);
        consumerDetails.setSignatureSecret(new SharedConsumerSecretImpl(secret));
        consumerDetails.setRequiredToObtainAuthenticatedToken(false);

        Map<String, BaseConsumerDetails> consumerDetailsStore = new HashMap<>();
        consumerDetailsStore.put(key, consumerDetails);
        consumerDetailsService.setConsumerDetailsStore(consumerDetailsStore);
        return consumerDetailsService;
    }

    @Bean
    public OAuthProviderTokenServices providerTokenServices()
    { return new InMemoryProviderTokenServices(); }

//	@Bean
//	public OpenID4JavaConsumer openIdConsumer() throws ConsumerException
//	{
//		AxFetchListFactory attributesToFetchFactory = identifier -> Lists.newArrayList(
//				new OpenIDAttribute("email", "http://axschema.org/contact/email"),
//				new OpenIDAttribute("firstName", "http://axschema.org/namePerson/first"),
//				new OpenIDAttribute("lastName", "http://axschema.org/namePerson/last"),
//				new OpenIDAttribute("country", "http://axschema.org/contact/country/home")
//		);
//
//		return new OpenID4JavaConsumer(attributesToFetchFactory);
//	}
}

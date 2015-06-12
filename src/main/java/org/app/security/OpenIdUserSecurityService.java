package org.app.security;

import org.apache.log4j.Logger;
import org.app.domain.model.entities.User;
import org.app.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;

//--------------------------------------------------------------------------------------------------------------------------------
/** User Security service. Used by Spring Security to load UserDetails object by openId. */
//--------------------------------------------------------------------------------------------------------------------------------
@Service("openIdUserSecurityService")
public class OpenIdUserSecurityService implements UserDetailsService, AuthenticationUserDetailsService<OpenIDAuthenticationToken>
{
	@Autowired
	UserService userService;

	private static Logger logger = Logger.getLogger(OpenIdUserSecurityService.class);

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Spring security authentication provider.
	 *  Current implementation implies user authentication by openId, not by username.
	 *  UserDetailsService implementation is adapted to use openId.
	 *  @param openId - embedded in the request header.
	 *  @return spring-adapted UserDetails object or throws UsernameNotFoundException */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException
	{
		String openId = (String) token.getPrincipal();
		logger.debug(String.format("OpenId from token: %s", openId));

		return this.loadUserByUsername(openId);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public UserDetails loadUserByUsername(String openId) throws UsernameNotFoundException
	{
		// load user from the database by openId
		User user = userService.loadByOpenId(openId);
		if (user == null)
			throw new UsernameNotFoundException("User with openId " + openId + "  has not been found.");

		// wrap it for Spring Security.
		return new SpringUserAdapter(user);
	}
}
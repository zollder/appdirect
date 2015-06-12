package org.app.security;

import org.app.domain.model.entities.User;
import org.app.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//--------------------------------------------------------------------------------------------------------------------------------
/** User Security service. Used by Spring Security to load UserDetails object by openId. */
//--------------------------------------------------------------------------------------------------------------------------------
@Service("openIdUserSecurityService")
public class OpenIdUserSecurityService implements UserDetailsService, AuthenticationUserDetailsService<OpenIDAuthenticationToken>
{
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException
	{
		return this.loadUserByUsername((String) token.getPrincipal());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Spring security authentication provider.
	 *  Current implementation implies user authentication by openId, not by username.
	 *  UserDetailsService implementation is adapted to use openId.
	 *  @param openId - embedded in the request header.
	 *  @return spring-adapted UserDetails object or throws UsernameNotFoundException */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String openId) throws UsernameNotFoundException, DataAccessException
	{
		// load user from the database by openId
		User user = userService.loadByOpenId(openId);
		if (user == null)
			throw new UsernameNotFoundException("User with openId " + openId + "  has not been found.");

		// and wrap it for Spring Security.
		SpringUserAdapter userToAuthenticate = new SpringUserAdapter(user);

		return userToAuthenticate;
	}
}
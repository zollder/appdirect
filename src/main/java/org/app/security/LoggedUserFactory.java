package org.app.security;

import org.app.domain.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


// --------------------------------------------------------------------------------------------------------------------------------
/** LoggedUserFactory */
// --------------------------------------------------------------------------------------------------------------------------------
public class LoggedUserFactory
{
	private static SecurityContext securityContextMock;

	// --------------------------------------------------------------------------------------------------------------------------------
	protected static SecurityContext getSecurityContext()
	{
		if (securityContextMock == null)
			return SecurityContextHolder.getContext();
		else
			return securityContextMock;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** @param spring SecurityContext */
	// --------------------------------------------------------------------------------------------------------------------------------
	public static void setMockSecurityContext(SecurityContext pSecurityContext)
	{
		securityContextMock = pSecurityContext;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** @return currently logged in user */
	// --------------------------------------------------------------------------------------------------------------------------------
	public static User get()
	{
		SpringUserAdapter principal = (SpringUserAdapter) getPrincipal();

		if (principal != null)
			return principal.getUser();
		else
			return null;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** @return the Spring UserDetails object. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public static UserDetails getPrincipal()
	{
		SecurityContext context = getSecurityContext();
		Authentication authentication = context.getAuthentication();

		if (authentication != null)
		{
			UserDetails principal = (UserDetails) authentication.getPrincipal();
			return principal;
		}
		else
			return null;
	}
}

package org.app.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.app.domain.enums.RoleEnum;
import org.app.domain.model.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//--------------------------------------------------------------------------------------------------------------------------------
/** Adapts an application user into a Spring UserDetails */
// --------------------------------------------------------------------------------------------------------------------------------
public class SpringUserAdapter implements UserDetails
{
	private static final long serialVersionUID = 1L;

	/** User (from the database) **/
	private User dbUser;

	/** Spring attributes needed in the UserDetails contract **/
	private String openId;
	private String password;
	private boolean enabled;
	private Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

	// --------------------------------------------------------------------------------------------------------------------------------
	public SpringUserAdapter(User user)
	{
		this.dbUser = user;
		this.openId = user.getOpenId();
		this.password = "";
		this.enabled = user.getIsEnabled();
		this.authorities.add(new SimpleGrantedAuthority(RoleEnum.ROLE_USER.name()));
	}

    public User getUser()
    {
        return this.dbUser;
    }

	// --------------------------------------------------------------------------------------------------------------------------------
    @Override
	public Collection<GrantedAuthority> getAuthorities()
    {
        return this.authorities;
    }

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String getPassword()
	{
		return this.password;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String getUsername()
	{
		return this.openId;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean isEnabled()
	{
		return this.enabled;
	}
}

package org.app.security;

import org.springframework.security.core.GrantedAuthority;

//--------------------------------------------------------------------------------------------------------------------------------
/** Implements granted user authority. */
// --------------------------------------------------------------------------------------------------------------------------------
public class UserAuthority implements GrantedAuthority
{
	private static final long serialVersionUID = 1L;

	private String authorityName;

	public void setAuthorityName(String name) {
		this.authorityName = name;
	}

	@Override
	public String getAuthority()
	{
		return this.authorityName;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Role: ");
		builder.append(this.getAuthority());

		return builder.toString();
	}
}

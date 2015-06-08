package org.app.domain.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//--------------------------------------------------------------------------------------------------
/** UserRoleAssociation bean. */
//--------------------------------------------------------------------------------------------------

@Entity
@Table(name = "user_role_association")
public class UserRoleAssociation extends AbstractBase<UserRoleAssociation>
{
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="role")
	@NotNull
	private Role authorityRole;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	@NotNull
	private User authorityUser;

	//--------------------------------------------------------------------------------------------------
	public Role getAuthorityRole()
	{
		return authorityRole;
	}

	public void setAuthorityRole(Role userRole)
	{
		this.authorityRole = userRole;
	}

	//--------------------------------------------------------------------------------------------------
	public User getAuthorityUser()
	{
		return authorityUser;
	}

	public void setAuthorityUser(User user)
	{
		this.authorityUser = user;
	}
}

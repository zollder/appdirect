
package org.app.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//--------------------------------------------------------------------------------------------------
/** Role bean. */
//--------------------------------------------------------------------------------------------------

@Entity
@Table(name = "role")
public class Role extends AbstractBase<Role>
{
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name="roleName", unique = true)
	private String roleName;

	@NotNull
	@Column(name="roleDescription")
	private String roleDescription;

	//--------------------------------------------------------------------------------------------------
	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String name)
	{
		this.roleName = name;
	}

	//--------------------------------------------------------------------------------------------------
	public String getRoleDescription()
	{
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription)
	{
		this.roleDescription = roleDescription;
	}
}


package org.app.domain.model.dto;


//--------------------------------------------------------------------------------------------------
/** Role bean. */
//--------------------------------------------------------------------------------------------------

public class Role
{
	private String roleName;
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

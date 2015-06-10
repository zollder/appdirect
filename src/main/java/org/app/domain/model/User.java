package org.app.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


// --------------------------------------------------------------------------------------------------
/** User bean. */
// --------------------------------------------------------------------------------------------------

@Entity
@Table(name="user")
@XmlRootElement
public class User extends AbstractBase<User>
{
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name="openId", unique = true)
	private String openId;

	@Column(name="firstName")
	private String firstName;

	@Column(name="lastName")
	private String lastName;

	@Column(name="email", unique = true)
	private String email;

	@Column(name="isEnabled")
	private Boolean isEnabled;

	@Column(name="isExpired")
	private Boolean isExpired;

	@ManyToOne
	@JoinColumn(name="company")
	private Company company;

	// --------------------------------------------------------------------------------------------------
	public String getOpenId()
	{
		return this.openId;
	}

	public void setOpenId(String id)
	{
		this.openId = id;
	}

	//---------------------------------------------------------------------------------------------------
	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	// --------------------------------------------------------------------------------------------------
	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String secondName)
	{
		this.lastName = secondName;
	}

	// --------------------------------------------------------------------------------------------------
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	// --------------------------------------------------------------------------------------------------
	public Boolean getIsEnabled()
	{
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled)
	{
		this.isEnabled = isEnabled;
	}

	// --------------------------------------------------------------------------------------------------
	public Boolean getIsExpired()
	{
		return isExpired;
	}

	public void setIsExpired(Boolean isExpired)
	{
		this.isExpired = isExpired;
	}

	// --------------------------------------------------------------------------------------------------
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("User [");
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", openId=");
		builder.append(openId);
		builder.append(", isEnabled=");
		builder.append(isEnabled);
		builder.append(", isExpired=");
		builder.append(isExpired);
		builder.append("]");
		return builder.toString();
	}
}
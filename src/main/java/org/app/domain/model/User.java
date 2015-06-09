package org.app.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.app.security.PasswordEncoder;
import org.app.security.PasswordEncoderFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;


// --------------------------------------------------------------------------------------------------
/** User bean. */
// --------------------------------------------------------------------------------------------------

@Entity
@Table(name="user")
public class User extends AbstractBase<User>
{
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Transient
	protected PasswordEncoder passwordEncoder;

	@Column(name="firstName")
	private String firstName;

	@Column(name="lastName")
	private String lastName;

	@Column(name="email", unique = true)
	private String email;

	@NotNull
	@Column(name="username", unique = true)
	private String username;

	@NotNull
	@Column(name="password")
	private String password;

	@NotNull
	@Column(name="openId", unique = true)
	private String openId;

	@Column(name="isEnabled")
	private Boolean isEnabled;

	@Column(name="isExpired")
	private Boolean isExpired;

	@Column(name="isLocked")
	private Boolean isLocked;

	@Basic
	@Column(name = "created")
	private Date creationDate;

	@Basic
	@Column(name = "modified")
	private Date modificationDate;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="primaryKey")
	private Set<Role> roles = new HashSet<>();


	// --------------------------------------------------------------------------------------------------
	public PasswordEncoder getPasswordEncoder()
	{
		if (passwordEncoder == null)
			passwordEncoder = PasswordEncoderFactory.getPasswordEncoder();

		return this.passwordEncoder;
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
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	// --------------------------------------------------------------------------------------------------
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	// --------------------------------------------------------------------------------------------------
	public String getOpenId()
	{
		return this.openId;
	}

	public void setOpenId(String id)
	{
		this.openId = id;
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
	public Boolean getIsLocked()
	{
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked)
	{
		this.isLocked = isLocked;
	}

	// --------------------------------------------------------------------------------------------------
	public Date getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Date date)
	{
		this.creationDate = date;
	}

	// --------------------------------------------------------------------------------------------------
	public Date getModificationDate()
	{
		return modificationDate;
	}

	public void setModificationDate(Date date)
	{
		this.modificationDate = date;
	}

	// --------------------------------------------------------------------------------------------------
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("User [");
		builder.append("username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
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
		builder.append(", isLocked=");
		builder.append(isLocked);
		builder.append("]");
		return builder.toString();
	}
}
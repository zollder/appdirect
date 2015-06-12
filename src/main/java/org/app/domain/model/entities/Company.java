package org.app.domain.model.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

// --------------------------------------------------------------------------------------------------
/** Company bean. */
// --------------------------------------------------------------------------------------------------
@Entity
@Table(name="company")
@XmlRootElement
public class Company extends AbstractBase<Company>
{
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name="country")
	private String country;

	@Basic
	@NotNull
	@Column(name="name")
	private String name;

	@Basic
	@Column(name="email")
	private String email;

	@Basic
	@Column(name="phone")
	private String phoneNumber;

	@Basic
	@Column(name="website")
	private String website;

	@Basic
	@NotNull
	@Column(name="uuid", unique = true)
	private String uuid;


	// --------------------------------------------------------------------------------------------------
	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	// --------------------------------------------------------------------------------------------------
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	// --------------------------------------------------------------------------------------------------
	public String getWebsite()
	{
		return website;
	}

	public void setWebsite(String website)
	{
		this.website = website;
	}

	// --------------------------------------------------------------------------------------------------
	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	// --------------------------------------------------------------------------------------------------
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Company [");
		builder.append("country=");
		builder.append(country);
		builder.append(", name=");
		builder.append(name);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phone=");
		builder.append(phoneNumber);
		builder.append(", website=");
		builder.append(website);
		builder.append(", uuid=");
		builder.append(uuid);
		builder.append("]");
		return builder.toString();
	}
}
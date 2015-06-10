package org.app.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Creator
{
	private String firstName;
	private String lastName;
	private String email;
	private String language;
    private String uuid;
    private String openId;

    public Creator() {}

    public Creator(String firstName, String lastName, String email, String language, String uuid, String openId)
    {
    	this.lastName = lastName;
    	this.firstName = firstName;
    	this.email = email;
    	this.language = language;
        this.uuid = uuid;
        this.openId = openId;
    }

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public String getOpenId()
	{
		return openId;
	}

	public void setOpenId(String openId)
	{
		this.openId = openId;
	}
}
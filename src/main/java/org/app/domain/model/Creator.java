package org.app.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Creator
{
	private String firstName;
	private String lastName;
	private String email;
    private String openId;
    private String language;

    // ---------------------------------------------------------------------------------------------
    public Creator() {}

    public Creator(String firstName, String lastName, String email, String openId, String lang)
    {
    	this.lastName = lastName;
    	this.firstName = firstName;
    	this.email = email;
        this.openId = openId;
        this.setLanguage(lang);
    }

    // ---------------------------------------------------------------------------------------------
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

	public String getOpenId()
	{
		return openId;
	}

	public void setOpenId(String openId)
	{
		this.openId = openId;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}
}
package org.app.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account
{
	private String accountIdentifier;

	// --------------------------------------------------------------------------------------------------
	public Account() {}

	public Account(String id)
	{
		this.accountIdentifier = id;
	}

	// --------------------------------------------------------------------------------------------------
	public String getAccountIdentifier()
	{
		return accountIdentifier;
	}

	public void setAccountIdentifier(String id)
	{
		this.accountIdentifier = id;
	}
}

package org.app.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.app.domain.enums.AccountStatusEnum;

@XmlRootElement
public class Account
{
	private String accountId;
	private AccountStatusEnum status;

	// --------------------------------------------------------------------------------------------------
	public Account() {}

	public Account(String id)
	{
		this.accountId = id;
	}

	// --------------------------------------------------------------------------------------------------
	public String getAccountId()
	{
		return this.accountId;
	}

	public void setAccountId(String id)
	{
		this.accountId = id;
	}

	public AccountStatusEnum getStatus()
	{
		return status;
	}

	public void setStatus(AccountStatusEnum status)
	{
		this.status = status;
	}
}

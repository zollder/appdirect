package org.app.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Payload
{
	private Account account;	// 'change', 'cancel' and 'status' events only
	private Company company;	// 'create' events only
	private Order order;		// 'create' and 'change' events only
	private Notice notice;		// 'status' events only
	private User user;			// 'user_assisg' and 'user_unassign' events only

	// --------------------------------------------------------------------------------------------------
	public Payload() {}

	public Payload(Account account, Company company, Order order, Notice notice, User user)
	{
		this.user = user;
		this.notice = notice;
		this.order = order;
		this.company = company;
		this.account = account;
	}

	// --------------------------------------------------------------------------------------------------
	public Account getAccount()
	{
		return account;
	}

	public void setAccount(Account account)
	{
		this.account = account;
	}

	public Company getCompany()
	{
		return company;
	}

	public void setCompany(Company company)
	{
		this.company = company;
	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}

	public Notice getNotice()
	{
		return notice;
	}

	public void setNotice(Notice notice)
	{
		this.notice = notice;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
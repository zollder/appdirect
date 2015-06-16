package org.app.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.app.domain.model.entities.Company;

@XmlRootElement
public class Payload
{
	private Account account;	// 'change', 'cancel' and 'status' events only
	private Company company;	// 'create' events only
	private Order order;		// 'create' and 'change' events only
	private Notice notice;		// 'status' events only
	private Configuration configuration;

	// --------------------------------------------------------------------------------------------------
	public Payload() {}

	public Payload(Account account, Company company, Order order, Notice notice, Configuration config)
	{
		this.notice = notice;
		this.order = order;
		this.company = company;
		this.account = account;
		this.setConfiguration(config);
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

	public Configuration getConfiguration()
	{
		return configuration;
	}

	public void setConfiguration(Configuration configuration)
	{
		this.configuration = configuration;
	}
}
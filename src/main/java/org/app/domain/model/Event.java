package org.app.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Event
{
	private Creator creator;
	private Marketplace marketplace;
	private Payload payload;
	private String flag;
	private String type;

	// --------------------------------------------------------------------------------------------------
	public Event() {}

	public Event(Creator creator, Marketplace marketplace, Payload payload, String flag, String type)
	{
		this.type = type;
		this.flag = flag;
		this.payload = payload;
		this.marketplace = marketplace;
		this.creator = creator;
	}

	// --------------------------------------------------------------------------------------------------
	public Creator getCreator()
	{
		return creator;
	}

	public void setCreator(Creator creator)
	{
		this.creator = creator;
	}

	public Marketplace getMarketplace()
	{
		return marketplace;
	}

	public void setMarketplace(Marketplace marketplace)
	{
		this.marketplace = marketplace;
	}

	public Payload getPayload()
	{
		return payload;
	}

	public void setPayload(Payload payload)
	{
		this.payload = payload;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
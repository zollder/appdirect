package org.app.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order
{
	private String editionCode;
	private String pricingDuration;

	// --------------------------------------------------------------------------------------------------
	public Order()
	{}

	public Order(String code, String duration)
	{
		this.setEditionCode(code);
		this.setPricingDuration(duration);
	}

	// --------------------------------------------------------------------------------------------------
	public String getEditionCode()
	{
		return editionCode;
	}

	public void setEditionCode(String code)
	{
		this.editionCode = code;
	}

	public String getPricingDuration()
	{
		return pricingDuration;
	}

	public void setPricingDuration(String pricingDuration)
	{
		this.pricingDuration = pricingDuration;
	}
}
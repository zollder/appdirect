package org.app.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item
{
	private String unit;
	private Integer quantity;

	// --------------------------------------------------------------------------------------------------
	public Item ()
	{}

	public Item (String unit, Integer qty)
	{
		this.quantity = qty;
		this.unit = unit;
	}

	// --------------------------------------------------------------------------------------------------
	public Integer getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

	// --------------------------------------------------------------------------------------------------
	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}
}
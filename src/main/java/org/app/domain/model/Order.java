package org.app.domain.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order
{
	private String editionCode;
	private Collection<Item> items;

	// --------------------------------------------------------------------------------------------------
	public Order ()
	{}

	public Order (String code, Collection<Item> itemsList)
	{
		this.setEditionCode(code);
		this.setItems(itemsList);
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

	public Collection<Item> getItems()
	{
		return items;
	}

	public void setItems(Collection<Item> itemsList)
	{
		this.items = itemsList;
	}
}
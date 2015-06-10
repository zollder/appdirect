package org.app.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Notice
{
	private String type;

	// --------------------------------------------------------------------------------------------------
	public Notice() {}

	public Notice(String type)
	{
		this.type = type;
	}

	// --------------------------------------------------------------------------------------------------
	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
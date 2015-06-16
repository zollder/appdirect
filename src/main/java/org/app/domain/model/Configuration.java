package org.app.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Configuration
{
	private String configType;

	// --------------------------------------------------------------------------------------------------
	public Configuration() {}

	public Configuration(String type)
	{
		this.setConfigType(type);
	}

	// --------------------------------------------------------------------------------------------------
	public String getConfigType()
	{
		return configType;
	}

	public void setConfigType(String configType)
	{
		this.configType = configType;
	}
}

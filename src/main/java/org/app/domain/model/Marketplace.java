package org.app.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Marketplace
{
	private String baseUrl;
	private String partner;

	public Marketplace() {}

	public Marketplace (String partner, String baseUrl)
	{
		this.baseUrl = baseUrl;
		this.partner = partner;
	}

	public String getBaseUrl(){
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl){
		this.baseUrl = baseUrl;
	}

	public String getPartner(){
		return partner;
	}

	public void setPartner(String partner){
		this.partner = partner;
	}
}
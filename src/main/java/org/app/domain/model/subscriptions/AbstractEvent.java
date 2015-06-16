package org.app.domain.model.subscriptions;

import java.io.Serializable;

import org.app.domain.enums.EventTypeEnum;
import org.app.domain.model.Creator;
import org.app.domain.model.Marketplace;

//-------------------------------------------------------------------------------------------------------
/** AbstractEvent base class. */
//-------------------------------------------------------------------------------------------------------
public abstract class AbstractEvent implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Creator creator;
	private Marketplace marketplace;
	private EventTypeEnum type;
	private String url;
	private String flag;

	// --------------------------------------------------------------------------------------------------
	public AbstractEvent(EventTypeEnum eventType)
	{
		this.type= eventType;
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

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public EventTypeEnum getType()
	{
		return type;
	}

	public void setType(EventTypeEnum eventType)
	{
		this.type = eventType;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}
}
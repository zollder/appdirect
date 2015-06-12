package org.app.domain.model.subscriptions;

import javax.xml.bind.annotation.XmlRootElement;

import org.app.domain.enums.EventTypeEnum;
import org.app.domain.model.Payload;

@XmlRootElement(name = "event")
public class OrderEvent extends AbstractEvent
{
	private static final long serialVersionUID = 1L;

	private Payload payload;

    // --------------------------------------------------------------------------------------------------
    public OrderEvent()
    {
        super(EventTypeEnum.SUBSCRIPTION_ORDER);
    }

    // --------------------------------------------------------------------------------------------------
	public Payload getPayload()
	{
		return payload;
	}

	public void setPayload(Payload payload)
	{
		this.payload = payload;
	}
}

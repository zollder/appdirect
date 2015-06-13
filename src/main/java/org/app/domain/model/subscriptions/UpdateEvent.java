package org.app.domain.model.subscriptions;

import javax.xml.bind.annotation.XmlRootElement;

import org.app.domain.enums.EventTypeEnum;
import org.app.domain.model.Payload;

@XmlRootElement(name = "event")
public class UpdateEvent extends AbstractEvent
{
	private static final long serialVersionUID = 1L;

	private Payload payload;

    // --------------------------------------------------------------------------------------------------
    public UpdateEvent()
    {
        super(EventTypeEnum.SUBSCRIPTION_CHANGE);
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

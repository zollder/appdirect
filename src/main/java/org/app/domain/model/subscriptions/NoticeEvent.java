package org.app.domain.model.subscriptions;

import javax.xml.bind.annotation.XmlRootElement;

import org.app.domain.enums.EventTypeEnum;
import org.app.domain.model.Payload;

@XmlRootElement(name = "event")
public class NoticeEvent extends AbstractEvent
{
	private static final long serialVersionUID = 1L;

	private Payload payload;

    // --------------------------------------------------------------------------------------------------
    public NoticeEvent()
    {
        super(EventTypeEnum.SUBSCRIPTION_NOTICE);
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

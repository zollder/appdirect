package org.app.web.controllers;

import org.apache.log4j.Logger;
import org.app.domain.enums.EventTypeEnum;
import org.app.domain.enums.FlagEnum;
import org.app.domain.enums.OrderCodeEnum;
import org.app.domain.enums.PricingDurationEnum;
import org.app.domain.model.Creator;
import org.app.domain.model.Marketplace;
import org.app.domain.model.Order;
import org.app.domain.model.Payload;
import org.app.domain.model.entities.Company;
import org.app.domain.model.subscriptions.OrderEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//-------------------------------------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping(value = "/test")
public class TestController
{
	private static Logger logger = Logger.getLogger(TestController.class);

	// ----------------------------------------------------------------------------------------------
	/** Returns {@link OrderEvent}. */
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/events/{token}", method = { RequestMethod.GET })
	@ResponseBody
	public OrderEvent returnOrderEvent(@PathVariable String token)
	{
		logger.info("return test order event object for tocken:" + token);

		// build fake OrderEvent
		OrderEvent event = new OrderEvent();
		event.setType(EventTypeEnum.SUBSCRIPTION_ORDER);
		event.setFlag(FlagEnum.DEVELOPMENT.name());
		event.setMarketplace(new Marketplace("APPDIRECT", "https://www.appdirect.com"));
		event.setCreator(new Creator("FName", "LName", "creator@email.com", "https://www.appdirect.com/openid/id/a2d05577-23ac-42d7-b031-097721305205", "EN"));

		Payload payload = new Payload();
		payload.setCompany(new Company("US", "Integration", "e@mail.com", "3456789012", null, "test-company-id"));
		payload.setOrder(new Order(OrderCodeEnum.FREE.name(), PricingDurationEnum.MONTHLY.name()));
		event.setPayload(payload);

		return event;
	}
}
package org.app.domain.services.impl;

import javax.persistence.EntityExistsException;

import org.apache.log4j.Logger;
import org.app.domain.enums.ErrorCodeEnum;
import org.app.domain.model.Response;
import org.app.domain.model.entities.User;
import org.app.domain.model.subscriptions.OrderEvent;
import org.app.domain.services.EventDataService;
import org.app.domain.services.SubscriptionEventService;
import org.app.domain.services.UserService;
import org.app.domain.utils.DtoMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//--------------------------------------------------------------------------------------------------
/** SubscriptionEventService implementation. */
//--------------------------------------------------------------------------------------------------
@Service("subscriptionEventService")
public class SubscriptionEventServiceImpl implements SubscriptionEventService
{
	@Autowired
	private EventDataService eventDataService;

	@Autowired
	private UserService userService;

	private static Logger logger = Logger.getLogger(SubscriptionEventServiceImpl.class);
    // ---------------------------------------------------------------------------------------------
	@Override
	public Response createSubscription(String eventUrl)
	{
		logger.debug(String.format("Processing order event with event URL: %s", eventUrl));

		Response response = null;
		try
		{
			// retrieve and parse subscription event
			OrderEvent orderEvent = eventDataService.getEvent(eventUrl, OrderEvent.class);

			// try to create subscriber account
			User subscriber = userService.loadByOpenId(orderEvent.getCreator().getOpenId());
			if (userService.loadByOpenId(orderEvent.getCreator().getOpenId()) == null)
			{
				subscriber = DtoMapperUtil.convertToUser(orderEvent.getCreator(), new User());
				subscriber.setCompany(orderEvent.getPayload().getCompany());
				User savedSubscriber = userService.save(subscriber);
				response = Response.success("Account creation successful", savedSubscriber.getAccountId());
			}
		}
		catch (EntityExistsException ex)
		{ response = Response.failure(ErrorCodeEnum.USER_ALREADY_EXISTS.name(), ex.getLocalizedMessage()); }
		catch (Exception ex)
		{ response = Response.failure(ErrorCodeEnum.UNKNOWN_ERROR.name(), ex.getLocalizedMessage()); }

		return response;
	}

	// ---------------------------------------------------------------------------------------------
	@Override
	public Response modifySubscription(String event)
	{
		// TODO Auto-generated method stub
		return null;
	}

	// ---------------------------------------------------------------------------------------------
	@Override
	public Response cancelSubscription(String event)
	{
		// TODO Auto-generated method stub
		return null;
	}

	// ---------------------------------------------------------------------------------------------
	@Override
	public Response getSubscriptionStatus(String event)
	{
		// TODO Auto-generated method stub
		return null;
	}
}

package org.app.web.controllers;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.app.domain.enums.ErrorCodeEnum;
import org.app.domain.model.Response;
import org.app.domain.services.SubscriptionEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//---------------------------------------------------------------------------------------------------
@Controller
@RequestMapping(value = "/subscription")
public class SubscriptionController
{
	private static Logger logger = Logger.getLogger(SubscriptionController.class);
	// ----------------------------------------------------------------------------------------------
	@Autowired
	private SubscriptionEventService eventService;

	// ----------------------------------------------------------------------------------------------
	/** Processes SUBSCRIPTION_ORDER event. */
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = { RequestMethod.GET })
	@ResponseBody

	public Response processCreateEvent(@RequestParam String eventUrl, @AuthenticationPrincipal OpenIDAuthenticationToken authentication)
	{
		logger.debug(String.format("URL: %s, Authentication: %", eventUrl, authentication));
		Response response = this.validateRequestParams(eventUrl, authentication);

		if (response != null)
			response = eventService.createSubscription(eventUrl);

		return response;
	}

	// ----------------------------------------------------------------------------------------------
	/** Validates AppDirect request parameters.
	 *  Builds and returns a {@link Response} if an issue or debug scenario is encountered.
	 *  Returns null otherwise. */
	// ----------------------------------------------------------------------------------------------
	private Response validateRequestParams(String url, OpenIDAuthenticationToken authentication)
	{
		Response response = null;
		if (StringUtils.isBlank(url) || StringUtils.isBlank(StringUtils.substringAfterLast(url, "/")))
		{
			response = Response.failure(ErrorCodeEnum.INVALID_RESPONSE.name(), "Invalid url and/or token");
			return response;
		}

		if (StringUtils.contains(url, "dummy"))
		{
			response = Response.success("It works!", url);
			return response;
		}

		if ((authentication == null) || !authentication.isAuthenticated())
		{
			response = Response.failure(ErrorCodeEnum.UNAUTHORIZED.name(), "Authentication failed for some reason");
			return response;
		}

		return response;
	}
}
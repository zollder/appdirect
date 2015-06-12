package org.app.web.controllers;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.app.domain.enums.ErrorCodeEnum;
import org.app.domain.model.Response;
import org.app.domain.services.SubscriptionEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth.provider.ConsumerAuthentication;
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

	public Response processCreateEvent(@RequestParam String eventUrl,
									   @RequestParam String token,
									   @AuthenticationPrincipal ConsumerAuthentication authentication)
	{
		logger.debug(String.format("URL: %s, OAuth token: %s, authentication: %s", eventUrl, token, authentication));
		Response response = this.validateRequestParams(eventUrl, token, authentication);

		if (response != null)
			response = eventService.createSubscription(eventUrl);

		return response;
	}

	// ----------------------------------------------------------------------------------------------
	/** Validates AppDirect request parameters.
	 *  Builds and returns a {@link Response} if an issue or debug scenario is encountered.
	 *  Returns null otherwise. */
	// ----------------------------------------------------------------------------------------------
	private Response validateRequestParams(String url, String token, ConsumerAuthentication authentication)
	{
		Response response = null;
		if (StringUtils.isBlank(url) || StringUtils.isBlank(token))
		{
			response = new Response();
			response.setErrorCode(ErrorCodeEnum.INVALID_RESPONSE.name());
			response.setMessage("Invalid url and/or token");
			response.setSuccess(false);
			return response;
		}

		if ((authentication == null) || !authentication.isAuthenticated())
		{
			response = new Response();
			response.setErrorCode(ErrorCodeEnum.UNAUTHORIZED.name());
			response.setMessage("Unauthorized request.");
			response.setSuccess(false);
			return response;
		}

		if (StringUtils.contains(token, "dummy"))
		{
			response = new Response();
			response.setMessage("It works!");
			response.setSuccess(true);
			return response;
		}

		return response;
	}
}
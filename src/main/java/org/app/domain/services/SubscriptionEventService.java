package org.app.domain.services;

import org.app.domain.model.Response;

//--------------------------------------------------------------------------------------------------
/** SubscriptionEventService interface.
 *  Declares available subscription and assignment event handing services. */
//--------------------------------------------------------------------------------------------------
public interface SubscriptionEventService
{
	/** Creates a new subscription/order. Returns confirmation message. */
	public Response createSubscription(String eventUrl);

	/** Modifies (upgrade/downgrade/change) an existing subscription/order.
	 *  Returns confirmation message. */
	public Response modifySubscription(String eventUrl);

	/** Cancels an existing subscription/order. Returns confirmation message. */
	public Response cancelSubscription(String eventUrl);

	/** Returns a status of an existing subscription/order. */
	public Response getSubscriptionStatus(String eventUrl);
}
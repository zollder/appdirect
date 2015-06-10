package org.app.domain.services;

import org.app.domain.model.Response;
import org.app.domain.model.User;

//--------------------------------------------------------------------------------------------------
/** SubscriptionEventService interface.
 *  Declares available subscription and assignment event handing services. */
//--------------------------------------------------------------------------------------------------
public interface EventService
{
	/** Creates a new subscription/order. Returns confirmation message. */
	public Response createSubscription(String event);

	/** Modifies (upgrade/downgrade/change) an existing subscription/order.
	 *  Returns confirmation message. */
	public Response modifySubscription(String event);

	/** Cancels an existing subscription/order. Returns confirmation message. */
	public Response cancelSubscription(String event);

	/** Returns a status of an existing subscription/order. */
	public Response getSubscriptionStatus(String event);

	/** Assigns a {@link User} to an application. */
	public Response assign(String event);

	/** Unassigns a {@link User} from an application. */
	public Response unassign(String event);
}
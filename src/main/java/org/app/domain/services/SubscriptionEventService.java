package org.app.domain.services;

import org.app.domain.dto.ResponseMessage;

//--------------------------------------------------------------------------------------------------
/** SubscriptionEventService interface.
 *  Declares services related to subscription event handing. */
//--------------------------------------------------------------------------------------------------
public interface SubscriptionEventService
{
	/** Creates a new subscription/order. Returns confirmation message. */
	public ResponseMessage createSubscription(String event);

	/** Modifies (upgrade/downgrade/change) an existing subscription/order.
	 *  Returns confirmation message. */
	public ResponseMessage modifySubscription(String event);

	/** Cancels an existing subscription/order. Returns confirmation message. */
	public ResponseMessage cancelSubscription(String event);

	/** Returns a status of an existing subscription/order. */
	public ResponseMessage getSubscriptionStatus(String event);
}
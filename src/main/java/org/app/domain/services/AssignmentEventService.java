package org.app.domain.services;

import org.app.domain.model.Response;

//--------------------------------------------------------------------------------------------------
/** AssignmentEventService interface.
 *  Declares available assignment event handing services. */
//--------------------------------------------------------------------------------------------------
public interface AssignmentEventService
{
	/** Assigns a {@link User} to an application. */
	public Response assign(String event);

	/** Unassigns a {@link User} from an application. */
	public Response unassign(String event);
}
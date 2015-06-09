package org.app.domain.services;

import org.app.domain.dto.ResponseMessage;
import org.app.domain.model.User;

//--------------------------------------------------------------------------------------------------
/** AssignmentEventService interface.
 *  Declares services related to user assignments event handing. */
//--------------------------------------------------------------------------------------------------
public interface AssignmentEventService
{
	/** Assigns a {@link User} to an application. */
	public ResponseMessage assign(String event);

	/** Unassigns a {@link User} from an application. */
	public ResponseMessage unassign(String event);
}
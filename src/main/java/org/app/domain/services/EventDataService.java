package org.app.domain.services;

import org.app.domain.exceptions.PayloadDataException;
import org.app.domain.model.Creator;
import org.app.domain.model.entities.User;


//--------------------------------------------------------------------------------------------------
/** EventDataService interface.
 *  Declares event handing services. */
//--------------------------------------------------------------------------------------------------
public interface EventDataService
{
	/** Retrieves event payload by sending a GET request to specified URL.
	 *  Parses retrieved event payload and maps it to specified class type.
	 * @throws Exception */
	<T> T getEvent(String url, Class<T> resultType) throws Exception;

	/** Maps {@link Creator} fields to {@link User} fields. */
	public User mapCreatorToUser(Creator creator, User user) throws PayloadDataException;
}
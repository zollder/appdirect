package org.app.domain.services;


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
}
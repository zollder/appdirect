package org.app.domain.exceptions;

//--------------------------------------------------------------------------------------------------------------------------------
/** Not modified exception class implementation.
 *  To be used when the UPDATE or DELETE operation has not effect,
 *  or if the concerned resource by a GET operation has the same lastModificationDate/version
 *  as passed with the request. */
//--------------------------------------------------------------------------------------------------------------------------------

public class NotModifiedException extends BaseException
{
	private static final long serialVersionUID = -3078714263062537007L;

	//--------------------------------------------------------------------------------------------------------------------------------
	/** Default constructor */
	//--------------------------------------------------------------------------------------------------------------------------------
	public NotModifiedException()
	{
		super();
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	/** Constructor with message */
	//--------------------------------------------------------------------------------------------------------------------------------
	public NotModifiedException(String message)
	{
		super(message);
	}
}

package org.app.domain.exceptions;


//--------------------------------------------------------------------------------------------------------------------------------
/** PayloadDataException class implementation.
 *  To be used when the payload data conversion/retrieval fails. */
//--------------------------------------------------------------------------------------------------------------------------------
public class PayloadDataException extends BaseException
{
	private static final long serialVersionUID = 2470837262313645109L;

	//--------------------------------------------------------------------------------------------------------------------------------
	public PayloadDataException(String errorCode)
	{
		super(errorCode);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public PayloadDataException(String errorCode, String errorMessage)
	{
		super(errorCode, errorMessage);
	}
}

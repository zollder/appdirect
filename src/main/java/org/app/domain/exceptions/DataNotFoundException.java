package org.app.domain.exceptions;


//--------------------------------------------------------------------------------------------------------------------------------
/** Data not found exception class implementation.
 *  To be used when a data retrieval fails due to missing requested information. */
//--------------------------------------------------------------------------------------------------------------------------------
public class DataNotFoundException extends BaseException
{
	private static final long serialVersionUID = 1L;

	//--------------------------------------------------------------------------------------------------------------------------------
	public DataNotFoundException(Class<?> name, String code, String message)
	{
		super(name, code, message);
	}
}

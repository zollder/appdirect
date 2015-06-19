package org.app.domain.exceptions;


//--------------------------------------------------------------------------------------------------------------------------------
/** DataExistsException implementation.
*   To be used when an entity is already persisted.. */
//--------------------------------------------------------------------------------------------------------------------------------

public class DataExistsException extends BaseException
{
	private static final long serialVersionUID = 1L;

	//--------------------------------------------------------------------------------------------------------------------------------
	public DataExistsException(Class<?> name, String code, String message)
	{
		super(name, code, message);
	}
}

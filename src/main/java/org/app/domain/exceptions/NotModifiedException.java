package org.app.domain.exceptions;

//--------------------------------------------------------------------------------------------------------------------------------
/** Not modified exception class implementation.
 *  To be used when the UPDATE/PUT or DELETE operation has no effect */
//--------------------------------------------------------------------------------------------------------------------------------
public class NotModifiedException extends BaseException
{
	private static final long serialVersionUID = -3078714263062537007L;

	//--------------------------------------------------------------------------------------------------------------------------------
	public NotModifiedException(Class<?> name)
	{
		super(name);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public NotModifiedException(String message)
	{
		super(message);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public NotModifiedException(Class<?> name, String message)
	{
		super(name, message);
	}
}

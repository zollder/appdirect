package org.app.domain.exceptions;


// --------------------------------------------------------------------------------------------------------
/** Base exception class implementation. */
// --------------------------------------------------------------------------------------------------------
public class BaseException extends Exception
{
	private static final long serialVersionUID = 1L;

	private Class<?> className;
	private String errorCode;

	/** Default with entity name */
	// --------------------------------------------------------------------------------------------------------
	public BaseException(Class<?> name)
	{
		super();
		this.className = name;
	}

	/** Default with error code */
	// --------------------------------------------------------------------------------------------------------
	public BaseException(String code)
	{
		super();
		this.errorCode = code;
	}

	/** Default with error code and message */
	// --------------------------------------------------------------------------------------------------------
	public BaseException(String code, String message)
	{
		super(message);
		this.errorCode = code;
	}

	/** Constructor with entity name and error message */
	// --------------------------------------------------------------------------------------------------------
	public BaseException(Class<?> name, String message)
	{
		super(message);
		this.className = name;
	}

	/** Constructor with entity name, error code and error message */
	// --------------------------------------------------------------------------------------------------------
	public BaseException(Class<?> name, String code, String message)
	{
		super(message);
		this.className = name;
		this.errorCode = code;
	}

	/** Constructor with entity name, error code, error message, and exception */
	// --------------------------------------------------------------------------------------------------------
	public BaseException(Class<?> name, String code, String message, Throwable cause)
	{
		super(message, cause);
		this.className = name;
		this.errorCode = code;
	}

	// --------------------------------------------------------------------------------------------------------
	public String getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(String code)
	{
		this.errorCode = code;
	}

	public String getEntityName()
	{
		return this.className.getSimpleName();
	}

	public void setEntityName(Class<?> entityName)
	{
		this.className = entityName;
	}
}

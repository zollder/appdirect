package org.app.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response
{
	private Boolean success;
	private String errorCode;
	private String message;

	// --------------------------------------------------------------------------------------------------
	public Response()
	{}

	public Response (Boolean success, String message, String errorCode)
	{
		this.errorCode = errorCode;
		this.message = message;
		this.success = success;
	}

	// --------------------------------------------------------------------------------------------------
	public static Response success()
	{
		return new Response(true, null, null);
	}

	public static Response success(String message)
	{
		return new Response(true, message, null);
	}

	public static Response failure(String error, String message)
	{
		return new Response(false, message, error);
	}

	// --------------------------------------------------------------------------------------------------
	public Boolean getSuccess()
	{
		return success;
	}

	public void setSuccess(Boolean success)
	{
		this.success = success;
	}

	public String getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
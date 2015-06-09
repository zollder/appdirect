package org.app.domain.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseMessage
{
	private Boolean success;
	private String errorCode;
	private String message;

	public ResponseMessage () {}

	public ResponseMessage (Boolean success, String message, String errorCode)
	{
		this.errorCode = errorCode;
		this.message = message;
		this.success = success;
	}

	public static ResponseMessage failure(String error, String message)
	{
		return new ResponseMessage(false, message, error);
	}

	public static ResponseMessage success()
	{
		return new ResponseMessage(true, null, null);
	}

	public static ResponseMessage success(String message)
	{
		return new ResponseMessage(true, message, null);
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}

	public Boolean getSuccess()
	{
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
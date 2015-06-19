package org.app.web.exceptions;

import org.apache.log4j.Logger;
import org.app.domain.enums.ErrorCodeEnum;
import org.app.domain.exceptions.DataNotFoundException;
import org.app.domain.exceptions.NotModifiedException;
import org.app.domain.model.Response;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.QueryException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

// --------------------------------------------------------------------------------------------------------------------------------

@ControllerAdvice
public class ExceptionResource
{
	private static Logger log = Logger.getLogger(ExceptionResource.class);

	// ----------------------------------------------------------------------------------------
	public ExceptionResource()
	{}

	// --------------------------------------------------------------------------------------------------------------------------------
	protected Response generateFailureResponse(Exception exception)
	{
		Response violation = new Response();
		if (!StringUtils.isEmpty(exception.getLocalizedMessage()))
			violation.setMessage(exception.getLocalizedMessage());
		else
			violation.setMessage(exception.getMessage());

		violation.setSuccess(false);

		return violation;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public Response illegalArgumentExceptionHandler(IllegalArgumentException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.BAD_REQUEST", exception);
		return new Response(false, HttpStatus.BAD_REQUEST.name(), exception.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Handles both {@link JsonMappingException} and {@link JsonParseException}
	 * but {@link JsonParseException} is hidden by {@link HttpMessageNotReadableException}
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(TypeMismatchException.class)
	@ResponseBody
	public Response typeMismatchException(TypeMismatchException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.BAD_REQUEST", exception);
		String message = String.format("Class: %s, message: %s", exception.getClass().getName(), exception.getCause().getMessage());
		return new Response(false, HttpStatus.BAD_REQUEST.name(), message);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Handles {@link JsonProcessingException}. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(JsonProcessingException.class)
	@ResponseBody
	public Response jsonProcessingExceptionHandler(JsonProcessingException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.BAD_REQUEST", exception);
		return new Response(false, HttpStatus.BAD_REQUEST.name(), "Error processing payload.");
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Handles a case when a DELETE operation is requested but no key is supplied. */
	// ----------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public Response httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.BAD_REQUEST", exception);
		return new Response(false, HttpStatus.BAD_REQUEST.name(), exception.getLocalizedMessage());
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseBody
	public Response objectNotFoundExceptionHandler(ObjectNotFoundException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.NOT_FOUND", exception);
		String message = String.format("Entity name: %s, error message: %s", exception.getEntityName(), exception.getLocalizedMessage());
		return new Response(false, ErrorCodeEnum.ENTITY_NOT_FOUND.name(), message);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseBody
	public Response dataNotFoundExceptionHandler(DataNotFoundException exception)
	{
		log.debug("Unexpected exception: " + exception.getEntityName() + " reported as HttpStatus.NOT_FOUND", exception);
		String message = String.format("Entity: %s, message: %s", exception.getEntityName(), exception.getMessage());
		return new Response(false, exception.getErrorCode(), message);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** For instance when filtering or sorting on an unknown field. */
	// ----------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(QueryException.class)
	@ResponseBody
	public Response queryExceptionHandler(QueryException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.BAD_REQUEST", exception);
		return new Response(false, ErrorCodeEnum.UNKNOWN_ERROR.name(), exception.getLocalizedMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** For instance when inserting a non existing foreign key. */
	// ----------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(TransactionSystemException.class)
	@ResponseBody
	public Response transactionSystemExceptionHandler(Exception exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.BAD_REQUEST", exception);
		return new Response(false, ErrorCodeEnum.UNKNOWN_ERROR.name(), exception.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler()
	public void defaultExceptionHandler(Exception exception)
	{
		log.fatal("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.INTERNAL_SERVER_ERROR",
				exception);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.NOT_MODIFIED)
	@ExceptionHandler(NotModifiedException.class)
	@ResponseBody
	public Response notModifiedExceptionHandler(NotModifiedException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.NOT_MODIFIED", exception);
		return new Response(false, ErrorCodeEnum.UNKNOWN_ERROR.name(), exception.getMessage());
	}
}
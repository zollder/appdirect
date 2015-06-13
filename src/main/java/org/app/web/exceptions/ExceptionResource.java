package org.app.web.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.app.domain.exceptions.BaseException;
import org.app.domain.exceptions.DataNotFoundException;
import org.app.domain.exceptions.EntityExistsException;
import org.app.domain.exceptions.NotModifiedException;
import org.app.domain.exceptions.Violation;
import org.app.domain.exceptions.ViolationList;
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
	protected static final String PAYLOAD_MAL_FORMED_EXCEPTION = "payloadMalFormedException";

	private static Logger log = Logger.getLogger(ExceptionResource.class);

	// ----------------------------------------------------------------------------------------
	public ExceptionResource()
	{}

	// --------------------------------------------------------------------------------------------------------------------------------
	protected ViolationList generateSingleViolation(Exception exception)
	{
		Violation violation = new Violation();
		if (!StringUtils.isEmpty(exception.getLocalizedMessage()))
			violation.setMessage(exception.getLocalizedMessage());
		else
			violation.setMessage(exception.getMessage());

		violation.setEntity(violation.getClass().getSimpleName());
		ViolationList vList = new ViolationList(Arrays.asList(new Violation[] { violation }));

		return vList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	protected ViolationList generateViolationList(BaseException exception)
	{
		// generate singleViolation if there is only one element in the list
		List<Violation> violations = exception.getViolations();
		if (exception.getViolations().isEmpty())
			return generateSingleViolation(exception);

		// add simple class name (temporarily) to violations without messages
		for (Violation violation : violations)
		{
			if (StringUtils.isEmpty(violation.getMessage()))
				violation.setMessage(exception.getClass().getSimpleName());
		}

		ViolationList vList = new ViolationList(violations);
		return vList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ViolationList illegalArgumentExceptionHandler(IllegalArgumentException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.BAD_REQUEST", exception);

		Violation violation = new Violation();
		violation.setMessage(exception.getMessage());

		ViolationList vList = new ViolationList(Arrays.asList(new Violation[] { violation }));
		return vList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * encompasses both {@link JsonMappingException} and {@link JsonParseException}
	 * but {@link JsonParseException} is hidden by {@link HttpMessageNotReadableException}
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(TypeMismatchException.class)
	@ResponseBody
	public ViolationList typeMismatchException(TypeMismatchException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.BAD_REQUEST", exception);

		ViolationList generateSingleViolation = generateSingleViolation(exception);
		Violation violation = generateSingleViolation.getViolations().get(0);

		violation.setMessage(exception.getCause().getMessage());
		violation.setExtendedInfo(exception.getClass().getName());

		return generateSingleViolation;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Encompasses both {@link JsonMappingException} and {@link JsonParseException}
	 * but {@link JsonParseException} is hidden by {@link HttpMessageNotReadableException}
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(JsonProcessingException.class)
	@ResponseBody
	public ViolationList jsonProcessingExceptionHandler(JsonProcessingException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.BAD_REQUEST", exception);

		ViolationList generateSingleViolation = generateSingleViolation(new BaseException(PAYLOAD_MAL_FORMED_EXCEPTION));
		return generateSingleViolation;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** In our particular case, handles exceptions thrown by invalid enum values in saved entities. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public ViolationList HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.BAD_REQUEST", exception);

		Violation violation = new Violation();
		violation.setExtendedInfo(exception.getClass().getSimpleName());
		violation.setMessage(exception.getCause().getMessage());

		ViolationList vList = new ViolationList(Arrays.asList(new Violation[] { violation }));
		return vList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Encountered cases: - a DELETE operation is requested but no key is supplied
	 */
	// ----------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public ViolationList httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.BAD_REQUEST", exception);

		return generateSingleViolation(exception);
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseBody
	public ViolationList objectNotFoundExceptionHandler(ObjectNotFoundException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.NOT_FOUND", exception);

		ViolationList generateSingleViolation = generateSingleViolation(exception);
		generateSingleViolation.getViolations().get(0).setEntity(exception.getEntityName());
		return generateSingleViolation;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseBody
	public ViolationList dataNotFoundExceptionHandler(DataNotFoundException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.NOT_FOUND", exception);


		return generateViolationList(exception);
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ViolationList javaxConstraintViolationExceptionHandler(ConstraintViolationException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.BAD_REQUEST", exception);

		List<Violation> violations = new ArrayList<Violation>();
		for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations())
			violations.add(new Violation(constraintViolation));
		return new ViolationList(violations);
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	/** For instance when filtering or sorting on an unknown field. */
	// ----------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(QueryException.class)
	@ResponseBody
	public ViolationList queryExceptionHandler(QueryException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.BAD_REQUEST", exception);

		Violation violation = new Violation();
		violation.setMessage(exception.getMessage());

		ViolationList vList = new ViolationList(Arrays.asList(new Violation[] { violation }));
		return vList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** For instance when inserting a non existing foreign key. */
	// ----------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(TransactionSystemException.class)
	@ResponseBody
	public ViolationList transactionSystemExceptionHandler(Exception exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.BAD_REQUEST", exception);

		Violation violation = new Violation();
		violation.setMessage(exception.getMessage());

		ViolationList vList = new ViolationList(Arrays.asList(new Violation[] { violation }));
		return vList;
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
	public ViolationList notModifiedExceptionHandler(NotModifiedException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.NOT_MODIFIED", exception);

		return generateViolationList(exception);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.NOT_MODIFIED)
	@ExceptionHandler(EntityExistsException.class)
	@ResponseBody
	public ViolationList entityExistsExceptionHandler(EntityExistsException exception)
	{
		log.debug("Unexpected exception: " + exception.getClass().getName() + " reported as HttpStatus.NOT_MODIFIED", exception);
		ViolationList generateSingleViolation = generateSingleViolation(exception);
		generateSingleViolation.getViolations().get(0).getMessage();
		return generateSingleViolation;
	}
}
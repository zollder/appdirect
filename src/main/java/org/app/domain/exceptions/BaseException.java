package org.app.domain.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.app.domain.dto.Violation;


// --------------------------------------------------------------------------------------------------------
/** Base exception class implementation. */
// --------------------------------------------------------------------------------------------------------
public class BaseException extends RuntimeException
{
	private static final long serialVersionUID = -3737363614162576677L;

	protected List<Violation> violations = new ArrayList<Violation>();

	// --------------------------------------------------------------------------------------------------------
	/** Default constructor */
	// --------------------------------------------------------------------------------------------------------
	public BaseException()
	{
		super();
	}

	// --------------------------------------------------------------------------------------------------------
	/** Constructor with message */
	// --------------------------------------------------------------------------------------------------------
	public BaseException(String message)
	{
		this(message, (Throwable) null);
	}

	// --------------------------------------------------------------------------------------------------------
	/** Constructor with message and root cause */
	// --------------------------------------------------------------------------------------------------------
	public BaseException(String message, Throwable cause)
	{
		super(message, cause);
	}

	// --------------------------------------------------------------------------------------------------------
	/** Constructor with message */
	// --------------------------------------------------------------------------------------------------------
	public BaseException(String message, String violationMessageKey, Object... violationMessageArguments)
	{
		this(message, (Throwable) null, violationMessageKey, violationMessageArguments);
	}

	// --------------------------------------------------------------------------------------------------------
	/** Constructor with message */
	// --------------------------------------------------------------------------------------------------------
	public BaseException(String message, Throwable cause, String violationMessageKey, Object...  arguments)
	{
		this(message, cause);
		Violation violation = new Violation();
		violation.setMessageKey(violationMessageKey);
		violation.setArguments(arguments);
		violations.add(violation);
	}

	// --------------------------------------------------------------------------------------------------------
	/** Constructor with message and reported constraint violations */
	// --------------------------------------------------------------------------------------------------------
	public BaseException(String message, Set<javax.validation.ConstraintViolation<?>> pViolations)
	{
		super(message);
		for (javax.validation.ConstraintViolation<?> violation : pViolations)
			violations.add(new Violation(violation));
	}

	// --------------------------------------------------------------------------------------------------------
	/** Constructor with message and reported constraint violations */
	// --------------------------------------------------------------------------------------------------------
	public BaseException(String message, Violation pViolation)
	{
		super(message);
		violations.add(pViolation);
	}

	// --------------------------------------------------------------------------------------------------------
	/** Constructor with message and reported constraint violations */
	// --------------------------------------------------------------------------------------------------------
	public BaseException(String message, List<Violation> pViolations)
	{
		super(message);
		violations.addAll(pViolations);
	}

	// --------------------------------------------------------------------------------------------------------
	/** Accessor */
	// --------------------------------------------------------------------------------------------------------
	public List<Violation> getViolations()
	{
		return violations;
	}
}

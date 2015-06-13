package org.app.domain.exceptions;

import java.io.Serializable;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

// --------------------------------------------------------------------------------------------------------------------------------
public class Violation implements Serializable
{
	private static final long serialVersionUID = 1607527855319543525L;

	@NotNull
	private String entity;

	private String property;

	private String extendedInfo;

	private Long primaryKey;

	private String message;

	@JsonIgnore
	/**arguments for resolving messageKey*/
	private Object[] arguments;

	// --------------------------------------------------------------------------------------------------------------------------------
	public Violation()
	{}

	public Violation(String entity)
	{
		this();
		this.setEntity(entity);
	}

	public Violation(String entity, String property)
	{
		this(entity);
		this.setProperty(property);
	}

	public Violation(String entity, String property, String extendedInfo)
	{
		this(entity, property);
		this.setExtendedInfo(extendedInfo);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Violation(ConstraintViolation<?> source)
	{
		this();

		entity = source.getRootBeanClass().getName();
		property = source.getPropertyPath() == null ? null : source.getPropertyPath().toString();
		extendedInfo = source.getMessageTemplate();
		message = source.getMessage();
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String toString()
	{
		return "Entity: " + entity + (property == null ? "" : ", Property: " + property) + ", ExtendedInfo: " + extendedInfo + ", Message: " + message;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getEntity()
	{
		return entity;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setEntity(String entity)
	{
		this.entity = entity;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getProperty()
	{
		return property;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setProperty(String property)
	{
		this.property = property;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getMessage()
	{
		return message;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setMessage(String message)
	{
		this.message = message;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getExtendedInfo()
	{
		return extendedInfo;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setExtendedInfo(String extendedInfo)
	{
		this.extendedInfo = extendedInfo;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Long getPrimaryKey()
	{
		return primaryKey;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setPrimaryKey(Long primaryKey)
	{
		this.primaryKey = primaryKey;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Object[] getArguments()
	{
		return arguments;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setArguments(Object[] arguments)
	{
		this.arguments = arguments;
	}
}
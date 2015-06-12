package org.app.domain.model.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//-------------------------------------------------------------------------------------------------------
/** Abstract entity base class. */
//-------------------------------------------------------------------------------------------------------
@MappedSuperclass
public abstract class AbstractBase<T> implements Serializable
{
	// Default serial version ID
	private static final long serialVersionUID = 1L;

	@Id
	@Basic
	@Column(name = "primaryKey")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer primaryKey;

	// --------------------------------------------------------------------------------------------------
	public Integer getPrimaryKey()
	{
		return this.primaryKey;
	}

	public void setPrimaryKey(Integer pk)
	{
		this.primaryKey = pk;
	}

	// --------------------------------------------------------------------------------------------------
	/** Helper functions (for the tests) */
	// --------------------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	protected Class<T> indirectGetClass()
	{
		return (Class<T>) this.getClass();
	}
}
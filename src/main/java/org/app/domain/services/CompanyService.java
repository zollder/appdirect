package org.app.domain.services;

import java.util.List;

import org.app.domain.model.entities.Company;

//--------------------------------------------------------------------------------------------------
/** CompanyService interface.
 *  Declares services related to {@link Company} management. */
//--------------------------------------------------------------------------------------------------
public interface CompanyService
{
	/** Loads {@link Company} entity by primary key (id).
	 *  Throws DataNotFoundException if not found. */
	public Company loadByPrimaryKey(Integer key);

	/** Loads {@link Company} entity by company name. */
	public Company loadByName(String name);

	/** Loads given {@link Company} entity by uuid. */
	public Company loadByUuid(String uuid);

	/** Loads all {@link Company} entities. */
	public List<Company> findAll();

	/** Saves (inserts) given {@link Company} entity. Returns saved entity. */
	public Company save(Company entity);

	/** Updates (merges) given {@link Company} entity. Returns updated entity. */
	public Company update(Company entity);

	/** Deletes {@link Company} entity by specified primary key. */
	public void delete(Integer key);
}
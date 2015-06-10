package org.app.repositories;

import org.app.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//--------------------------------------------------------------------------------------------------
/** Implements Repository for {@link Company} entities. */
//--------------------------------------------------------------------------------------------------
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>
{
	/** Loads given {@link Company} by company name. */
	public Company findByName(String name);

	/** Loads given {@link Company} by UUID. */
	public Company findByUuid(String uuid);
}
package org.app.domain.services.impl;

import java.util.List;

import org.app.domain.exceptions.DataNotFoundException;
import org.app.domain.model.entities.Company;
import org.app.domain.services.CompanyService;
import org.app.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService
{
	@Autowired
	private CompanyRepository companyRepository;

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public Company loadByPrimaryKey(Integer key)
	{
    	Company entity = companyRepository.findOne(key);
		if (entity == null)
			throw new DataNotFoundException("Company not found with key:" + String.valueOf(key));

		return entity;
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public Company loadByName(String name)
	{
    	Company entity = companyRepository.findByName(name);
		if (entity == null)
			throw new DataNotFoundException("Company not found with name:" + name);

		return entity;
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public Company loadByUuid(String uuid)
	{
    	Company entity = companyRepository.findByUuid(uuid);
		if (entity == null)
			throw new DataNotFoundException("Company not found with UUID:" + uuid);

		return entity;
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public List<Company> findAll()
	{
    	List<Company> entity = companyRepository.findAll();
		if ((entity == null) || entity.isEmpty())
		{
			String message = String.format("Company record(s) not found");
			throw new DataNotFoundException(message);
		}

		return entity;
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional
	public Company save(Company entity)
	{
    	return companyRepository.save(entity);
	}

    // ---------------------------------------------------------------------------------------------
    @Override
    @Transactional
    public Company update(Company entity)
    {
    	return save(entity);
    }

    // ---------------------------------------------------------------------------------------------
	@Override
	@Transactional
	public void delete(Integer key)
	{
		if (!companyRepository.exists(key))
			throw new DataNotFoundException("Company not found with key:" + String.valueOf(key));

		companyRepository.delete(key);
	}
}

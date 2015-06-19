package org.app.domain.services.impl;

import java.util.List;

import org.app.domain.enums.ErrorCodeEnum;
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
    	return companyRepository.findOne(key);
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public Company loadByName(String name)
	{
    	return companyRepository.findByName(name);
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public Company loadByUuid(String uuid)
	{
    	return companyRepository.findByUuid(uuid);
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public List<Company> findAll()
	{
    	return companyRepository.findAll();
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
	public void delete(Integer key) throws DataNotFoundException
	{
		if (!companyRepository.exists(key))
		{
			String message = "Company not found, pk:" + String.valueOf(key);
			throw new DataNotFoundException(this.getClass(), ErrorCodeEnum.ENTITY_NOT_FOUND.name(), message);
		}

		companyRepository.delete(key);
	}
}

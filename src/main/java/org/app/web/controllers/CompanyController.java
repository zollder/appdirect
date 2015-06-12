package org.app.web.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.app.domain.model.entities.Company;
import org.app.domain.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//-------------------------------------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping(value = "/company")
public class CompanyController
{
	private static Logger logger = Logger.getLogger(CompanyController.class);
	// ----------------------------------------------------------------------------------------------
	@Autowired
	private CompanyService companyService;

	// ----------------------------------------------------------------------------------------------
	/** TEST */
	// ----------------------------------------------------------------------------------------------
    @RequestMapping("/test")
    public String index()
    {
        return "It works!";
    }

	// ----------------------------------------------------------------------------------------------
	/** Retrieves {@link Company} resource associated to the given key (JSON). */
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.GET })
	@ResponseBody
	public Company loadCompanyByPrimaryKey(@PathVariable Integer key)
	{
		logger.info("load company with primary key:" + key);
		return companyService.loadByPrimaryKey(key);
	}

	// ----------------------------------------------------------------------------------------------
	/** Retrieves {@link Company} resource associated to the given name (JSON). */
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/name/{name}", method = { RequestMethod.GET })
	@ResponseBody
	public Company loadCompanyByName(@PathVariable String name)
	{
		logger.info("load company with name:" + name);
		return companyService.loadByName(name);
	}

	// ----------------------------------------------------------------------------------------------
	/** Retrieves {@link Company} resource associated to the given UUID (JSON). */
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/uuid/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Company loadCompanyByUuid(@PathVariable String id)
	{
		logger.info("load company with UUID:" + id);
		return companyService.loadByUuid(id);
	}

	// ----------------------------------------------------------------------------------------------
	/** Retrieves a collection of all {@link Company} entities. */
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/all", method = { RequestMethod.GET })
	@ResponseBody
	public List<Company> loadAll()
	{
		return companyService.findAll();
	}

	// ----------------------------------------------------------------------------------------------
	/** Inserts the {@link Company} resource received in the payload. */
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(method = { RequestMethod.POST })
	@ResponseBody
	public Company save(@RequestBody Company company)
	{
		logger.info("saving company with UUID:" + company.getUuid());
		return companyService.save(company);
	}

	// ----------------------------------------------------------------------------------------------
	/** Updates the {@link Company} resource. */
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.PUT })
	@ResponseBody
	public Company update(@PathVariable Integer key, @RequestBody Company company)
	{
		logger.info("updating company with openId:" + company.getUuid());
		company.setPrimaryKey(key);
		return companyService.update(company);
	}


	// ----------------------------------------------------------------------------------------------
	/** Deletes the {@link Company} resource associated to a given key. */
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.DELETE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer key)
	{
		logger.info("deleting company with primary key:" + key);
		companyService.delete(key);
	}
}
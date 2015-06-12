package org.app.web.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.app.domain.model.entities.User;
import org.app.domain.services.UserService;
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
@RequestMapping(value = "/user")
public class UserController
{
	private static Logger logger = Logger.getLogger(UserController.class);
	// ----------------------------------------------------------------------------------------------
	@Autowired
	private UserService userService;

	// ----------------------------------------------------------------------------------------------
	/** Retrieves {@link User} resource associated to the given key (JSON). */
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.GET })
	@ResponseBody
	public User loadWithPrimaryKey(@PathVariable Integer key)
	{
		logger.info("load user with primary key:" + key);
		return userService.loadByPrimaryKey(key);
	}

	// ----------------------------------------------------------------------------------------------
	/** Retrieves all Users. Returns a collection of {@link User} entities. */
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/all", method = { RequestMethod.GET })
	@ResponseBody
	public List<User> loadAll()
	{
		return userService.findAll();
	}

	// ----------------------------------------------------------------------------------------------
	/** Inserts the {@link User} resource received in the payload. */
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(method = { RequestMethod.POST })
	@ResponseBody
	public User save(@RequestBody User user)
	{
		logger.info("saving user with openId:" + user.getOpenId());
		return userService.save(user);
	}

	// ----------------------------------------------------------------------------------------------
	/** Updates the {@link User} resource. */
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.PUT })
	@ResponseBody
	public User update(@PathVariable Integer key, @RequestBody User user)
	{
		logger.info("update user with openId:" + user.getOpenId());
		user.setPrimaryKey(key);
		return userService.update(user);
	}


	// ----------------------------------------------------------------------------------------------
	/** Deletes the {@link User} resource associated to a given key. */
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.DELETE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer key)
	{
		logger.info("delete user with primary key:" + key);
		userService.delete(key);
	}
}
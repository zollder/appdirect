package org.app.domain.services.impl;

import java.util.Date;
import java.util.List;

import org.app.domain.exceptions.DataNotFoundException;
import org.app.domain.model.User;
import org.app.domain.services.UserService;
import org.app.repositories.RoleRepository;
import org.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public User loadByPrimaryKey(Integer key)
	{
    	User user = userRepository.findOne(key);
		if (user == null)
		{
			String message = String.format(this.getClass() + " with primary key '%d' not found");
			throw new DataNotFoundException(message, String.valueOf(key));
		}

		return user;
	}

	// ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public User loadByUserName(String username)
	{
    	User user = userRepository.findByUsername(username);
		if (user == null)
		{
			String message = String.format(this.getClass() + " with username '%s' not found");
			throw new DataNotFoundException(message, username);
		}

		return user;
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public User loadByOpenId(String openId)
	{
    	User user = userRepository.findByOpenId(openId);
		if (user == null)
		{
			String message = String.format(this.getClass() + " with openId '%s' not found");
			throw new DataNotFoundException(message, openId);
		}

		return user;
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public List<User> findAll()
	{
    	List<User> users = userRepository.findAll();
		if ((users == null) || users.isEmpty())
		{
			String message = String.format(this.getClass() + "record(s) not found");
			throw new DataNotFoundException(message);
		}

		return users;
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional
	public User save(User user)
	{
    	Integer pk = user.getPrimaryKey();
    	if ((pk != null) && !userRepository.exists(pk))
    		user.setCreationDate(new Date());

    	user.setModificationDate(new Date());

    	return userRepository.save(user);
	}

    // ---------------------------------------------------------------------------------------------
    @Override
    @Transactional
    public User update(User user)
    {
    	// TODO: revise how the password is saved
    	return save(user);
    }

    // ---------------------------------------------------------------------------------------------
	@Override
	@Transactional
	public void delete(Integer key)
	{
		if (!userRepository.exists(key))
			throw new DataNotFoundException(String.format("entity with key '%s' not found", key));

		userRepository.delete(key);
	}

	// ---------------------------------------------------------------------------------------------
//	@Transactional
//	public User resetPassword(Long primaryKey, PasswordReset resetPassword)
//	{
//		User entity = loadWithPrimaryKey(primaryKey);
//		entity.resetPassword(resetPassword);
//		update(entity);
//
//		return entity;
//	}





}

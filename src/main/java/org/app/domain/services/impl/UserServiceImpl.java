package org.app.domain.services.impl;

import java.util.List;

import org.app.domain.exceptions.DataNotFoundException;
import org.app.domain.model.User;
import org.app.domain.services.UserService;
import org.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;

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
    	return userRepository.save(user);
	}

    // ---------------------------------------------------------------------------------------------
    @Override
    @Transactional
    public User update(User user)
    {
    	return save(user);
    }

    // ---------------------------------------------------------------------------------------------
	@Override
	@Transactional
	public void delete(Integer key)
	{
		if (!userRepository.exists(key))
			throw new DataNotFoundException(String.format("entity with key '%d' not found", key));

		userRepository.delete(key);
	}
}

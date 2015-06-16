package org.app.domain.services.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityExistsException;

import org.app.domain.exceptions.DataNotFoundException;
import org.app.domain.model.entities.User;
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
    	return userRepository.findOne(key);
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public User loadByOpenId(String openId)
	{
    	return userRepository.findByOpenId(openId);
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public User loadByAccountId(String accountId)
	{
    	return userRepository.findByAccountId(accountId);
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public List<User> findAll()
	{
    	return userRepository.findAll();
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional
	public User save(User user) throws EntityExistsException
	{
    	if (userRepository.findByOpenId(user.getOpenId()) != null)
    		throw new EntityExistsException("User already exists.");

    	// generate account ID
    	user.setAccountId(UUID.randomUUID().toString());

    	return userRepository.save(user);
	}

    // ---------------------------------------------------------------------------------------------
    @Override
    @Transactional
    public User update(User user)
    {
    	return userRepository.save(user);
    }

    // ---------------------------------------------------------------------------------------------
	@Override
	@Transactional
	public void delete(Integer key)
	{
		if (!userRepository.exists(key))
			throw new DataNotFoundException(String.format("User not found, key: " + String.valueOf(key)));

		userRepository.delete(key);
	}
}

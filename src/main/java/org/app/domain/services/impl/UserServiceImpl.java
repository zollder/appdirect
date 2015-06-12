package org.app.domain.services.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityExistsException;

import org.app.domain.enums.AccountStatusEnum;
import org.app.domain.exceptions.DataNotFoundException;
import org.app.domain.model.Account;
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
    	User user = userRepository.findOne(key);
		if (user == null)
			throw new DataNotFoundException(String.format(this.getClass() + " with primary key '%d' not found"), String.valueOf(key));

		return user;
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public User loadByOpenId(String openId)
	{
    	User user = userRepository.findByOpenId(openId);
		if (user == null)
			throw new DataNotFoundException(String.format(this.getClass() + " with openId '%s' not found"), openId);

		return user;
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public User loadByAccountId(String accountId)
	{
    	User user = userRepository.findByAccountId(accountId);
		if (user == null)
			throw new DataNotFoundException(String.format(this.getClass() + " with accountId '%s' not found"), accountId);

		return user;
	}

    // ---------------------------------------------------------------------------------------------
    @Override
	@Transactional(readOnly = true)
	public List<User> findAll()
	{
    	List<User> users = userRepository.findAll();
		if ((users == null) || users.isEmpty())
			throw new DataNotFoundException(String.format(this.getClass() + "record(s) not found"));

		return users;
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
			throw new DataNotFoundException(String.format("entity with key '%d' not found", key));

		userRepository.delete(key);
	}

	// ---------------------------------------------------------------------------------------------
	@Override
	public Account getUserAccount(Integer key)
	{
		if (!userRepository.exists(key))
			throw new DataNotFoundException(String.format("entity with key '%d' not found", key));

		User entity = userRepository.findOne(key);

		Account userAccount = new Account();
		userAccount.setAccountId(entity.getAccountId());
		userAccount.setStatus(AccountStatusEnum.ACTIVE);

		return userAccount;
	}
}

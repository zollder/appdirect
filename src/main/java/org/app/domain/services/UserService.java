package org.app.domain.services;

import java.util.List;

import org.app.domain.model.Account;
import org.app.domain.model.entities.User;


//--------------------------------------------------------------------------------------------------
/** UserService interface.
 *  Declares available user management services. */
//--------------------------------------------------------------------------------------------------
public interface UserService
{
	/** Loads {@link User} entity by primary key (id). Throws DataNotFoundException. */
	public User loadByPrimaryKey(Integer key);

	/** Loads given {@link User} entity by openId. */
	public User loadByOpenId(String openId);

	/** Loads given {@link User} entity by accountId. */
	User loadByAccountId(String accountId);

	/** Loads all {@link User} entities. */
	public List<User> findAll();

	/** Saves (inserts) given {@link User} entity. Returns saved entity. */
	public User save(User entity);

	/** Updates (merges) given {@link User} entity. Returns updated entity. */
	public User update(User entity);

	/** Deletes {@link User} entity by specified primary key. */
	public void delete(Integer key);

	/** returns {@link Account} by specified {@link User} primary key. */
	public Account getUserAccount(Integer key);
}
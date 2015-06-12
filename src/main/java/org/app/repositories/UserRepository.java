package org.app.repositories;

import org.app.domain.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implements Repository for {@link User} entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
	/** Loads given {@link User} by email. */
	public User findByEmail(String email);

	/** Loads given {@link User} by openId. */
	public User findByOpenId(String openId);

	/** Loads given {@link User} by accountId. */
	public User findByAccountId(String accountId);
}
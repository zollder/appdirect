package org.app.repositories;

import org.app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Implements Repository for {@link User} objects.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
	/** Loads given {@link User} by username. */
	public User findByUsername(String username);

	/** Loads given {@link User} by email. */
	public User findByEmail(String email);

	/** Loads given {@link User} by openId. */
	public User findByOpenId(String openId);
}
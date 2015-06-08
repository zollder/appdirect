package org.app.repositories;

import org.app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Implements Repository for {@link Role} objects.
 */
@Repository
public interface RoleRepository extends JpaRepository<User, Integer>
{}
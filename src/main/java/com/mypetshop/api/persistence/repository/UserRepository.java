package com.mypetshop.api.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypetshop.api.persistence.model.User;

/**
 * Interface which contains the methods to interact with the User model.
 * @author Alexandre Sousa (abdesousa@gmail.com)
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
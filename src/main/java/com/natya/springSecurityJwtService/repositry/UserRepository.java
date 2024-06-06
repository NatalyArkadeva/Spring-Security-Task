package com.natya.springSecurityJwtService.repositry;

import com.natya.springSecurityJwtService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    boolean existsByEmail(String email);
}

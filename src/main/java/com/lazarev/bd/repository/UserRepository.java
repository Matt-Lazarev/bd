package com.lazarev.bd.repository;

import com.lazarev.bd.security.ApplicationUser;
import com.lazarev.bd.security.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> getUserByLogin(String login);
    List<ApplicationUser> findByRoleIs(UserRole role);
}

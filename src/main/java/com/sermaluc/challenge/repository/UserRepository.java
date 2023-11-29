package com.sermaluc.challenge.repository;

import com.sermaluc.challenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

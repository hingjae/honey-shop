package com.example.honeyshop.repository;

import com.example.honeyshop.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

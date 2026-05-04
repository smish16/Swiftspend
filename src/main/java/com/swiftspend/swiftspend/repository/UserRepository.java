package com.swiftspend.swiftspend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.swiftspend.swiftspend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
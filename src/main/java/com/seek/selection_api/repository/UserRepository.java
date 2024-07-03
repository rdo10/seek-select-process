package com.seek.selection_api.repository;

import com.seek.selection_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

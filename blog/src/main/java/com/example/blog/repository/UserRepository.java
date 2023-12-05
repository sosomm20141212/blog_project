package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.blog.model.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, String>{
    User findByUserIdAndPassword(String userId, String password);
    List<User> findByPasswordAndUserId(String password, String userId);
    List<User> findByUserId(String userId);
}
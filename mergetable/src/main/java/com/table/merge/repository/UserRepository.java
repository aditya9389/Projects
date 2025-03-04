package com.table.merge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.table.merge.Model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    Optional<User> findByUserId(Integer userId);
    Optional<User> findByUsername(String username);
}

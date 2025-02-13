package com.microservices.Authentication.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.Authentication.model.AppUser;


@Repository
public interface UserRepository extends JpaRepository<AppUser, String> {

}
package com.crud.fnpblog.repository;

import com.crud.fnpblog.model.Google_Login;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface Google_Login_Repository extends JpaRepository<Google_Login,Long>{
    Optional<Google_Login> findById(long id) ;
    Optional<Google_Login> findByUsername(String username);

}

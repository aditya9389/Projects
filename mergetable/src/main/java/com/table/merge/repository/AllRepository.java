package com.table.merge.repository;

import com.table.merge.Model.OneForAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AllRepository extends JpaRepository<OneForAll,Integer>{
    Optional<OneForAll> findByUserId(Integer userId);
}

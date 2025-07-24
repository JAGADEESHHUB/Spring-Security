package com.spring.security.repository;

import com.spring.security.entity.UserD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserD,Long> {

    Optional<UserD> findByUserName(String userName);
}

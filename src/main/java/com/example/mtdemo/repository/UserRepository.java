package com.example.mtdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.mtdemo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

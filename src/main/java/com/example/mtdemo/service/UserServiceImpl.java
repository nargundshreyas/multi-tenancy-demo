package com.example.mtdemo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mtdemo.domain.User;
import com.example.mtdemo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository repository;

  @Override
  public List<User> getAllUsers() {
    return this.repository.findAll();
  }

}

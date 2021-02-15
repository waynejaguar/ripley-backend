package com.ripley.authlogin.user.service;

import com.ripley.authlogin.user.model.User;
import com.ripley.authlogin.user.repository.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

  private final UserRepository repository;

  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<User> getByRut(String rut, String password) {
    return this.repository.findByUserName(rut, password);
  }
}

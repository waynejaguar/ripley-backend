package com.ripley.authlogin.user.service;

import com.ripley.authlogin.user.model.User;
import java.io.IOException;
import java.util.Optional;



public interface UserService {
  Optional<User> getByRut(String rut, String password);

  void saveUser(User user);

  boolean isUserExist(User user);

  void accountRecovery(User user) throws IOException;

}

package com.ripley.authlogin.user.service;

import com.ripley.authlogin.user.model.User;
import java.util.Optional;



public interface UserService {
  Optional<User> getByRut(String rut, String password);

}

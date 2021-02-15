package com.ripley.authlogin.loging.service;

import com.ripley.authlogin.loging.request.LoginRequest;
import com.ripley.authlogin.loging.response.LoginResponse;
import com.ripley.authlogin.user.model.User;
import com.ripley.authlogin.user.service.UserService;
import java.util.Optional;
import javax.security.auth.login.LoginException;
import org.springframework.stereotype.Service;


@Service
public class LogingServiceImpl implements  LoginService{


  private final UserService userService;

  public LogingServiceImpl(UserService userService) {
    this.userService = userService;
  }


  @Override
  public LoginResponse login(LoginRequest request) throws LoginException {
    final Optional<User> user = this.userService.getByRut(request.getUsername(), request.getPassword());

    if (user.isEmpty()) {
      throw new LoginException("User or password not exist in ripley");
    }

    return new LoginResponse("12345");


  }
}

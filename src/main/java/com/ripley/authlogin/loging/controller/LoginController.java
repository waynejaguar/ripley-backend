package com.ripley.authlogin.loging.controller;

import com.ripley.authlogin.loging.request.LoginRequest;
import com.ripley.authlogin.loging.response.LoginResponse;
import com.ripley.authlogin.loging.service.LoginService;
import javax.security.auth.login.LoginException;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class LoginController {

  private final LoginService loginService;

  public LoginController(final LoginService loginService) {
    this.loginService = loginService;
  }


  @PostMapping(path = "/login")
  public LoginResponse login(@RequestBody @Valid final LoginRequest request) throws LoginException {
    return this.loginService.login(request);
  }

}

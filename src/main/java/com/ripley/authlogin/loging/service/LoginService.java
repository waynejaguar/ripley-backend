package com.ripley.authlogin.loging.service;

import com.ripley.authlogin.loging.request.LoginRequest;
import com.ripley.authlogin.loging.response.LoginResponse;
import javax.security.auth.login.LoginException;

public interface LoginService  {

  LoginResponse login(LoginRequest request) throws LoginException;
}

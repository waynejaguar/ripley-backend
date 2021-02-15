package com.ripley.authlogin.loging.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.ripley.authlogin.loging.exeception.LoginException;
import com.ripley.authlogin.loging.request.LoginRequest;
import com.ripley.authlogin.loging.response.LoginResponse;
import com.ripley.authlogin.loging.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

  @Mock
  private LoginService service;

  private LoginController controller;


  private final LoginResponse successfulLoginResponse = new LoginResponse("my fake token");
  private final LoginRequest successfulLoginRequest = new LoginRequest("user1", "pass1");
  private final LoginRequest failedLoginRequest = new LoginRequest("badUser", "basPass");
  private final LoginRequest unknownUserLoginRequest = new LoginRequest("unknownUser", "ripley313");
  private final LoginRequest emptyCredentialsLoginRequest = new LoginRequest("", "");


  @BeforeEach
  public void setUp() {
    this.controller = new LoginController(this.service);
  }


  @Test
  public void shouldReturnTheLoginResponseWhenSendingGoodCredentials() throws Exception {
    when(this.service.login(this.successfulLoginRequest))
        .thenReturn(this.successfulLoginResponse);

    final LoginResponse loginResponse = this.controller.login(this.successfulLoginRequest);

    assertEquals(loginResponse.getToken(), this.successfulLoginResponse.getToken());
  }


}

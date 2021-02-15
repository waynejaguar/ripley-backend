package com.ripley.authlogin.loging.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class LoginResponse {

  private String token;

  public LoginResponse(final String token) {
    this.token = token;
  }

}
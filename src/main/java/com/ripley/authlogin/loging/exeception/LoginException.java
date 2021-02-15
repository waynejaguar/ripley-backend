package com.ripley.authlogin.loging.exeception;

import com.ripley.authlogin.core.exception.RipleyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    code = HttpStatus.UNAUTHORIZED,
    reason = "failed to login"
)
public class LoginException  extends RipleyException {

  public LoginException(final String message) {
    super(message);
  }
}

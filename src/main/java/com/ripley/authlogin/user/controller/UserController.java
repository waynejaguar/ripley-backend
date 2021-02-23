package com.ripley.authlogin.user.controller;


import com.ripley.authlogin.user.model.User;
import com.ripley.authlogin.user.service.UserService;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = "/api/v1")
@Slf4j
public class UserController {


  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  ///users/account-recovery

  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
    log.info("Creating User : {}", user);

    if (service.isUserExist(user)) {
      log.error("Unable to create. A User with name {} already exist", user.getName());
      return new ResponseEntity<String>(HttpStatus.CONFLICT);

    }
    service.saveUser(user);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
    return new ResponseEntity<String>(headers, HttpStatus.CREATED);
  }


  @PostMapping(path = "/user/account-recovery")
  @ResponseStatus( HttpStatus.OK )
  public void accountRecovery(@RequestBody User user) throws IOException {
    this.service.accountRecovery(user);

  }

}

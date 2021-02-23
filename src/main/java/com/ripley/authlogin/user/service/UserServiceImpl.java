package com.ripley.authlogin.user.service;

import com.ripley.authlogin.mail.service.MailService;
import com.ripley.authlogin.user.model.User;
import com.ripley.authlogin.user.repository.UserRepository;
import java.io.IOException;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

  private final UserRepository repository;
  private final MailService mailService;

  public UserServiceImpl(UserRepository repository,
      MailService mailService) {
    this.repository = repository;
    this.mailService = mailService;
  }

  @Override
  public Optional<User> getByRut(String rut, String password) {
    return this.repository.findByUserName(rut, password);
  }

  @Override
  public void saveUser(User user) {
    this.repository.save(user);
  }

  @Override
  public boolean isUserExist(User user) {

  return !this.repository.findOneByUserName(user.getUsername()).isEmpty();
  }

  @Override
  public void accountRecovery(User user) throws IOException {

    Optional<User> oneByUserName = this.repository.findOneByUserName(user.getUsername());
    String subject = "recuperar cuenta";
    String emailTo = oneByUserName.get().getEmailUser();
    String contrasena = oneByUserName.get().getPassword();
    this.mailService.sendEmail(subject, emailTo, contrasena);

  }
}

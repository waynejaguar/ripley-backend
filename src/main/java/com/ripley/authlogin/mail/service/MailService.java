package com.ripley.authlogin.mail.service;

import java.io.IOException;

public interface MailService {

  void sendEmail(String subject, String emailTo, String password) throws IOException;

}

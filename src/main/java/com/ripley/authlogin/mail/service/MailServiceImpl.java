package com.ripley.authlogin.mail.service;


import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{

  @Override
  public void sendEmail(String subject, String emailTo, String password ) throws IOException {


    Email from = new Email("waynejaguar@gmail.com");

    Email to = new Email(emailTo);
    Content content = new Content("text/plain", "este es un correo de banco ripley su contrasena es:" + password);
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid(System.getenv("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"));
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      //TODO: quitar syso
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
      throw ex;
    }

  }
}

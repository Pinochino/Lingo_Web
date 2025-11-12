package com.lingo.notificationservice.service;

import com.lingo.notificationservice.dto.request.AccountMessage;
import com.lingo.notificationservice.dto.request.RequestMailDTO;
import com.lingo.notificationservice.utils.Constants;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class EmailService {
  @Autowired
  private JavaMailSender sender;

  @Autowired
  private Configuration config;

  @Async
  public void sendOTPCode(RequestMailDTO request) throws MessagingException {
    Map<String, Object> model = new HashMap<>();
    model.put("email", request.getTo());
    model.put("code", request.getContent());

    this.sendEmail(request.getTo(), Constants.MailContent.MAIL_WELCOME, model, "email-template.ftl");
  }
  @RabbitListener(queues = "${notification.new-account-queue}")
  public void sendWelcome(AccountMessage accountMessage) throws MessagingException {
    log.info("Sending welcome mail to {}", accountMessage.getEmail());
    Map<String, Object> model = new HashMap<>();
    model.put("email", accountMessage.getEmail());
    model.put("username", accountMessage.getUsername());

    this.sendEmail(accountMessage.getEmail(), Constants.MailContent.MAIL_WELCOME, model, "welcome-template.ftl");
  }

  @Async
  public void sendEmail(String to, String subject, Map<String, Object> model, String template) throws MessagingException {
    long startTime = System.currentTimeMillis();

    MimeMessage message = sender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
              StandardCharsets.UTF_8.name());
//      helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

      Template t = config.getTemplate(template);
      String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

      helper.setTo(to);
      helper.setText(html, true);
      helper.setSubject(subject);
      sender.send(message);
      log.info("Mail sent successfully to {}", to);
      log.info("Time counting: {}", System.currentTimeMillis() - startTime);

    } catch (MessagingException | IOException | TemplateException e) {
      log.info("Mail sent unsuccessfully to {}", to);
      throw new MessagingException(Constants.ErrorCode.MAIL_UNSUCCESSFUL);
    }
  }
}

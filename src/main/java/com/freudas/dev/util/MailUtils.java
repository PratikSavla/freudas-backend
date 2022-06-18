package com.freudas.dev.util;

//import org.springframework.context.annotation.Bean;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//@Component
public class MailUtils {
//    final private JavaMailSenderImpl mailSender;
//
//    public MailUtils() {
//        this.mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//
//        mailSender.setUsername("freudas.mail@gmail.com");
//        mailSender.setPassword("20020a056a0008ce00b004f66dcd4f1csm13212777");
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//    }
//
//    public void sendMessage(
//            String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("freudas.mail@gmail.com");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);
//    }
//
//    public void sendVerificationEmail(String toMail, String key) throws MessagingException {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//        String htmlMsg = "<h3>Welcome!</h3><p>We're excited to have you get started. " +
//                "First, you need to confirm your account. <a href=\"http://localhost:3000/verify-email?email="
//                +toMail+"&resetKey="+key+"\" class=\"button\">Click here</a>.</p>";
//        mimeMessage.setContent(htmlMsg, "text/html"); // Use this or below line
////        helper.setText(htmlMsg, true); // Use this or above line.
//        helper.setTo(toMail);
//        helper.setSubject("Welcome to Freudas!");
//        helper.setFrom("freudas.mail@gmail.com");;
//        mailSender.send(mimeMessage);
//    }
}

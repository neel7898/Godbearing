package com.godbearing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	 @Autowired
	 public JavaMailSender emailSender;
	 
	 public void sendSimpleMessage(String to, String subject, String text) {
		 System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		        SimpleMailMessage message = new SimpleMailMessage(); 
		        message.setTo(to); 
		        message.setSubject(subject); 
		        message.setText(text);
		        emailSender.send(message);
	 }
	
}

package com.companheirosautocenter.appautocenter.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	/**
	 * Link para desbloquear a conta google
	 * 
	 * @see 
	 * 
	 * {@link  https://accounts.google.com/b/0/DisplayUnlockCaptcha}
	 */
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando de email...");
		mailSender.send(msg);
		LOG.info("Email enviando");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Enviando de email...");
		javaMailSender.send(msg);
		LOG.info("Email enviando");
	}

}

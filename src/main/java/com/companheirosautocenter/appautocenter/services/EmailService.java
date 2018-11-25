package com.companheirosautocenter.appautocenter.services;


import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.companheirosautocenter.appautocenter.domain.Pedido;
import com.companheirosautocenter.appautocenter.domain.Pessoa;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Pessoa pessoa, String newPass);
}
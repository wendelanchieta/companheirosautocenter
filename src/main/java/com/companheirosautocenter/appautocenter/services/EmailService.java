package com.companheirosautocenter.appautocenter.services;


import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.companheirosautocenter.appautocenter.domain.Cliente;
import com.companheirosautocenter.appautocenter.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
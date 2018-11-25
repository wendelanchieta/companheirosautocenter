package com.companheirosautocenter.appautocenter.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.companheirosautocenter.appautocenter.domain.Pessoa;
import com.companheirosautocenter.appautocenter.repositories.PessoaRepository;
import com.companheirosautocenter.appautocenter.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public Pessoa findPessoaByLogin(String login) {
		Pessoa pessoa = pessoaRepository.findByLogin(login);
		if(pessoa == null) {
			throw new ObjectNotFoundException("Login não encontrado");
		}
		return pessoa;
	}

	public void sendNewPassword(String login) {
		
		Pessoa pessoa = pessoaRepository.findByLogin(login);
		if(pessoa == null) {
			throw new ObjectNotFoundException("Login não encontrado");
		} else if(pessoa.getEmail() == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		pessoa.setSenha(pe.encode(newPass));
		pessoaRepository.save(pessoa);
		
		emailService.sendNewPasswordEmail(pessoa, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	/**
	 * Gerado de caracteres aleatorios
	 * 
	 * @see {@link <a href="https://unicode-table.com">https://unicode-table.com</a>}
	 * 
	 * @return char
	 */
	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}else if(opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}

package com.companheirosautocenter.appautocenter.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.companheirosautocenter.appautocenter.domain.Pessoa;
import com.companheirosautocenter.appautocenter.services.AuthService;

/**
 * @see {@link <a href="https://www.thymeleaf.org/doc/articles/springsecurity.html">https://www.thymeleaf.org/doc/articles/springsecurity.html</a>}
 * 
 * @author wendel.anchieta
 *
 */
@RestController
@RequestMapping(value = "/login")
public class LoginResource {
	
	@Autowired
	private AuthService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		System.out.println("Entrei no Login");
		Pessoa pessoa = service.findPessoaByLogin(username);
		System.out.println("Usuario: " + pessoa.getLogin() + "\nEmail: " + pessoa.getEmail());
		return "home";
	}
}

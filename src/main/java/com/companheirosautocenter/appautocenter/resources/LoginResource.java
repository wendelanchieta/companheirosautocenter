package com.companheirosautocenter.appautocenter.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.companheirosautocenter.appautocenter.domain.Pessoa;
import com.companheirosautocenter.appautocenter.services.AuthService;

@RestController
@RequestMapping(value = "/login")
public class LoginResource {
	
	@Autowired
	private AuthService service;
	
	@RequestMapping(value = "/newpage", method = RequestMethod.GET)
    public String newPage() {
        return "newpage";
    }

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> login(@RequestParam("username") String username, @RequestParam("password") String password) {
		System.out.println("Entrei no Login");
		Pessoa pessoa = service.findPessoaByLogin(username);
		System.out.println("Usuario: " + pessoa.getLogin() + "\nEmail: " + pessoa.getEmail());
		return ResponseEntity.noContent().build();
	}
}

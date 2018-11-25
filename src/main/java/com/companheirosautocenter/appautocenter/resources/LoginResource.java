package com.companheirosautocenter.appautocenter.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.companheirosautocenter.appautocenter.domain.Pessoa;
import com.companheirosautocenter.appautocenter.dto.PessoaDTO;
import com.companheirosautocenter.appautocenter.services.PessoaFisicaService;

@RestController
@RequestMapping(value = "/login")
public class LoginResource {
	
	@Autowired
	private PessoaFisicaService service;
	
	@RequestMapping(value = "/newpage", method = RequestMethod.GET)
    public String newPage() {
        System.out.println("Showing The Redirected Page");
        return "home";
    }

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> login(@Valid @RequestBody PessoaDTO dto) {
		Pessoa obj = service.fromDTO(dto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}

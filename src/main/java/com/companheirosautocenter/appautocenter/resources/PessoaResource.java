package com.companheirosautocenter.appautocenter.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companheirosautocenter.appautocenter.services.PessoaFisicaService;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResource {
	
	@Autowired
	private PessoaFisicaService service;
	

}

package com.companheirosautocenter.appautocenter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companheirosautocenter.appautocenter.domain.Cidade;
import com.companheirosautocenter.appautocenter.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findAll() {
		return repo.findAll();
	}
	
	public List<Cidade> findAllByOrderByNomeAsc() {
		return repo.findAllByOrderByNomeAsc();
	}
	
	public List<Cidade> findByNomeOrderByNomeDesc(String nome) {
		return repo.findByNomeOrderByNomeDesc(nome);
	}
}

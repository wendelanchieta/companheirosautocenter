package com.companheirosautocenter.appautocenter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companheirosautocenter.appautocenter.domain.Estado;
import com.companheirosautocenter.appautocenter.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repo;
	
	public List<Estado> findAll() {
		return repo.findAll();
	}
	
	public List<Estado> findAllByOrderByUfAsc() {
		return repo.findAllByOrderByUfAsc();
	}
}

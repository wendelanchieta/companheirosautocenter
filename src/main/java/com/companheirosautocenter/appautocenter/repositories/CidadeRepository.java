package com.companheirosautocenter.appautocenter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.companheirosautocenter.appautocenter.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	List<Cidade> findAllByOrderByNomeAsc();
	
	List<Cidade> findByNomeOrderByNomeDesc(String nome);
}

package com.companheirosautocenter.appautocenter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.companheirosautocenter.appautocenter.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
	List<Estado> findAllByOrderByUfAsc();
}

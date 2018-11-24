package com.companheirosautocenter.appautocenter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.companheirosautocenter.appautocenter.domain.Cliente;
import com.companheirosautocenter.appautocenter.repositories.ClienteRepository;
import com.companheirosautocenter.appautocenter.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cliente = repo.findByEmail(email);
		if(cliente == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cliente.getId(),cliente.getEmail(),cliente.getSenha(),cliente.getPerfis());
	}

}

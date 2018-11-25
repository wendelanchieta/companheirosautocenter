package com.companheirosautocenter.appautocenter.domain;

import javax.persistence.Entity;

import com.companheirosautocenter.appautocenter.domain.enums.Perfil;
import com.companheirosautocenter.appautocenter.domain.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pessoafisica")
public class PessoaFisica extends Pessoa {

	private static final long serialVersionUID = 1L;

	private String cpf;

	public PessoaFisica() {

	}

	public PessoaFisica(Integer id, String nome, String email, String login, String senha, TipoPessoa tipo, String cpf) {
		super(id, nome, email, login, senha, tipo);
		this.cpf=cpf;		
		addPerfil(Perfil.CLIENTE);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}

package com.companheirosautocenter.appautocenter.domain;

import javax.persistence.Entity;

import com.companheirosautocenter.appautocenter.domain.enums.Perfil;
import com.companheirosautocenter.appautocenter.domain.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pessoaJuridica")
public class PessoaJuridica extends Pessoa {

	private static final long serialVersionUID = 1L;

	private String cnpj;

	public PessoaJuridica() {

	}

	public PessoaJuridica(Integer id, String nome, String email, String login, String senha, TipoPessoa tipo, String cnpj) {
		super(id, nome, email, login, senha, tipo);
		this.cnpj=cnpj;
		
		addPerfil(Perfil.CLIENTE);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}

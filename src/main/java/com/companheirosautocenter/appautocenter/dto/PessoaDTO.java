package com.companheirosautocenter.appautocenter.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.companheirosautocenter.appautocenter.domain.Pessoa;
import com.companheirosautocenter.appautocenter.validation.ClienteUpdate;

@ClienteUpdate
public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	//@NotEmpty(message="Preenchimento obrigatório")
	//@Length(min=5, max=120, message="o tamanho deve ser entre 5 e 120 caracteres.")
	private String nome;
	//@NotEmpty(message="Preenchimento obrigatório")
	private String login;
	private String senha;
	//@Email(message="Email inválido")
	private String email;
	
	public PessoaDTO() {
	}

	public PessoaDTO(Integer id, String nome, String login, String senha, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

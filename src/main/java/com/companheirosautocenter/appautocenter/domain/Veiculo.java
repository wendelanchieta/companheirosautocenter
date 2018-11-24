/**
 * 
 */
package com.companheirosautocenter.appautocenter.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String marca;

	private String modelo;

	private Integer ano;
	
	private String placa;
	
	private Long kilometragem;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "COMBUSTIVEL")
	private Set<Integer> combustiveis = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Long getKilometragem() {
		return kilometragem;
	}

	public void setKilometragem(Long kilometragem) {
		this.kilometragem = kilometragem;
	}

	public Set<Integer> getCombustiveis() {
		return combustiveis;
	}

	public void setCombustiveis(Set<Integer> combustiveis) {
		this.combustiveis = combustiveis;
	} 
	
}

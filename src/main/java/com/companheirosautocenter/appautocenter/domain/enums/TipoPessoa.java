package com.companheirosautocenter.appautocenter.domain.enums;

public enum TipoPessoa {
	
	FISICA(1, "Física"), 
	JURIDICA(2, "Jurídica");

	private int cod;
	private String descricao;
	
	private TipoPessoa(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoPessoa toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoPessoa x:TipoPessoa.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}

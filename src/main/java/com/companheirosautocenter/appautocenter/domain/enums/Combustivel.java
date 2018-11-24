package com.companheirosautocenter.appautocenter.domain.enums;

public enum Combustivel {
	
	GASOLINA(1, "Gasolina"), 
	ETANOL(2, "Etanol"),
	FLEX(2, "Flex");

	private int cod;
	private String descricao;
	
	private Combustivel(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Combustivel toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(Combustivel x:Combustivel.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}

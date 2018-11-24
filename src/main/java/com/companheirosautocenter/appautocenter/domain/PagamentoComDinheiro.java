package com.companheirosautocenter.appautocenter.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.companheirosautocenter.appautocenter.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComDinheiro")
public class PagamentoComDinheiro extends Pagamento{
	
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataPagamento;
	
	public  PagamentoComDinheiro() {
		
	}

	public PagamentoComDinheiro(Integer id, EstadoPagamento estado, Pedido pedido, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
}

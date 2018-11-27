package com.companheirosautocenter.appautocenter.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.companheirosautocenter.appautocenter.domain.Categoria;
import com.companheirosautocenter.appautocenter.domain.Cidade;
import com.companheirosautocenter.appautocenter.domain.Endereco;
import com.companheirosautocenter.appautocenter.domain.Estado;
import com.companheirosautocenter.appautocenter.domain.ItemPedido;
import com.companheirosautocenter.appautocenter.domain.Pagamento;
import com.companheirosautocenter.appautocenter.domain.PagamentoComBoleto;
import com.companheirosautocenter.appautocenter.domain.PagamentoComCartao;
import com.companheirosautocenter.appautocenter.domain.PagamentoComDinheiro;
import com.companheirosautocenter.appautocenter.domain.Pedido;
import com.companheirosautocenter.appautocenter.domain.Pessoa;
import com.companheirosautocenter.appautocenter.domain.PessoaFisica;
import com.companheirosautocenter.appautocenter.domain.Produto;
import com.companheirosautocenter.appautocenter.domain.Veiculo;
import com.companheirosautocenter.appautocenter.domain.enums.Combustivel;
import com.companheirosautocenter.appautocenter.domain.enums.EstadoPagamento;
import com.companheirosautocenter.appautocenter.domain.enums.Perfil;
import com.companheirosautocenter.appautocenter.domain.enums.TipoPessoa;
import com.companheirosautocenter.appautocenter.repositories.CategoriaRepository;
import com.companheirosautocenter.appautocenter.repositories.CidadeRepository;
import com.companheirosautocenter.appautocenter.repositories.EnderecoRepository;
import com.companheirosautocenter.appautocenter.repositories.EstadoRepository;
import com.companheirosautocenter.appautocenter.repositories.ItemPedidoRepository;
import com.companheirosautocenter.appautocenter.repositories.PagamentoRepository;
import com.companheirosautocenter.appautocenter.repositories.PedidoRepository;
import com.companheirosautocenter.appautocenter.repositories.PessoaRepository;
import com.companheirosautocenter.appautocenter.repositories.ProdutoRepository;
import com.companheirosautocenter.appautocenter.repositories.VeiculoRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private VeiculoRepository veiculoRepository;

	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Serrvico de Mecanica");
		Categoria cat2 = new Categoria(null, "Servico de Funilaria");
		Categoria cat3 = new Categoria(null, "Servico de Pintura");
		Categoria cat4 = new Categoria(null, "Servico de eletrica");

		Produto p1 = new Produto(null, "Troca de cambio", 2000.00);
		Produto p2 = new Produto(null, "Troca de velas", 80.00);
		Produto p3 = new Produto(null, "Reparo na porta direita", 80.00);
		Produto p4 = new Produto(null, "Balanceamento", 50.00);
		Produto p5 = new Produto(null, "Alinhamento", 50.00);
		Produto p6 = new Produto(null, "Substituição do cano de descarga", 200.00);
		Produto p7 = new Produto(null, "Troca de chicote do motor", 1200.00);
		Produto p8 = new Produto(null, "Revisão 10.000 km", 800.00);
		Produto p9 = new Produto(null, "Reparo no cambio", 100.00);
		Produto p10 = new Produto(null, "Reparo no porta malas", 180.00);
		Produto p11 = new Produto(null, "Pintura da porta direita", 90.00);
		Produto p12 = new Produto(null, "Pintura d porta malas", 90.00);
		

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p4, p5, p6, p8, p9));
		cat2.getProdutos().addAll(Arrays.asList(p3,p10));
		cat3.getProdutos().addAll(Arrays.asList(p11,p12));
		cat4.getProdutos().addAll(Arrays.asList(p7));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		p4.getCategorias().addAll(Arrays.asList(cat1));
		p5.getCategorias().addAll(Arrays.asList(cat1));
		p6.getCategorias().addAll(Arrays.asList(cat1));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat1));
		p9.getCategorias().addAll(Arrays.asList(cat1));
		p10.getCategorias().addAll(Arrays.asList(cat2));
		p11.getCategorias().addAll(Arrays.asList(cat3));
		p12.getCategorias().addAll(Arrays.asList(cat3));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11,p12));

		Estado est1 = new Estado(null,null, "Minas Gerais");
		Estado est2 = new Estado(null,null, "São Paulo");
		Estado est3 = new Estado(null,null, "Distrito Federal");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cidade c4 = new Cidade(null, "Brasilia", est3);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
		
		Pessoa pes1 = new PessoaFisica(null, "Jorge Silva", "jorge.silva@gmail.com", "jorge.silva",pe.encode("123"), TipoPessoa.FISICA, "36378912377");
		pes1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Pessoa pes2 = new PessoaFisica(null, "Wendel Anchieta", "wendelanchieta@gmail.com", "wendelanchieta", pe.encode("123"),TipoPessoa.FISICA, "60876179065");
		pes2.addPerfil(Perfil.ADMIN);
		pes2.getTelefones().addAll(Arrays.asList("99993323", "98888393"));
		
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", pes1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", pes1, c2);
		
		Endereco e3 = new Endereco(null, "Avenida Floriano", "105", null, "Centro", "28777012", pes2, c2);

		Veiculo v1 = new Veiculo(null, "Peugeot", "208 Griffe", 2018, "PAD-1589", 10000L, pes2);
		Veiculo v2 = new Veiculo(null, "Ford", "Fusion", 2016, "PAZ-1799", 100000L, pes1);
		
		v1.addCombustiveis(Combustivel.FLEX);
		v2.addCombustiveis(Combustivel.FLEX);
		
		pes1.getEnderecos().addAll(Arrays.asList(e1, e2));
		pes1.getVeiculos().addAll(Arrays.asList(v2));
		
		pes2.getEnderecos().addAll(Arrays.asList(e3));
		pes2.getVeiculos().addAll(Arrays.asList(v1));

		pessoaRepository.saveAll(Arrays.asList(pes1, pes2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		veiculoRepository.saveAll(Arrays.asList(v1,v2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), pes1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), pes1, e2);
		Pedido ped3 = new Pedido(null, sdf.parse("10/05/2018 11:15"), pes2, e3);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		Pagamento pagto3 = new PagamentoComDinheiro(null, EstadoPagamento.QUITADO, ped3, sdf.parse("10/05/2018 15:09"));
		ped3.setPagamento(pagto3);
		
		pes1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2,ped3));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2,pagto3));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		ItemPedido ip4 = new ItemPedido(ped3, p6, 20.00, 1, 180.00);		
				
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		ped3.getItens().addAll(Arrays.asList(ip4));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		p6.getItens().addAll(Arrays.asList(ip4));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3,ip4));
	}
}

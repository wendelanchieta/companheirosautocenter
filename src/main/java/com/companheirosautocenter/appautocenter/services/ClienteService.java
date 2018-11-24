package com.companheirosautocenter.appautocenter.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.companheirosautocenter.appautocenter.domain.Cidade;
import com.companheirosautocenter.appautocenter.domain.Cliente;
import com.companheirosautocenter.appautocenter.domain.Endereco;
import com.companheirosautocenter.appautocenter.domain.enums.TipoCliente;
import com.companheirosautocenter.appautocenter.dto.ClienteDTO;
import com.companheirosautocenter.appautocenter.dto.ClienteNewDTO;
import com.companheirosautocenter.appautocenter.repositories.ClienteRepository;
import com.companheirosautocenter.appautocenter.repositories.EnderecoRepository;
import com.companheirosautocenter.appautocenter.services.exceptions.DataIntegrityException;
import com.companheirosautocenter.appautocenter.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	
	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ImageService imageService;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert (Cliente obj) {
		obj.setId(null);
		repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir por que há entidades relacionadas");
		}
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy); 
		return repo.findAll(pageRequest);
	} 
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cli = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()), pe.encode(dto.getSenha()));
		//Cidade cidade = cidadeRepository.findOne(dto.getCidadeId());
		Cidade cidade = new Cidade(dto.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cli, cidade);
		cli.getEnderecos().add(endereco);
		cli.getTelefones().add(dto.getTelefone1());
		if (dto.getTelefone2() != null) {
			cli.getTelefones().add(dto.getTelefone2());
		}
		
		if (dto.getTelefone3() != null) {
			cli.getTelefones().add(dto.getTelefone3());
		}
		
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	/**
	 * TODO TERMINAR O METODO <strong>uploadProfilePicture</strong>
	 * 
	 * @param multipartFile
	 * @return
	 */
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		return null;
	}

}

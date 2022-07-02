package br.com.starti.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.domain.entity.Endereco;
import br.com.starti.domain.repository.EnderecoRepository;
import br.com.starti.exception.ResourceNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	public Endereco inserir(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public List<Endereco> buscarTodos(){
		return enderecoRepository.findAll();
	}
	
	public Endereco buscarPorId(Integer id) {
		Endereco entity = enderecoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado"));
		return entity;
	}
	
	public void deletar(Integer id) {
		enderecoRepository.deleteById(id);
	}
	
	public Endereco atualizar(Endereco endereco) {
		Endereco entity = buscarPorId(endereco.getIdEndereco());
		entity.setRua(endereco.getRua());
		entity.setNumero(endereco.getNumero());
		entity.setBairro(endereco.getBairro());
		entity.setCidade(endereco.getCidade());
		entity.setCep(endereco.getCep());
		entity.setUf(endereco.getUf());
	
		return inserir(entity);
	}
	
}

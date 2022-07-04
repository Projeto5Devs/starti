package br.com.starti.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.domain.entity.PFisica;
import br.com.starti.domain.repository.PFisicaRepository;
import br.com.starti.exception.ResourceNotFoundException;

@Service
public class PFisicaService {
	
	@Autowired
	PFisicaRepository repository;
	
	public PFisica inserir(PFisica pfisica) {
		return repository.save(pfisica);
	}
	
	public List<PFisica> buscarTodos() {
		return repository.findAll();
	}
	
	public PFisica buscarPorId(Long id) {
		PFisica entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		return entity;
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	public PFisica atualizar(PFisica pfisica) {
		PFisica entity = buscarPorId(pfisica.getIdPFisica());
		entity.setContato(pfisica.getContato());
		entity.setCpf(pfisica.getCpf());
		entity.setDataDeNascimento(pfisica.getDataDeNascimento());
		entity.setEndereco(pfisica.getEndereco());
		entity.setNome(pfisica.getNome());
		entity.setSobrenome(pfisica.getSobrenome());
		entity.setLogin(pfisica.getLogin());
		
		return inserir(entity);
	}
}

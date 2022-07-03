package br.com.starti.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.domain.entity.Candidato;
import br.com.starti.domain.repository.CandidatoRepository;
import br.com.starti.exception.ResourceNotFoundException;

@Service
public class CandidatoService {
	
	@Autowired
	CandidatoRepository repository;
	
	public Candidato inserir(Candidato candidato) {
		return repository.save(candidato);
	}
	
	public List<Candidato> buscarTodos() {
		return repository.findAll();
	}
	
	public Candidato buscarPorId(Long id) {
		Candidato entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		return entity;
	}
	
	public void deletar(Candidato candidato) {
		repository.delete(candidato);
	}
	
	public Candidato atualizar(Candidato candidato) {
		Candidato entity = buscarPorId(candidato.getIdCandidato());
		entity.setContato(candidato.getContato());
		entity.setCpf(candidato.getCpf());
		entity.setDataDeNascimento(candidato.getDataDeNascimento());
		entity.setEndereco(candidato.getEndereco());
		entity.setNome(candidato.getNome());
		entity.setSobrenome(candidato.getSobrenome());
		entity.setLogin(candidato.getLogin());
		
		return inserir(entity);
	}
}

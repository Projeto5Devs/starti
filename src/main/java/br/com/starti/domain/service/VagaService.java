package br.com.starti.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.exception.ResourceNotFoundException;
import br.com.starti.domain.entity.Vaga;
import br.com.starti.domain.repository.VagaRepository;

@Service
public class VagaService {
	
	@Autowired
	VagaRepository repository;
	
	public Vaga inserir(Vaga vaga){
		return repository.save(vaga);
	}
	
	public List<Vaga> buscarTodos(){
		return repository.findAll();
	}

	public Vaga buscarPorId(Long id) {
		Vaga entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		return entity;
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	public Vaga atualizar(Vaga vaga) {
		Vaga entity = buscarPorId(vaga.getId());
		entity.setDescricao(vaga.getDescricao());
		entity.setSalario(vaga.getSalario());
		entity.setModalidade(vaga.getModalidade());
		entity.setPrazo(vaga.getPrazo());
		entity.setCargo(vaga.getCargo());
		entity.setEmpresa(vaga.getEmpresa());
		
		return inserir(entity);
	}
}
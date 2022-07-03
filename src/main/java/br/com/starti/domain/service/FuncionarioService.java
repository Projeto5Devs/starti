package br.com.starti.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.domain.entity.Funcionario;
import br.com.starti.domain.repository.FuncionarioRepository;
import br.com.starti.exception.ResourceNotFoundException;

@Service
public class FuncionarioService {
	
	@Autowired
	FuncionarioRepository repository;
	
	public Funcionario inserir(Funcionario funcionario) {
		return repository.save(funcionario);
	}
	
	public List<Funcionario> buscarTodos() {
		return repository.findAll();
	}
	
	public Funcionario buscarPorId(Long id) {
		Funcionario entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("N�o foi encontrado registro com esse Id"));
		return entity;
	}
	
	public void deletar(Funcionario funcionario) {
		repository.delete(funcionario);
	}
	
	public Funcionario atualizar(Funcionario funcionario) {
		Funcionario entity = buscarPorId(funcionario.getIdFuncionario());
//		entity.setFuncionario(funcionario.getFuncionario());
		entity.setCpf(funcionario.getCpf());
		entity.setDataDeNascimento(funcionario.getDataDeNascimento());
		entity.setEndereco(funcionario.getEndereco());
		entity.setNome(funcionario.getNome());
		entity.setSobrenome(funcionario.getSobrenome());
		entity.setLogin(funcionario.getLogin());
		
		return inserir(entity);
	}
}
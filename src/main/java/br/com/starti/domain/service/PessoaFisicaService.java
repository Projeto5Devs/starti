package br.com.starti.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.domain.entity.PessoaFisica;
import br.com.starti.domain.repository.PessoaFisicaRepository;
import br.com.starti.exception.ResourceNotFoundException;

@Service
public class PessoaFisicaService {
	
	@Autowired
	PessoaFisicaRepository repository;
	
	public PessoaFisica inserir(PessoaFisica pessoaFisica) {
		return repository.save(pessoaFisica);
	}
	
	public List<PessoaFisica> buscarTodos() {
		return repository.findAll();
	}
	
	public PessoaFisica buscarPorId(Long id) {
		PessoaFisica entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		return entity;
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	public PessoaFisica atualizar(PessoaFisica pessoaFisica) {
		PessoaFisica entity = buscarPorId(pessoaFisica.getIdPessoaFisica());
		entity.setContato(pessoaFisica.getContato());
		entity.setCpf(pessoaFisica.getCpf());
		entity.setDataDeNascimento(pessoaFisica.getDataDeNascimento());
		entity.setEndereco(pessoaFisica.getEndereco());
		entity.setNome(pessoaFisica.getNome());
		entity.setSobrenome(pessoaFisica.getSobrenome());
		entity.setLogin(pessoaFisica.getLogin());
		
		return inserir(entity);
	}
}

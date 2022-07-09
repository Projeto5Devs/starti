package br.com.starti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.adapter.DozerConverter;
import br.com.starti.domain.entity.PessoaFisica;
import br.com.starti.domain.vo.PessoaFisicaVO;
import br.com.starti.exception.ResourceNotFoundException;
import br.com.starti.repository.PessoaFisicaRepository;

@Service
public class PessoaFisicaService {
	
	@Autowired
	PessoaFisicaRepository repository;
	
	public PessoaFisicaVO inserir(PessoaFisicaVO pessoaFisica) {
		var entity = DozerConverter.parseObject(pessoaFisica, PessoaFisica.class);
		var vo = DozerConverter.parseObject(repository.save(entity), PessoaFisicaVO.class);
		return vo;
	}
	
	public List<PessoaFisicaVO> buscarTodos() {
		return DozerConverter.parseListObject(repository.findAll(), PessoaFisicaVO.class);
	}
	
	public PessoaFisicaVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		return DozerConverter.parseObject(entity, PessoaFisicaVO.class);
	}
	
	public void deletar(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		repository.delete(entity);
	}
	
	public PessoaFisicaVO atualizar(PessoaFisicaVO pessoaFisica) {
		var entity = repository.findById(pessoaFisica.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		
		entity.setContato(pessoaFisica.getContato());
		entity.setCpf(pessoaFisica.getCpf());
		entity.setDataDeNascimento(pessoaFisica.getDataDeNascimento());
		entity.setEndereco(pessoaFisica.getEndereco());
		entity.setNome(pessoaFisica.getNome());
		entity.setSobrenome(pessoaFisica.getSobrenome());
		entity.setLogin(pessoaFisica.getLogin());
		
		var vo = DozerConverter.parseObject(repository.save(entity), PessoaFisicaVO.class);
		return vo;
	}
}

package br.com.starti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.starti.adapter.DozerConverter;
import br.com.starti.domain.entity.PessoaFisica;
import br.com.starti.domain.vo.v1.PessoaFisicaVO;
import br.com.starti.exception.ResourceNotFoundException;
import br.com.starti.repository.PessoaFisicaRepository;

@Service
public class PessoaFisicaService  {
	
	@Autowired
	PessoaFisicaRepository repository;
	
	public PessoaFisicaVO inserir(PessoaFisicaVO pessoaFisica) {
		var entity = DozerConverter.parseObject(pessoaFisica, PessoaFisica.class);
		var vo = DozerConverter.parseObject(repository.save(entity), PessoaFisicaVO.class);
		return vo;
	}
	
	public Page<PessoaFisicaVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToPessoaFisicaVO);
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
		var entity = repository.findById(pessoaFisica.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		
		entity.setContato(pessoaFisica.getContato());
		entity.setCpf(pessoaFisica.getCpf());
		entity.setDataDeNascimento(pessoaFisica.getDataDeNascimento());
		entity.setEndereco(pessoaFisica.getEndereco());
		entity.setNome(pessoaFisica.getNome());
		entity.setSobrenome(pessoaFisica.getSobrenome());
		
		var vo = DozerConverter.parseObject(repository.save(entity), PessoaFisicaVO.class);
		return vo;
	}

	private PessoaFisicaVO convertToPessoaFisicaVO(PessoaFisica entity) {
		return DozerConverter.parseObject(entity, PessoaFisicaVO.class);
	}

}

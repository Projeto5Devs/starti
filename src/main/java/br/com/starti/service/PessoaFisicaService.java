package br.com.starti.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.starti.adapter.DozerConverter;
import br.com.starti.domain.entity.Permission;
import br.com.starti.domain.entity.PessoaFisica;
import br.com.starti.domain.vo.v1.PessoaFisicaVO;
import br.com.starti.exception.ResourceNotFoundException;
import br.com.starti.repository.PermissionRepository;
import br.com.starti.repository.PessoaFisicaRepository;

@Service
public class PessoaFisicaService  {
	
	@Autowired
	PessoaFisicaRepository repository;
	
	@Autowired
	PermissionRepository permissionRepository;
	
	public PessoaFisicaVO inserir(PessoaFisicaVO pessoaFisica) {
		Permission permission = permissionRepository.findByDescricao("ROLE_USER_PF");
		List<Permission> list = Arrays.asList(permission);   
		var entity = DozerConverter.parseObject(pessoaFisica, PessoaFisica.class);
		entity.getUserId().setPermissoes(list);
		var vo = DozerConverter.parseObject(repository.save(entity), PessoaFisicaVO.class);
		return vo;
	}
	
	public Page<PessoaFisicaVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToPessoaFisicaVO);
	}

	
	public PessoaFisica buscarPorIdUsuario(Long id) {
		return repository.findByUsuario(id);

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
		entity.setUserId(pessoaFisica.getUserId());
		entity.setNome(pessoaFisica.getNome());
		entity.setSobrenome(pessoaFisica.getSobrenome());
		
		var vo = DozerConverter.parseObject(repository.save(entity), PessoaFisicaVO.class);
		return vo;
	}

	private PessoaFisicaVO convertToPessoaFisicaVO(PessoaFisica entity) {
		return DozerConverter.parseObject(entity, PessoaFisicaVO.class);
	}

}

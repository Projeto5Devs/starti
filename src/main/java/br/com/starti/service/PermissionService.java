package br.com.starti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.domain.entity.Permission;
import br.com.starti.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	PermissionRepository repository;
	
	public Permission buscarPorDescricao(String descricao){
		return repository.findByDescricao(descricao);
	}
}

package br.com.starti.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.domain.entity.Empresa;
import br.com.starti.domain.repository.EmpresaRepository;
import br.com.starti.exception.ResourceNotFoundException;

@Service
public class EmpresaService {
	
	@Autowired
	EmpresaRepository empresaRepository;

	public Empresa inserir(Empresa empresa) {
		return empresaRepository.save(empresa);
	}
	
	public List<Empresa>buscarTodos(){
		return empresaRepository.findAll();
	}
	
	public Empresa buscarPorId(Integer id) {
		return empresaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("O ID a empresa não pode ser encontrado."));
	}
	
	public void deletar(Integer id) {
		empresaRepository.deleteById(id);
	}
	
	public Empresa atualizar(Empresa empresa) {
		Empresa entity = buscarPorId(empresa.getIdEmpresa());
		entity.setNomeFantasia(empresa.getNomeFantasia());
		entity.setRazaoSocial(empresa.getRazaoSocial());
		entity.setSegmento(empresa.getSegmento());
		entity.setSegmento(empresa.getCnpj());
		entity.setEndereco(empresa.getEndereco());
		entity.setContato(empresa.getContato());
		entity.setLogin(empresa.getLogin());
		return entity;
	}

}

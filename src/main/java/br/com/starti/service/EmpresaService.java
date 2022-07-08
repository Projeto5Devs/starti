package br.com.starti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.domain.entity.Empresa;
import br.com.starti.exception.ResourceNotFoundException;
import br.com.starti.repository.EmpresaRepository;

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
	
	public Empresa buscarPorId(Long id) {
		return empresaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("O ID a empresa não pode ser encontrado."));
	}
	
	public void deletar(Long id) {
		empresaRepository.deleteById(id);
	}
	
	public Empresa atualizar(Empresa empresa) {
		Empresa entity = buscarPorId(empresa.getIdEmpresa());
		entity.setNomeFantasia(empresa.getNomeFantasia());
		entity.setRazaoSocial(empresa.getRazaoSocial());
		entity.setCnpj(empresa.getCnpj());
		entity.setSegmento(empresa.getSegmento());
		entity.setEndereco(empresa.getEndereco());
		entity.setContato(empresa.getContato());
		entity.setLogin(empresa.getLogin());
		return inserir(entity);
	}

}

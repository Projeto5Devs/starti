package br.com.starti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.adapter.DozerConverter;
import br.com.starti.domain.entity.Empresa;
import br.com.starti.domain.entity.PessoaFisica;
import br.com.starti.domain.vo.v1.EmpresaVO;
import br.com.starti.exception.ResourceNotFoundException;
import br.com.starti.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	EmpresaRepository empresaRepository;

	public EmpresaVO inserir(EmpresaVO empresa) {
		var entity = DozerConverter.parseObject(empresa, Empresa.class);
		var vo = DozerConverter.parseObject(empresaRepository.save(entity), EmpresaVO.class);
		return vo;
	}
	
	public List<EmpresaVO>buscarTodos(){
		return DozerConverter.parseListObject(empresaRepository.findAll(), EmpresaVO.class);	
	}
	
	public EmpresaVO buscarPorId(Long id) {
		var entity =  empresaRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("O ID a empresa não pode ser encontrado."));
		return DozerConverter.parseObject(entity, EmpresaVO.class);
	}
	
	public void deletar(Long id) {
		empresaRepository.deleteById(id);
	}
	
	public Empresa buscarPorUsuario(Long id) {
		return empresaRepository.findByUsuario(id);

	}
	
	public EmpresaVO atualizar(EmpresaVO empresa) {
		var entity =  empresaRepository.findById(empresa.getKey())
				.orElseThrow(()-> new ResourceNotFoundException("O ID a empresa não pode ser encontrado."));
		
		entity.setNomeFantasia(empresa.getNomeFantasia());
		entity.setRazaoSocial(empresa.getRazaoSocial());
		entity.setCnpj(empresa.getCnpj());
		entity.setSegmento(empresa.getSegmento());
//		entity.setEndereco(empresa.getEndereco());
//		entity.setContato(empresa.getContato());
//		entity.setLogin(empresa.getLogin());
		
		var vo = DozerConverter.parseObject(empresaRepository.save(entity), EmpresaVO.class);
		return vo;
	}

}

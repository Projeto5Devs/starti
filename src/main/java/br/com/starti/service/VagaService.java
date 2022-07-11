package br.com.starti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.adapter.DozerConverter;
import br.com.starti.domain.entity.Vaga;
import br.com.starti.domain.vo.v1.VagaVO;
import br.com.starti.exception.ResourceNotFoundException;
import br.com.starti.repository.VagaRepository;

@Service
public class VagaService {
	
	@Autowired
	VagaRepository repository;
	
	public VagaVO inserir(VagaVO vaga){
		var entity = DozerConverter.parseObject(vaga, Vaga.class);
		var vo = DozerConverter.parseObject(repository.save(entity), VagaVO.class);
		return vo;
	}
	
	public List<VagaVO> buscarTodos(){
		return DozerConverter.parseListObject(repository.findAll(), VagaVO.class);
	}

	public VagaVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		return DozerConverter.parseObject(entity, VagaVO.class);
	}
	
	public void deletar(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		repository.delete(entity);
	}
	
	public VagaVO atualizar(VagaVO vaga) {
		var entity = repository.findById(vaga.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		
		entity.setDescricao(vaga.getDescricao());
		entity.setSalario(vaga.getSalario());
		entity.setModalidade(vaga.getModalidade());
		entity.setPrazo(vaga.getPrazo());
		entity.setCargo(vaga.getCargo());
		entity.setEmpresa(vaga.getEmpresa());
		
		var vo = DozerConverter.parseObject(repository.save(entity), VagaVO.class);
		return vo;
	}
}
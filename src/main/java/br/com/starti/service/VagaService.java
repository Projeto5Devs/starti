package br.com.starti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.starti.adapter.DozerConverter;
import br.com.starti.domain.entity.Empresa;
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
	
	public Page<VagaVO> buscarTodos(Pageable pageable){
		var page = repository.findAll(pageable);
		return page.map(this::convertToVagaVO);
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
		Empresa empresa = DozerConverter.parseObject(vaga.getEmpresa(), Empresa.class);
		entity.setEmpresa(empresa); 
		
		var vo = DozerConverter.parseObject(repository.save(entity), VagaVO.class);
		return vo;
	}
	
	public Page<VagaVO> findByCargo(String cargo, Pageable pageable) {
		var page = repository.findByCargoContainingIgnoreCase(cargo, pageable);
		return page.map(this::convertToVagaVO);	
	}
	
	public Page<VagaVO> findByModalidade(String modalidade, Pageable pageable) {
		var page = repository.buscarPorModalidade(modalidade, pageable);
		return page.map(this::convertToVagaVO);	
	}
	
	
	public List<Vaga> buscarPorTipoAndModalidade(String tipo, String modalidade) {
		return repository.buscarPorTipoAndModalidade(tipo, modalidade);
	}
	
	public Page<VagaVO> findByTipo(String tipo, Pageable pageable) {
		var page = repository.buscarPorTipo(tipo, pageable);
		return page.map(this::convertToVagaVO);	
	}

	private VagaVO convertToVagaVO(Vaga entity) {
		return DozerConverter.parseObject(entity, VagaVO.class);
	}
	
}
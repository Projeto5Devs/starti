package br.com.starti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.adapter.DozerConverter;
import br.com.starti.domain.entity.Empresa;
import br.com.starti.domain.entity.Endereco;
import br.com.starti.domain.vo.v1.EmpresaVO;
import br.com.starti.domain.vo.v1.EnderecoVO;
import br.com.starti.domain.vo.v1.VagaVO;
import br.com.starti.exception.ResourceNotFoundException;
import br.com.starti.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	public EnderecoVO inserir(EnderecoVO endereco) {
		var entity = DozerConverter.parseObject(endereco, Endereco.class);
		var vo = DozerConverter.parseObject(enderecoRepository.save(entity), EnderecoVO.class);
		return vo;
	}

	public List<EnderecoVO> buscarTodos() {
		return DozerConverter.parseListObject(enderecoRepository.findAll(), EnderecoVO.class);
	}

	public EnderecoVO buscarPorId(Long id) {
		Endereco entity = enderecoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado"));
		return DozerConverter.parseObject(entity, EnderecoVO.class);
	}

	public void deletar(Long id) {
		enderecoRepository.deleteById(id);
	}

	public EnderecoVO atualizar(EnderecoVO endereco) {
		var entity = enderecoRepository.findById(endereco.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado endereço com esse Id"));

		entity.setRua(endereco.getRua());
		entity.setNumero(endereco.getNumero());
		entity.setBairro(endereco.getBairro());
		entity.setCidade(endereco.getCidade());
		entity.setCep(endereco.getCep());
		entity.setUf(endereco.getUf());

		var vo = DozerConverter.parseObject(enderecoRepository.save(entity), EnderecoVO.class);
		return (EnderecoVO) vo;
	}

}
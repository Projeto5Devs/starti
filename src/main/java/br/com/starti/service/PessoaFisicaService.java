package br.com.starti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.starti.adapter.DozerConverter;
import br.com.starti.domain.entity.PessoaFisica;
import br.com.starti.domain.vo.v1.PessoaFisicaVO;
import br.com.starti.exception.ResourceNotFoundException;
import br.com.starti.repository.PessoaFisicaRepository;

@Service
public class PessoaFisicaService implements UserDetailsService {
	
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

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		var user = repository.findByEmail(email);
		if(user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("E-mail " + email + " não localizado");
		}
	
	}
}

package br.com.starti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.starti.adapter.DozerConverter;
import br.com.starti.domain.entity.Usuario;
import br.com.starti.domain.vo.v1.UsuarioVO;
import br.com.starti.exception.ResourceNotFoundException;
import br.com.starti.repository.UsuarioRepository;

@Service
@Transactional
//Classe para Autenticação do Usuário
public class UsuarioService implements UserDetailsService {


	@Autowired 
	UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUsername(username);
		if (user != null) {
			return new User(user.getUsername(), user.getPassword(), true, true, true,true,user.getAuthorities());
		} else {
			throw new UsernameNotFoundException("Usuário " + username + " não localizado");
		}

	}

	public UsuarioVO inserir(UsuarioVO usuario) {
		var entity = DozerConverter.parseObject(usuario, Usuario.class);
		var vo = DozerConverter.parseObject(repository.save(entity), UsuarioVO.class);
		return vo;
	}
	
	public List<UsuarioVO> buscarTodos() {
		return DozerConverter.parseListObject(repository.findAll(), UsuarioVO.class);
	}

	
	public UsuarioVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		return DozerConverter.parseObject(entity, UsuarioVO.class);
	}
	
	public void deletar(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		repository.delete(entity);
	}
	
	public UsuarioVO atualizar(UsuarioVO usuario) {
		var entity = repository.findById(usuario.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		
		entity.setPassword(usuario.getPassword());
		entity.setUsername(usuario.getUsername());
		
		var vo = DozerConverter.parseObject(repository.save(entity), UsuarioVO.class);
		return vo;
	}


	

	
}

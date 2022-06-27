package br.com.starti.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.domain.entity.Usuario;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repository;

	public void inserir(Usuario usuario){
		repository.save(usuario);
	}
	

}

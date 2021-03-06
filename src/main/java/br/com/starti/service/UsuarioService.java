package br.com.starti.service;





import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

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

	

	
}

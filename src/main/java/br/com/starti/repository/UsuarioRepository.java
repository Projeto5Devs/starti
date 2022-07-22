package br.com.starti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.starti.domain.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

//	@Query("SELECT u FROM Usuario u WHERE u.username =: username")
	Usuario findByUsername(String username);
	
}


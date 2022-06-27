package br.com.starti.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.starti.domain.entity.Usuario;

/**
 * Incluir, excluir, alterar e consultar
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

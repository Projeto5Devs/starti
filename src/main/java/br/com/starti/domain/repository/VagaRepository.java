package br.com.starti.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.starti.domain.entity.Vaga;


/**
 * Incluir, excluir, alterar e consultar
 */
@Repository
public interface VagaRepository  extends JpaRepository<Vaga, Long> {

}

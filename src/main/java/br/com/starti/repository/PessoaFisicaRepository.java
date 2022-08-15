package br.com.starti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.starti.domain.entity.PessoaFisica;

@Repository 
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long>{ 
 
	
	@Query(value="SELECT * FROM pessoa_fisica WHERE id_usuario =:id", nativeQuery=true)
	PessoaFisica findByUsuario(@Param("id")Long id);
}
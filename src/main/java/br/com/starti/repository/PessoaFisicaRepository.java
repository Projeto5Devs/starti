package br.com.starti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.starti.domain.entity.PessoaFisica;

@Repository 
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long>{ 
 
	@Query("SELECT p FROM PessoaFisica p WHERE p.contato.email=:email")
	PessoaFisica findByEmail(@Param("email")String email);
}
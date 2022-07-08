package br.com.starti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.starti.domain.entity.PessoaFisica;

@Repository 
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long>{ 
 
}
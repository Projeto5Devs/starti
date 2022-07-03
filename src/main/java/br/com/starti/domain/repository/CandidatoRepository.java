package br.com.starti.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.starti.domain.entity.Candidato;

@Repository 
public interface CandidatoRepository extends JpaRepository<Candidato, Integer>{ 
 
}
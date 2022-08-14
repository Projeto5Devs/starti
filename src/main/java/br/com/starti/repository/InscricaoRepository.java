package br.com.starti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.starti.domain.entity.ChaveCompostaInscricao;
import br.com.starti.domain.entity.Inscricao;


@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, ChaveCompostaInscricao> {

	

}

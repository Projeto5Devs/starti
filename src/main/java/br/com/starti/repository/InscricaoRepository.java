package br.com.starti.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.starti.domain.entity.ChaveCompostaInscricao;
import br.com.starti.domain.entity.Inscricao;


@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, ChaveCompostaInscricao> {

	@Query(value="SELECT * FROM inscricao WHERE id_pessoafisica =:id", nativeQuery=true)
	List<Inscricao>findByCandidato(@Param("id")Long id);
	
	@Modifying
	@Transactional 
	@Query(value="DELETE FROM inscricao WHERE id_vaga =:id", nativeQuery=true)
	void deleteByIdVaga(@Param("id")Long id);
	

}

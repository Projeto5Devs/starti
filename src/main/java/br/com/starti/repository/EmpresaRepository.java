package br.com.starti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.starti.domain.entity.Empresa;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	@Query(value="SELECT * FROM empresa WHERE id_usuario =:id", nativeQuery=true)
	Empresa findByUsuario(@Param("id")Long id);
	
}

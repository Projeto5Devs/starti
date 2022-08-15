package br.com.starti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import br.com.starti.domain.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

	@Query(value="SELECT * FROM db_starti.permissao WHERE descricao =:descricao", nativeQuery=true)
	Permission findByDescricao(@Param("descricao")String descricao);
}

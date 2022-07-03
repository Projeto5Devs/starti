package br.com.starti.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.starti.domain.entity.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

}

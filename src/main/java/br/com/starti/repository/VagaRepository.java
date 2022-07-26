package br.com.starti.repository; 
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; 
 
import br.com.starti.domain.entity.Vaga; 
 
@Repository 
public interface VagaRepository  extends JpaRepository<Vaga, Long>{ 
	
@Query("SELECT c FROM Vaga c WHERE c.cargo=:cargo")
 Page<Vaga> buscarPorCargo(@Param("cargo") String cargo, Pageable pageable);
 
 //Page<Vaga> buscarPorCidade(String cidade, Pageable pageable);
 //Page<Vaga> buscarPorTipo(String tipo, Pageable pageable);
}
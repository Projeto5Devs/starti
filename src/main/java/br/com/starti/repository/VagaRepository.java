package br.com.starti.repository; 
 
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository; 
 
import br.com.starti.domain.entity.Vaga; 
 
@Repository 
public interface VagaRepository  extends JpaRepository<Vaga, Long>{ 
	
// @Query("SELECT v FROM Vaga v WHERE v.cargo LIKE %:cargo%")
 Page<Vaga> findByCargoContainingIgnoreCase( String cargo, Pageable pageable);
 
 @Query("SELECT m FROM Vaga m WHERE m.modalidade=:modalidade")
 Page<Vaga> buscarPorModalidade(String modalidade, Pageable pageable);
 
 @Query("SELECT t FROM Vaga t WHERE t.tipo=:tipo")
 Page<Vaga> buscarPorTipo(String tipo, Pageable pageable);
 
 
 @Query("SELECT v FROM Vaga v WHERE  v.tipo like :tipo and v.modalidade like :modalidade")
 List<Vaga> buscarPorTipoAndModalidade(String tipo, String modalidade);
 


}
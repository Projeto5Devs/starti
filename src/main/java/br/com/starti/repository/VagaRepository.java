package br.com.starti.repository; 
 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 
 
import br.com.starti.domain.entity.Vaga; 
 
@Repository 
public interface VagaRepository  extends JpaRepository<Vaga, Long>{ 
 
}
package br.com.furafila.domain.restaurante;





import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestauranteRepository extends JpaRepository<Restaurante,Long> {


    Page<Restaurante> findAllByAtivoTrue(Pageable paginacao);
}

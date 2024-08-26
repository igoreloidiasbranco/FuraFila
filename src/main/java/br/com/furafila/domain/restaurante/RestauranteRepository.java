package br.com.furafila.domain.restaurante;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;

public interface RestauranteRepository extends JpaRepository<Restaurante,Long> {


    Page<Restaurante> findAllByAtivoTrue(Pageable paginacao);

   @Query("""
           select r from Restaurante r
           where
           r.ativo = true
           and
           r.especialidade = :especialidade
           and
           r.id not in(
                select rsv.restaurante.id from Reserva rsv
                where
                rsv.data = :data
           and
                rsv.motivoCancelamento is null
           )
           order by rand()
           limit 1
           """)
    Restaurante escolherRestauranteAleatorioNaData(Especialidade especialidade, LocalDateTime data);


   @Query("""
           select r.ativo
           from Restaurante r
           where
           r.id = :id
           """)
   Boolean findAtivoById(Long id);
}

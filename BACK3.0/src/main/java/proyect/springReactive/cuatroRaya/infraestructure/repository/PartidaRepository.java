package proyect.springReactive.cuatroRaya.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyect.springReactive.cuatroRaya.domain.Partida;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Integer> {


}

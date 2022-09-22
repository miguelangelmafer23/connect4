package proyect.springReactive.cuatroRaya.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyect.springReactive.cuatroRaya.domain.Player;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Player, Integer> {

    public List<Player> findByName(String name);
}

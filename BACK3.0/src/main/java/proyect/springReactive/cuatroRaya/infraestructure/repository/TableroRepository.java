package proyect.springReactive.cuatroRaya.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyect.springReactive.cuatroRaya.domain.Movement;

public interface TableroRepository extends JpaRepository<Movement, Integer> {
}

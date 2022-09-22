package proyect.springReactiveFront.cuatroRayaFront.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jugador {

    Integer id;
    String name;

    Integer  idpartida;

    String  IpPlayer;

    public Jugador(String name) {
        this.name = name;
    }

    public Jugador(int id) {
        this.id = id;
    }
}

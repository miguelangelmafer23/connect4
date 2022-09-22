package proyect.springReactive.cuatroRaya.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int id;
    String name;

    String ipJugador;

    public Player(String name, String ipJugador) {
        this.name = name;
        this.ipJugador = ipJugador;
    }
}


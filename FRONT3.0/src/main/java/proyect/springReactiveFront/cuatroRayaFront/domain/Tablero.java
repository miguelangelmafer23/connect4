package proyect.springReactiveFront.cuatroRayaFront.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tablero {

    int idTablero;
    Jugador jugador;
    private Partida partida;
    String columna;
    String fila;

    public Tablero(Jugador jugador, Partida partida, String columna, String fila) {
        this.jugador = jugador;
        this.partida = partida;
        this.columna = columna;
        this.fila = fila;
    }
}




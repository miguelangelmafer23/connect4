package proyect.springReactiveFront.cuatroRayaFront.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partida {

    int idPartida;


    Jugador firstPlayer;


    Jugador secondPlayer;

    boolean jugada;

    LocalDate date;

    String jugadorGanador;

    public Partida(Jugador firstPlayer, Jugador secondPlayer, boolean jugada, LocalDate date, String jugadorGanador) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.jugada = jugada;
        this.date = date;
        this.jugadorGanador = jugadorGanador;
    }

    public Partida(Jugador firstPlayer, boolean jugada, LocalDate date) {
        this.firstPlayer = firstPlayer;
        this.jugada = jugada;
        this.date = date;
    }
}

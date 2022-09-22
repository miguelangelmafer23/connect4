package proyect.springReactive.cuatroRaya.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Partida {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int idPartida;

    @ManyToOne
    @JoinColumn(nullable = false)
    Player firstPlayer;

    @ManyToOne
    @JoinColumn(nullable = true)
    Player secondPlayer;
    boolean jugada;

    LocalDate date;

    int winStatus;

    public Partida(Player firstPlayer, Player secondPlayer, boolean jugada, LocalDate date, int winStatus) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.jugada = jugada;
        this.date = date;
        this.winStatus = winStatus;
    }

    public Partida(Player firstPlayer, boolean jugada, LocalDate date) {
        this.firstPlayer = firstPlayer;
        this.jugada = jugada;
        this.date = date;
    }
}

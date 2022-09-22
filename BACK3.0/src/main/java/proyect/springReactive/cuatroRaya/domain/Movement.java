package proyect.springReactive.cuatroRaya.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movement {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int idTablero;

    @ManyToOne
    @JoinColumn(nullable = true)
    Player player;

    int turno;
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Partida partida;
        PlayerEnum board[][] = new PlayerEnum[6][7];

    public Movement(Player player, int turno, Partida partida, PlayerEnum board[][]) {
        this.player = player;
        this.partida = partida;
        this.board = board;
    }

    public void setBoard (int x, int y, PlayerEnum player){
        board [x][y]= player;
    }
}

package proyect.springReactiveFront.cuatroRayaFront.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Integer idPlayer;
    private Integer idTablero;
    private Integer idPartida;
    private Integer column;
}

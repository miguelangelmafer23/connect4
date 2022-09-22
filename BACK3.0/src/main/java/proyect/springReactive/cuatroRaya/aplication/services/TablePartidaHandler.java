package proyect.springReactive.cuatroRaya.aplication.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.domain.PlayerEnum;
import proyect.springReactive.cuatroRaya.exception.NotFoundException;
import proyect.springReactive.cuatroRaya.infraestructure.repository.PartidaRepository;
import proyect.springReactive.cuatroRaya.infraestructure.repository.TableroRepository;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class TablePartidaHandler {


    @Autowired
    TableroRepository tableroRepository;

    @Autowired
    PartidaRepository partidaRepository;


    public Mono<ServerResponse> devolverEstructuraTablero (ServerRequest serverRequest) {
        PlayerEnum board[][] = tableroRepository.findById(Integer.valueOf(serverRequest.pathVariable("idTablero")))
                .orElseThrow(() -> new NotFoundException("Table not found")).getBoard();
        System.out.println("                                                                         ");
        for (int i = 5; i >= 0; i--) {
            System.out.println(" ");
            for (int j = 0; j < 7; j++) {
                //System.out.print(board[i][j]);
            }
        }
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(board));
    }
}

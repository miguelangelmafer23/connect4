package proyect.springReactive.cuatroRaya.aplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.domain.Movement;
import proyect.springReactive.cuatroRaya.domain.Player;
import proyect.springReactive.cuatroRaya.exception.NotFoundException;
import proyect.springReactive.cuatroRaya.infraestructure.repository.JugadorRepository;
import proyect.springReactive.cuatroRaya.infraestructure.repository.TableroRepository;
import reactor.core.publisher.Mono;

@Component
public class GameHandler {

    @Autowired
    TableroRepository tableroRepository;

    public Mono<ServerResponse> getIdGame(ServerRequest serverRequest) {
        Movement m = tableroRepository
                .findById(Integer.valueOf(serverRequest.pathVariable("idTablero")))
                .orElseThrow(()->new NotFoundException("Tablero Not Found"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                body(BodyInserters.fromValue(m.getPartida().getIdPartida()));
    }

    public Mono<ServerResponse> getIdBoard(ServerRequest serverRequest) {
        Movement m = tableroRepository
                .findById(Integer.valueOf(serverRequest.pathVariable("idGame")))
                .orElseThrow(()->new NotFoundException("Game Not Found"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                body(BodyInserters.fromValue(m.getIdTablero()));
    }
}

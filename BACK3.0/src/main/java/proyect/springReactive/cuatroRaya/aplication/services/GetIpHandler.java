package proyect.springReactive.cuatroRaya.aplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.domain.Partida;
import proyect.springReactive.cuatroRaya.domain.Player;
import proyect.springReactive.cuatroRaya.exception.NotFoundException;
import proyect.springReactive.cuatroRaya.infraestructure.repository.JugadorRepository;
import reactor.core.publisher.Mono;

@Component
public class GetIpHandler {

    @Autowired
    JugadorRepository jugadorRepository;

    public Mono<ServerResponse> getIPPlayer(ServerRequest serverRequest) {
        Player p = jugadorRepository
                .findById(Integer.valueOf(serverRequest.pathVariable("idPlayer")))
                .orElseThrow(()->new NotFoundException("Player Not Found"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                body(BodyInserters.fromValue(p.getIpJugador()));
    }
}

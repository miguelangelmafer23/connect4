package proyect.springReactive.cuatroRaya.aplication.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.domain.Player;
import proyect.springReactive.cuatroRaya.infraestructure.repository.JugadorRepository;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JugadorHandler {

    @Autowired
    JugadorRepository jugadorRepository;

    @Autowired
    PartidaHandler partidaHandler;

    public Mono<ServerResponse> createJugador(ServerRequest serverRequest){
        Player player = new Player(serverRequest.pathVariable("username"), serverRequest.pathVariable("ipPlayer"));
        jugadorRepository.save(player);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(partidaHandler.createPartida(player)));
    }

}

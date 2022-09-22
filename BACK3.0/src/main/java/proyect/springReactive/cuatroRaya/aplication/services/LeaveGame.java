package proyect.springReactive.cuatroRaya.aplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.domain.Movement;
import proyect.springReactive.cuatroRaya.exception.NotFoundException;
import proyect.springReactive.cuatroRaya.infraestructure.repository.PartidaRepository;
import proyect.springReactive.cuatroRaya.infraestructure.repository.TableroRepository;
import reactor.core.publisher.Mono;
@Component
public class LeaveGame {
    @Autowired
    TableroRepository tableroRepository;
    @Autowired
    PartidaRepository partidaRepository;

    public Mono<ServerResponse> statusGame(ServerRequest serverRequest){
        Movement movement = tableroRepository.findById(Integer.valueOf(serverRequest.pathVariable("idPartida")))
                .orElseThrow(() -> new NotFoundException("Tablero no encontrado"));
        movement.getPartida().setJugada(true);
        partidaRepository.save(movement.getPartida());
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                body(BodyInserters.fromValue(movement));
    }
}

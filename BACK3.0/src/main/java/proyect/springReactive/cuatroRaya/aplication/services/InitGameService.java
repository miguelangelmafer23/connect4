package proyect.springReactive.cuatroRaya.aplication.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.domain.Partida;
import proyect.springReactive.cuatroRaya.infraestructure.repository.PartidaRepository;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class InitGameService {

    @Autowired
    PartidaRepository partidaRepository;

    public Mono<ServerResponse> startGame(ServerRequest serverRequest) {
        List<Partida> partidaList = partidaRepository.findAll();
        for(Partida p: partidaList){
            if(p.getSecondPlayer() == null){
                log.warn("Hay partida creada");
                return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(p.getIdPartida()));
            }
        }
        log.warn("NO hay partida creada");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(0));
    }
}

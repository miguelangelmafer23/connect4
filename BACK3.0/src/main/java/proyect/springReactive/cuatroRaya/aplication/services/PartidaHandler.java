package proyect.springReactive.cuatroRaya.aplication.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.domain.Player;
import proyect.springReactive.cuatroRaya.domain.Partida;
import proyect.springReactive.cuatroRaya.domain.Movement;
import proyect.springReactive.cuatroRaya.exception.NameRequiredException;
import proyect.springReactive.cuatroRaya.exception.NotFoundException;
import proyect.springReactive.cuatroRaya.infraestructure.repository.JugadorRepository;
import proyect.springReactive.cuatroRaya.infraestructure.repository.PartidaRepository;
import proyect.springReactive.cuatroRaya.infraestructure.repository.TableroRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
@Slf4j
public class PartidaHandler {

    @Autowired
    JugadorRepository jugadorRepository;

    @Autowired
    PartidaRepository partidaRepository;

    @Autowired
    TableroRepository tableroRepository;
    @Autowired
    MatchService matchService;

    public Integer createPartida(Player player1){
       Partida partida = partidaRepository.save(new Partida(player1, false, LocalDate.now()));
       return partida.getIdPartida();
    }

    private Partida findPartida(String id) throws Exception {
       return partidaRepository.findById(Integer.valueOf(id)).orElseThrow(()-> new NotFoundException("Game Not Found"));
    }

    public Mono<ServerResponse> updatePartida(ServerRequest serverRequest){
        Player player2 = new Player(serverRequest.pathVariable("username"), serverRequest.pathVariable("ipplayer"));
        Movement movement;
        try {
            Partida partidaExistente = findPartida(serverRequest.pathVariable("id"));
            jugadorRepository.save(player2);
            partidaExistente.setSecondPlayer(player2);
            partidaRepository.save(partidaExistente);
            movement = new Movement();
            movement.setTurno(1);
            movement.setPartida(partidaExistente);
            movement.setPlayer(partidaExistente.getFirstPlayer());
            matchService.resetBoard(movement.getBoard());
            tableroRepository.save(movement);
        }  catch (Exception ex) {
            throw new NameRequiredException(HttpStatus.NOT_FOUND, "Game Not Found", ex);
        }
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                body(BodyInserters.fromValue(movement.getIdTablero() + ";" + movement.getPartida().getSecondPlayer().getId()));
    }

    public Mono<ServerResponse> returnP1(ServerRequest serverRequest) {
        Partida p = partidaRepository
                .findById(Integer.valueOf(serverRequest.pathVariable("idPartida")))
                .orElseThrow(()->new NotFoundException("Game Not Found"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                body(BodyInserters.fromValue(p.getFirstPlayer().getId()));
    }

    public Mono<ServerResponse> returnP2(ServerRequest serverRequest) {
        Partida p = partidaRepository
                .findById(Integer.valueOf(serverRequest.pathVariable("idPartida")))
                .orElseThrow(()->new NotFoundException("Game Not Found"));
        if(p.getSecondPlayer()==null){
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                    body(BodyInserters.fromValue(007));
        }
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                body(BodyInserters.fromValue(p.getSecondPlayer().getId()));
    }
}

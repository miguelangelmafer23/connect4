package proyect.springReactive.cuatroRaya.aplication.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.domain.Movement;
import proyect.springReactive.cuatroRaya.domain.PlayerEnum;
import proyect.springReactive.cuatroRaya.exception.NotFoundException;
import proyect.springReactive.cuatroRaya.infraestructure.repository.TableroRepository;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ColumnHandler {

    @Autowired
    TableroRepository tableroRepository;

  public Mono<ServerResponse> columnLimit(ServerRequest serverRequest) {
    Movement movement =
        tableroRepository
            .findById(Integer.valueOf(serverRequest.pathVariable("idTablero")))
            .orElseThrow(() -> new NotFoundException("Not found table"));
//    if (tablero.getBoard()[1][Integer.valueOf(serverRequest.pathVariable("column"))] != 0) {
      if (movement.getBoard()[1][0] != PlayerEnum.PLAYER_EMPTY) {
          log.warn("lleno 0");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(0));
      }else if(movement.getBoard()[1][1] != PlayerEnum.PLAYER_EMPTY){
          log.warn("lleno 1");
          return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(1));
      }else if(movement.getBoard()[1][2] != PlayerEnum.PLAYER_EMPTY){
          log.warn("lleno 2");
          return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(2));
      }else if(movement.getBoard()[1][3] != PlayerEnum.PLAYER_EMPTY){
          log.warn("lleno 3");
          return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(3));
      }else if(movement.getBoard()[1][4] != PlayerEnum.PLAYER_EMPTY){
          log.warn("lleno 4");
          return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(4));
      }else if(movement.getBoard()[1][5] != PlayerEnum.PLAYER_EMPTY){
          log.warn("lleno 5");
          return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(5));
      }else if(movement.getBoard()[1][6] != PlayerEnum.PLAYER_EMPTY){
          log.warn("lleno 6");
          return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(6));
      }
      return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(200));
  }
}

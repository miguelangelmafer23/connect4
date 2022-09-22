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

import java.util.Arrays;

@Component
@Slf4j
public class CheckFullColumn {

    @Autowired
    TableroRepository tableroRepository;

    public Mono<ServerResponse> checkBlockColumn (ServerRequest serverRequest)

    {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Movement table = tableroRepository.findById(Integer.valueOf(serverRequest.pathVariable("idTablero")))
                .orElseThrow(()->new NotFoundException("Table Not Found"));
        Integer columna = Integer.valueOf(serverRequest.pathVariable("column"));

        int[] lastColumn = new int[7];
        for(int i = 0; i < 7; i++){
            if(!table.getBoard()[0][i].equals(PlayerEnum.PLAYER_EMPTY)){
                lastColumn[i] = 1;
            }
        }
        log.warn(Arrays.toString(lastColumn));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(lastColumn));
    }
}

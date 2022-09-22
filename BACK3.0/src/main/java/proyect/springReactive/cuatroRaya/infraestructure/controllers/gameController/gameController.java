package proyect.springReactive.cuatroRaya.infraestructure.controllers.gameController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.aplication.services.GameHandler;
import proyect.springReactive.cuatroRaya.aplication.services.TablePartidaHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class gameController {

    @Bean
    public RouterFunction<ServerResponse> returnGameId (GameHandler gameHandler) throws Exception{
        return RouterFunctions
                .route (GET  ("gameId/{idTablero}")
                        .and (accept (MediaType.APPLICATION_JSON)), gameHandler::getIdGame);
    }

    @Bean
    public RouterFunction<ServerResponse> returnBoardId (GameHandler gameHandler) throws Exception{
        return RouterFunctions
                .route (GET  ("boardId/{idGame}")
                        .and (accept (MediaType.APPLICATION_JSON)), gameHandler::getIdBoard);
    }
}

package proyect.springReactive.cuatroRaya.infraestructure.controllers.gameStatusController;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.aplication.services.PonerFichaHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class ReturnGameStatusController {


    /**
     * A este endPoint le pasas el idTablero y te devuelve:
     * 1 --> si ha ganado el Player 1
     * 2 --> si ha ganado el Player 2
     * 0 -->
     * -1 --> si la Partida ha sido abortada por cualquiera de los Player
     * @param ponerFichaHandler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> routeWinStatusCode (PonerFichaHandler ponerFichaHandler){
        return RouterFunctions
                .route (GET ("getStatusCodeGame/{idTablero}/{column}")
                        .and (accept (MediaType.APPLICATION_JSON)), ponerFichaHandler::getWinStatusCode);
    }

    @Bean
    public RouterFunction<ServerResponse> routeTurnCode (PonerFichaHandler ponerFichaHandler){
        return RouterFunctions
                .route (GET ("getTurnCode/{idTablero}")
                        .and (accept (MediaType.APPLICATION_JSON)), ponerFichaHandler::getTurnCode);
    }


    @Bean
    public RouterFunction<ServerResponse> estadoPatida (PonerFichaHandler ponerFichaHandler){
        return RouterFunctions
                .route (GET ("getStatusCodeGame/{idPartida}")
                        .and (accept (MediaType.APPLICATION_JSON)), ponerFichaHandler::estadoPatida);
    }


}

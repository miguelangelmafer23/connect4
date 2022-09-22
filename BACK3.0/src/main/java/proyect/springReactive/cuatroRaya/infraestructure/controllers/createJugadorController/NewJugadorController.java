package proyect.springReactive.cuatroRaya.infraestructure.controllers.createJugadorController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.MediaType;

import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import proyect.springReactive.cuatroRaya.aplication.services.JugadorHandler;
import proyect.springReactive.cuatroRaya.aplication.services.PartidaHandler;


import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
@Slf4j
public class NewJugadorController {


    @Bean
    public RouterFunction<ServerResponse> routePlayer(JugadorHandler jugadorHandler) {

        return RouterFunctions
                .route(GET("/username/{username}/ip/{ipPlayer}")
                        .and(accept(MediaType.ALL)), jugadorHandler::createJugador);
    }
   @Bean
    public RouterFunction<ServerResponse> routeUpdatePartida (PartidaHandler partidaHandler)throws Exception{
        return RouterFunctions
                .route (GET ("username/{username}/{id}/{ipplayer}")
                        .and (accept (MediaType.APPLICATION_JSON)), partidaHandler::updatePartida);
    }
    @Bean
    public RouterFunction<ServerResponse> returnPlayerid1 (PartidaHandler partidaHandler)throws Exception{
        return RouterFunctions
                .route (GET ("returnp1/{idPartida}")
                        .and (accept (MediaType.APPLICATION_JSON)), partidaHandler::returnP1);
    }
    @Bean
    public RouterFunction<ServerResponse> returnPlayerid2 (PartidaHandler partidaHandler)throws Exception{
        return RouterFunctions
                .route (GET ("returnp2/{idPartida}")
                        .and (accept (MediaType.APPLICATION_JSON)), partidaHandler::returnP2);

    }
}

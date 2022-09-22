package proyect.springReactive.cuatroRaya.infraestructure.controllers.gameController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.aplication.services.InitGameService;
import proyect.springReactive.cuatroRaya.aplication.services.PonerFichaHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class initGameController {

    @Bean
    public RouterFunction<ServerResponse> startGame (InitGameService initGameService){
        return RouterFunctions
                .route (GET ("getFirstPlayerGame")
                        .and (accept (MediaType.APPLICATION_JSON)), initGameService::startGame);
    }
}

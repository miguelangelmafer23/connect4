package proyect.springReactive.cuatroRaya.infraestructure.controllers.finishGameController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.aplication.services.LeaveGame;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class FinishGame {

    @Bean
    public RouterFunction<ServerResponse> leavePlayer (LeaveGame leaveGame){
        return RouterFunctions
                .route (GET ("leaveGame/{idPartida}")
                        .and (accept (MediaType.APPLICATION_JSON)), leaveGame::statusGame);
    }


}

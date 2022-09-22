package proyect.springReactive.cuatroRaya.infraestructure.controllers.tableController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.aplication.services.TablePartidaHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class ReturnTableController {


    @Bean
    public RouterFunction<ServerResponse> returnTablero (TablePartidaHandler tablePartidaHandler) throws Exception{
        return RouterFunctions
                .route (GET  ("table/{idTablero}")
                        .and (accept (MediaType.APPLICATION_JSON)), tablePartidaHandler::devolverEstructuraTablero);
    }




}

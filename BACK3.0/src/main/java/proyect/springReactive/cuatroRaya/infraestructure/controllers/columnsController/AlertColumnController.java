package proyect.springReactive.cuatroRaya.infraestructure.controllers.columnsController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.aplication.services.CheckFullColumn;
import proyect.springReactive.cuatroRaya.aplication.services.ColumnHandler;


import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class AlertColumnController {
    @Bean
    public RouterFunction<ServerResponse> checkColumnLimit (ColumnHandler columnHandler){
        return RouterFunctions
                .route (GET ("checkColumn/{idTablero}/{column}")
                        .and (accept (MediaType.APPLICATION_JSON)), columnHandler::columnLimit);
    }

    @Bean
    public RouterFunction<ServerResponse> checkColumnLast(CheckFullColumn checkFullColumn){
        return RouterFunctions
                .route (GET ("checkFullColumn/{idTablero}/{column}")
                        .and (accept (MediaType.APPLICATION_JSON)), checkFullColumn::checkBlockColumn);
    }
}

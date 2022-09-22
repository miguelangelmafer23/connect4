package proyect.springReactive.cuatroRaya.infraestructure.controllers.filePlaceController;

import com.sun.istack.NotNull;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.aplication.services.JugadorHandler;
import proyect.springReactive.cuatroRaya.aplication.services.PonerFichaHandler;
import proyect.springReactive.cuatroRaya.aplication.services.TablePartidaHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class newFileController {


    @Bean
    public RouterFunction<ServerResponse> routeBoxPlace (PonerFichaHandler ponerFichaHandler){
        return RouterFunctions
                .route (GET ("filePlace/{idPlayer}/{idTablero}/{column}")
                        .and (accept (MediaType.APPLICATION_JSON)), ponerFichaHandler::boxPlace);
    }
}

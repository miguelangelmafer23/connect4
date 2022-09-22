package proyect.springReactive.cuatroRaya.infraestructure.controllers.IPController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.aplication.services.GetIpHandler;
import proyect.springReactive.cuatroRaya.aplication.services.PonerFichaHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class IpController {

    @Bean
    public RouterFunction<ServerResponse> IPPlayer (GetIpHandler getIpHandler){
        return RouterFunctions
                .route (GET ("getipplayer/{idPlayer}")
                        .and (accept (MediaType.APPLICATION_JSON)), getIpHandler::getIPPlayer);
    }
}

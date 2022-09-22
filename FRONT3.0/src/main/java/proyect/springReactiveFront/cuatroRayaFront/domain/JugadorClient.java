package proyect.springReactiveFront.cuatroRayaFront.domain;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
public class JugadorClient {
    private final WebClient client;

    public JugadorClient(WebClient.Builder builder){
        client = builder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> serverPlayer(){
        return this.client.get().uri("/tablero/{player}")//Peticion al server
                .accept(MediaType.APPLICATION_JSON)//convierte lo recibido en JSON
                .retrieve().bodyToMono(Jugador.class)//El JSON se pasa a JugadorClass
                .map(Jugador::getName);//Habrá que devolver el id del tablero
    }

}

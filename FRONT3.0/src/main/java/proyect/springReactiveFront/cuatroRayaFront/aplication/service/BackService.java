package proyect.springReactiveFront.cuatroRayaFront.aplication.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.result.view.Rendering;
import proyect.springReactiveFront.cuatroRayaFront.domain.*;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@Slf4j
public class BackService {

    private final WebClient webClient;

    public BackService(WebClient.Builder webClientB){
        this.webClient = webClientB.baseUrl("http://localhost:8080").build();
    }

    public Mono<Rendering> createPlayer1(String username,String IpPlayer){
         Mono <Integer> idpartida = this.webClient.get()
                .uri("/username/" + username + "/ip/" + IpPlayer)
                 .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Integer.class);
       return Mono.just(Rendering.view("waitScreen")
               .modelAttribute("gameIdGenerated" ,idpartida)
               .modelAttribute("partida",new Partida()).build());
    }

    public Mono<Rendering> createPlayer2(String username, Integer partida , String IpPlayer){

        //CREA TABLERO CON IMG EMPTY
        String table[][] = new String[6][7];

        for(int i=0;i<7;i++)
        {
            for(int j=0;j<6;j++)
            {
                table[j][i] = "PLAYER_EMPTY";
            }
        }

        Mono<String> idTablero = this.webClient.get()//DEVOLVEMOS EL ID DEL TABLERO DE JUEGO
                 .uri("/username/" + username + "/" + partida + "/" + IpPlayer )
                 .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        return idTablero.flatMap(string ->{
            String[] parts = string.split(";");
            String part1 = parts[0]; // idTablero
            String part2 = parts[1]; // idJugador2

            Mono<Integer> player1ID = this.webClient.get() // PARTIDA TODAVIA NO CREADA
                    .uri("/returnp1/" + partida)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Integer.class);

            return player1ID.flatMap(integer -> {

                Mono<String> IpPlayerTurn = this.webClient.get() // TURNO PARTIDA
                        .uri("getipplayer/" + part2)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(String.class);

                return Mono.just(Rendering.view("redirect:/tablero/" + part1)
                        .modelAttribute("idTablero",part1)
                        .modelAttribute("idPlayer",part2)
                        .modelAttribute("idPartida",partida)
                        .modelAttribute("gameStatus",0)
                        .modelAttribute("board", new BoardDTO())
                        .modelAttribute("interfaz", table)
                        .modelAttribute("turno", 1)
                        .modelAttribute("IpPlayerTurn",IpPlayerTurn)
                        .build());
            });
        });
    }

    //
    public Mono<Rendering> makeMove(Integer idPlayer, Integer idTablero, Integer column,Integer idPartida){

        Mono<String[][]> interfaz = this.webClient.get()
                .uri("filePlace/"+ idPlayer + "/"+ idTablero +"/"+ column)//DEVUELVE INTERFAZ TABLERO
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String[][].class);

        Mono<Integer> gameStatus = this.webClient.get() // ESTADO PARTIDA
                .uri("getStatusCodeGame/" + idTablero + "/" + column)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Integer.class);

        Mono<Integer> turno = this.webClient.get() // TURNO PARTIDA
                .uri("getTurnCode/" + idTablero)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Integer.class);

        Mono<int[]> completColumn = this.webClient.get() // COLUMNA COMPLETA
                .uri("checkFullColumn/" + idTablero + "/" + column)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(int[].class);

        return turno.flatMap(integer -> {
            Mono<Integer> playerId = Mono.just(0) ;
            //System.out.println("TURNO de J " + integer);
            if (integer == 1){
                playerId = this.webClient.get()
                        .uri("/returnp2/" + idPartida) //
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Integer.class);
            }else if(integer == 2){
                playerId = this.webClient.get()
                        .uri("/returnp1/" + idPartida) //
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Integer.class);
            }

           return playerId.flatMap(integer1 -> {

               Mono<String> IpPlayerTurn = this.webClient.get() // TURNO PARTIDA
                       .uri("getipplayer/" + integer1)
                       .accept(MediaType.APPLICATION_JSON)
                       .retrieve()
                       .bodyToMono(String.class);

               return Mono.just(Rendering.view("redirect:/tablero/" + idTablero)
                       .modelAttribute("idTablero",idTablero)
                       .modelAttribute("idPlayer",integer1)
                       .modelAttribute("idPartida",idPartida)
                       .modelAttribute("gameStatus",gameStatus)
                       .modelAttribute("interfaz",interfaz)
                       .modelAttribute("board",new BoardDTO())
                       .modelAttribute("turno",integer)
                       .modelAttribute("fullColumn",completColumn)
                       .modelAttribute("JUGADOR1","🎉 GANA JUGADOR 1 🎉")
                       .modelAttribute("JUGADOR2","🎉 GANA JUGADOR 2 🎉")
                       .modelAttribute("IpPlayerTurn",IpPlayerTurn)
                       .build());
           });
        });
    }

    public Mono<Rendering> gameStarted() {

        Mono<Integer> firstPlayerset = this.webClient.get() // ESTADO PARTIDA
                    .uri("getFirstPlayerGame")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                   .bodyToMono(Integer.class);
        return firstPlayerset.flatMap(integer -> {
            if(integer!=0){
                return Mono.just(Rendering.view("player2")
                        .modelAttribute("gameIdGenerated" ,integer)
                        .modelAttribute("player2",new Player2Game()).build());
            }
            return Mono.just(Rendering.view("player1").
                    modelAttribute("player1", new Jugador()).build());
        });
    }

    public Mono<Rendering> searchOponent(Integer idPartida) {

        String table[][] = new String[6][7];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                table[j][i] = "PLAYER_EMPTY";
            }
        }

        Mono<Integer> player2ID = this.webClient.get() // PARTIDA TODAVIA NO CREADA
                .uri("/returnp2/" + idPartida)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Integer.class);

        Mono<Integer> player1ID = this.webClient.get() // PARTIDA TODAVIA NO CREADA
                .uri("/returnp1/" + idPartida)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Integer.class);

        Mono<Integer> boardId = this.webClient.get() // PARTIDA TODAVIA NO CREADA
                .uri("/boardId/" + idPartida)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Integer.class);

        return player1ID.flatMap(integer2 -> {

            Mono<String> IpPlayerTurn = this.webClient.get() // TURNO PARTIDA
                    .uri("getipplayer/" + integer2)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class);

            return player2ID.flatMap(integer -> {
                if (integer != 007) {

                    return Mono.just(Rendering.view("tablero")
                            .modelAttribute("idTablero", boardId)
                            .modelAttribute("interfaz", table)
                            .modelAttribute("idPartida", idPartida)
                            .modelAttribute("idPlayer", integer2)
                            .modelAttribute("gameStatus", 0)
                            .modelAttribute("turno", 2)
                            .modelAttribute("board", new BoardDTO())
                            .modelAttribute("IpPlayerTurn",IpPlayerTurn)
                            .build());
                }
                return Mono.just(Rendering.view("waitScreen")
                        .modelAttribute("gameIdGenerated", idPartida)
                        .modelAttribute("partida", new Partida()).build());
            });
        });
    }
}

package proyect.springReactiveFront.cuatroRayaFront.infraestructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.result.view.Rendering;
import proyect.springReactiveFront.cuatroRayaFront.aplication.service.BackService;
import proyect.springReactiveFront.cuatroRayaFront.domain.BoardDTO;
import proyect.springReactiveFront.cuatroRayaFront.domain.Jugador;
import proyect.springReactiveFront.cuatroRayaFront.domain.Partida;
import proyect.springReactiveFront.cuatroRayaFront.domain.Player2Game;
import reactor.core.publisher.Mono;

@PropertySource("classpath:/application.properties")
@Controller
public class JugadorController {

    @Autowired
    BackService backService;

    private final WebClient webClient;

    public JugadorController(WebClient.Builder webClientB){
        this.webClient = webClientB.baseUrl("http://localhost:8080").build();
    }

    @RequestMapping("/pruebaDeFuego")
    public String forInline (){
        return "test";
    }

    /*@GetMapping("/move/{idPlayer}/{idTablero}/{column}")
    public Mono<Rendering> makeMove (@PathVariable Integer idPlayer,@PathVariable Integer idTablero,@PathVariable Integer column,@PathVariable Integer idPartida ){
        return backService.makeMove(idPlayer,idTablero,column,idPartida);
    }*/


    //|||||||| creación usuario 1|||||||//

    @GetMapping("/player1")
    public String playerForm1(Model model) {
        model.addAttribute("player1", new Jugador());
        return "player1";
    }

    @PostMapping("/player1")
    public Mono<Rendering> playerSended1(@ModelAttribute Jugador jugador,Model model) {
        String nombre = jugador.getName();
        String ipPlayer = jugador.getIpPlayer();
       return backService.createPlayer1(nombre,ipPlayer);
    }
/////Creación jugador2 y asignación a partida/////

    @PostMapping("/player2")
    public Mono<Rendering> playerSended2(@ModelAttribute Player2Game player2) {
        System.out.println(player2);
        return backService.createPlayer2(player2.getPlayerName(),player2.getGameId(),player2.getIpPlayer());
    }

/////PRUEBAS TABLERO/////
    @PostMapping("/tablero")
    public Mono<Rendering> moveSended(@ModelAttribute BoardDTO board) {
        System.out.println(board);
        return backService.makeMove(board.getIdPlayer(),board.getIdTablero(),board.getColumn(),board.getIdPartida());
    }

// Este metodo devuelve el tablero actualizado llamda web client con ID tablero y recoger todos los datos de tablero


    @GetMapping("/tablero/{id}")
    public Mono<Rendering> partida(@PathVariable int id) {

        Mono<String[][]> board = this.webClient
                .get()
                .uri("table/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String[][].class);

        Mono<Integer> idPartida = this.webClient
                .get()
                .uri("gameId/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Integer.class);

        return idPartida.flatMap(integer0 -> {

        Mono<Integer> gameStatus = this.webClient
                .get()
                .uri("getStatusCodeGame/"+ integer0 )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Integer.class);

        Mono<Integer> turno = this.webClient
                .get()
                .uri("getTurnCode/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Integer.class);

        return turno.flatMap(integer1 -> {
            Mono<Integer> idPlayer = Mono.just(0);
            if (integer1 == 1) {
                idPlayer = this.webClient
                        .get()
                        .uri("returnp1/" + integer0)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Integer.class);
            }else if(integer1 == 2){
                idPlayer = this.webClient.get()
                        .uri("/returnp2/" + integer0) //
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Integer.class);
            }

            return idPlayer.flatMap(integer2 -> {
                Mono<String> IpPlayerTurn = this.webClient.get() // TURNO PARTIDA
                        .uri("getipplayer/" + integer2)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(String.class);

                return Mono.just(Rendering.view("index")
                        .modelAttribute("interfaz", board)
                        .modelAttribute("turno", integer1)
                        .modelAttribute("JUGADOR1", "🎉 GANA JUGADOR 1 🎉")
                        .modelAttribute("JUGADOR2", "🎉 GANA JUGADOR 2 🎉")
                        .modelAttribute("idPartida", integer0)
                        .modelAttribute("idTablero", id)
                        .modelAttribute("idPlayer", integer2)
                        .modelAttribute("IpPlayerTurn",IpPlayerTurn)
                        .modelAttribute("board",new BoardDTO())
                        .modelAttribute("gameStatus",gameStatus)
                        .build());

                });
            });
        });
    }

// Este metodo devuelve el tablero actualizado

    @GetMapping("/home")
    public String initialView() {
        return "home";
    }

    @PostMapping("/home")
    public Mono<Rendering> startGame() {
        return backService.gameStarted();
    }

    ///Buscar jugador
    @PostMapping("/searchPlayer")
    public Mono<Rendering> searchOponent(@ModelAttribute Partida partida) {
        System.out.println(partida.getIdPartida());
        return backService.searchOponent(partida.getIdPartida());
    }

}

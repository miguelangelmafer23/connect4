package proyect.springReactive.cuatroRaya.aplication.services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import proyect.springReactive.cuatroRaya.domain.Player;
import proyect.springReactive.cuatroRaya.domain.Partida;
import proyect.springReactive.cuatroRaya.domain.Movement;
import proyect.springReactive.cuatroRaya.domain.PlayerEnum;
import proyect.springReactive.cuatroRaya.exception.NotFoundException;
import proyect.springReactive.cuatroRaya.infraestructure.repository.JugadorRepository;
import proyect.springReactive.cuatroRaya.infraestructure.repository.PartidaRepository;
import proyect.springReactive.cuatroRaya.infraestructure.repository.TableroRepository;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class PonerFichaHandler {
    @Autowired
    TableroRepository tableroRepository;
    @Autowired
    JugadorRepository jugadorRepository;
    @Autowired
    CheckPlayerWin checkPlayerWin;
    @Autowired
    MatchService matchService;
    @Autowired
    PartidaRepository partidaRepository;
    @Autowired
    TablePartidaHandler tablePartidaHandler;
    boolean firstToken = false;
@SneakyThrows
public Mono<ServerResponse> boxPlace(ServerRequest serverRequest) {

        Movement movementActual = tableroRepository.findById(Integer.valueOf(serverRequest.pathVariable("idTablero")))
                .orElseThrow(()-> new Exception ("No se ha encontrado el tablero"));
        Player player = jugadorRepository.findById(Integer.valueOf(serverRequest.pathVariable("idPlayer")))
                .orElseThrow(()-> new NotFoundException("Player not found"));
        Partida partida = partidaRepository.findById(movementActual.getPartida().getIdPartida()).orElseThrow();
        //COMPROBAMOS SI player (playerActual) ES A QUIEN LE TOCA JUGAR
        if(partida.getFirstPlayer().getId() == player.getId()){
            return checkColumn(player, movementActual,serverRequest.pathVariable("column"), serverRequest);
        } else if (partida.getSecondPlayer().getId() == player.getId()) {
            return checkColumn(player, movementActual,serverRequest.pathVariable("column"), serverRequest);
        }
    return ServerResponse.noContent().build();
}
    private Mono<ServerResponse> checkColumn(Player player, Movement movement, String column, ServerRequest serverRequest) {
        for(int i = 5; i >=0; i--){
            if (movement.getBoard()[i][Integer.valueOf(column)] == PlayerEnum.PLAYER_EMPTY){
                checkPlayerAndTurn(player, movement,i,Integer.valueOf(column), serverRequest);
                return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(movement.getBoard());
            }
        }
        return ServerResponse.noContent().build();
    }
    //MÉTODO CREADO A PARTIR DE LINEA DE saveColumn(tablero) DEL MÉTODO checkPlayerAndTurn
    private Mono<ServerResponse> saveTable (Movement movement, ServerRequest serverRequest, int column, PlayerEnum playerEnum){
        tableroRepository.save(movement);
        //tablePartidaHandler.devolverEstructuraTablero(serverRequest);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(String.valueOf(movement)));
    }
    private Mono<ServerResponse> checkPlayerAndTurn(Player player, Movement movement, int i, int column , ServerRequest serverRequest) {

        if(player.getId() == movement.getPlayer().getId() && movement.getTurno()==1){
            movement.setBoard(i,Integer.valueOf(column), PlayerEnum.PLAYER_ONE);
            movement.setTurno(2);
            movement.setPlayer(movement.getPartida().getSecondPlayer());
            saveTable(movement,serverRequest, column,PlayerEnum.PLAYER_ONE);
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(player));

        }else if(player.getId() == movement.getPlayer().getId() && movement.getTurno()==2){
            movement.setBoard(i, Integer.valueOf(column), PlayerEnum.PLAYER_TWO);
            movement.setTurno(1);
            movement.setPlayer(movement.getPartida().getFirstPlayer());
            saveTable(movement,serverRequest, column, PlayerEnum.PLAYER_TWO);
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(player));
        }else{
            return ServerResponse.notFound().build();
        }
    }

    public Mono<ServerResponse> getWinner(Movement table, PlayerEnum player, int column) {
          if(matchService.hasPlayerWon(table.getBoard(),column, player) && player == PlayerEnum.PLAYER_ONE){
              Partida partida = table.getPartida();
              partida.setWinStatus(1);
              partida.setJugada(true);
              partidaRepository.save(partida);
              return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(1));
          }else if(matchService.hasPlayerWon(table.getBoard(),column, player) && player == PlayerEnum.PLAYER_TWO){
              Partida partida = table.getPartida();
              partida.setWinStatus(2);
              partida.setJugada(true);
              partidaRepository.save(partida);
              return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(2));
          }
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(0));
    }
    private Mono<ServerResponse> getTurnStatus(Movement table) {
        Integer turn = table.getTurno();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(turn));
    }
    /**
     * Método para saber el winStatusCode (1, 2, 0, -1) a raiz de un idTablero.
     * Explicación: Este método solo llama a winPlayer pasándole el tablero. El tablero se guarda en BB.DD.
     * en el método saveTable
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> getWinStatusCode (ServerRequest serverRequest){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Movement table = tableroRepository.findById(Integer.valueOf(serverRequest.pathVariable("idTablero")))
                .orElseThrow(()->new NotFoundException("Table Not Found"));
        Integer columna = Integer.valueOf(serverRequest.pathVariable("column"));

        if(table.getTurno() == PlayerEnum.PLAYER_TWO.ordinal()){
            return Mono.from(getWinner(table,PlayerEnum.PLAYER_ONE,columna));
        }else if(table.getTurno() == PlayerEnum.PLAYER_ONE.ordinal()) {
            return Mono.from(getWinner(table,PlayerEnum.PLAYER_TWO,columna));
        }
        return ServerResponse.badRequest().build();
    }
    public Mono<ServerResponse> getTurnCode (ServerRequest serverRequest){
        Movement table = tableroRepository.findById(Integer.valueOf(serverRequest.pathVariable("idTablero")))
                .orElseThrow(()->new NotFoundException("Table Not Found"));
        return Mono.from (getTurnStatus(table));
    }

    public Mono<ServerResponse> estadoPatida(ServerRequest serverRequest) {
        Partida p = partidaRepository
                .findById(Integer.valueOf(serverRequest.pathVariable("idPartida")))
                .orElseThrow(()->new NotFoundException("Tablero Not Found"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                body(BodyInserters.fromValue(p.getWinStatus()));
    }
}

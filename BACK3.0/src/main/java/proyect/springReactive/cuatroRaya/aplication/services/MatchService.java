package proyect.springReactive.cuatroRaya.aplication.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import proyect.springReactive.cuatroRaya.domain.Player;
import proyect.springReactive.cuatroRaya.domain.Movement;
import proyect.springReactive.cuatroRaya.domain.PlayerEnum;

import java.util.Arrays;

@Slf4j
@Component
public class MatchService {

    private static final int ROW_LENGTH = 6;
    private static final int COLUMN_LENGTH = 7;

// 6   - - - - - -
// 5   - - - - - -
// 4   - - - - - -
// 3   - - - - - -
// 2   - - - - - -
// 1   - - - - - -
// 0   - - - - - -

    /**
     * Resets board filling all elements with PLAYER_EMPTY.
     * @param matchBoard Match board.
     */
    void resetBoard(PlayerEnum[][] matchBoard) {
        for (PlayerEnum[] boardRow : matchBoard) {
            Arrays.fill(boardRow, PlayerEnum.PLAYER_EMPTY);
        }
    }

    boolean hasPlayerWon(PlayerEnum[][] matchBoard, int column, PlayerEnum player) {
        // Check vertical chips

        if(checkColumnChips(matchBoard, column, player)) return true;

        // Check horizontal chips
        for (int i = 0; i < 6; i ++)
        if(checkRowChips(matchBoard[i], player)) return true;

        if(checkDiagonalChips(matchBoard, player)) return true;

        // Check diagonal chips

        return false;
    }

    /**
     * Checks if there is 4 consecutive chips in a row.
     * @param boardRow Board row.
     * @param player Player to check.
     */
    //protected boolean checkRowChips(PlayerEnum[] boardRow, PlayerEnum player) {
    protected boolean checkRowChips(PlayerEnum[] boardRow, PlayerEnum player) {
        int playerChips = 0;    //---------

        for(int i = 0; i < boardRow.length; i++) {
            if(boardRow[i] == player){
                playerChips++;
                if(playerChips == 4){
                    System.out.println(" Gana jugador: " + player );
                    return true;
                }
            }
            else {
                playerChips = 0;
            }
        }
        return false;
    }

    /**
     * Checks if there is 4 consecutive chips in a column.
     * @param board Board.
     * @param player Player to check.
     */
    //protected boolean checkColumnChips(PlayerEnum[][] board, int columnIndex, PlayerEnum player) {
    protected boolean checkColumnChips(PlayerEnum [][] board, int columnIndex, PlayerEnum player) {
        int playerChips = 0;
        for(int i = 0; i < board.length; i++){
            if (board [i][columnIndex] == player){

                playerChips++;
                if(playerChips == 4){
                    System.out.println(" Gana jugador: " + player );
                    return true;
                }
            } else {
                playerChips = 0;
            }
        }
        return false;
    }

    protected boolean checkDiagonalChips(PlayerEnum [][] board, PlayerEnum player) {
        int playerChips = 0;
        for (int limita = 3; limita > 0; limita--) {
            for (int i = 0, j = limita; i < 6 && j < 7; i++, j++) {
                if (board[i][j] == player) {

                    playerChips++;
                    if (playerChips == 4) {
                        System.out.println(" Gana jugador: " + player);
                        return true;
                    }
                } else {
                    playerChips = 0;
                }
            }
        }
        for(int limitb = 2; limitb>0; limitb--){
            for(int i = limitb ,j=0; i<6 && j<7; i++, j++ ){
                if (board[i][j] == player) {

                    playerChips++;
                    if (playerChips == 4) {
                        System.out.println(" Gana jugador: " + player);
                        return true;
                    }
                } else {
                    playerChips = 0;
                }
            }
        }
        for(int limitc = 3; limitc<6; limitc++){
            for(int i = limitc ,j=0; i>=0 && j<7; i--, j++ ){
                if (board[i][j] == player) {

                    playerChips++;
                    if (playerChips == 4) {
                        System.out.println(" Gana jugador: " + player);
                        return true;
                    }
                } else {
                    playerChips = 0;
                }
            }
        }

        for(int limitd = 2; limitd>=0; limitd--){
            for(int i = limitd ,j=6; i<6 && j<7; i++, j-- ){
                if (board[i][j] == player) {

                    playerChips++;
                    if (playerChips == 4) {
                        System.out.println(" Gana jugador: " + player);
                        return true;
                    }
                } else {
                    playerChips = 0;
                }
            }
        }
        return false;
    }
}

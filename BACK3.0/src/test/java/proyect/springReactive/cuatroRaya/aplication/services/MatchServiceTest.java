package proyect.springReactive.cuatroRaya.aplication.services;

import org.junit.jupiter.api.Test;
import proyect.springReactive.cuatroRaya.domain.PlayerEnum;

import static proyect.springReactive.cuatroRaya.domain.PlayerEnum.*;

import static org.junit.jupiter.api.Assertions.*;

class MatchServiceTest {

    @Test
    void checkRowChips_rowWithFourConsecutiveChips_returnsTrue() {
        PlayerEnum[] boardRow = new PlayerEnum[] {
                PLAYER_ONE, PLAYER_ONE, PLAYER_ONE, PLAYER_ONE, PLAYER_EMPTY, PLAYER_EMPTY
        };

        MatchService matchService = new MatchService();
        assertTrue(matchService.checkRowChips(boardRow, PlayerEnum.PLAYER_ONE));
    }

    @Test
    void checkRowChips_rowWithOneChip_returnsFalse() {
        PlayerEnum[] boardRow = new PlayerEnum[] {
                PLAYER_ONE, PLAYER_TWO, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY
        };
        MatchService matchService = new MatchService();
        assertFalse(matchService.checkRowChips(boardRow, PlayerEnum.PLAYER_TWO));
    }

    @Test
    void checkRowChips_fourNonConsecutiveChips_returnsFalse() {
        PlayerEnum[] boardRow = new PlayerEnum[] {
                PLAYER_ONE, PLAYER_TWO, PLAYER_ONE, PLAYER_ONE, PLAYER_ONE, PLAYER_EMPTY
        };
        MatchService matchService = new MatchService();
        assertFalse(matchService.checkRowChips(boardRow, PlayerEnum.PLAYER_ONE));
    }

    @Test
    void checkColumnChips_columnWithFourConsecutiveChips_returnsTrue() {
        PlayerEnum[][] matchBoard = new PlayerEnum[][] {
                {PLAYER_ONE, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_ONE, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_ONE, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_ONE, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
        };

        MatchService matchService = new MatchService();
        assertTrue(matchService.checkColumnChips(matchBoard, 0, PlayerEnum.PLAYER_ONE));
    }

    @Test
    void checkColumnChips_columnWithOneChip_returnsFalse() {
        PlayerEnum[][] matchBoard = new PlayerEnum[][] {
                {PLAYER_ONE, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_TWO, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
        };

        MatchService matchService = new MatchService();
        assertTrue(matchService.checkColumnChips(matchBoard, 0, PLAYER_TWO));
    }

    @Test
    void checkColumnChips_fourNonConsecutiveChips_returnsFalse() {
        PlayerEnum[][] matchBoard = new PlayerEnum[][] {
                {PLAYER_ONE, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_TWO, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_ONE, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_ONE, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_ONE, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
                {PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY},
        };

        MatchService matchService = new MatchService();
        assertTrue(matchService.checkColumnChips(matchBoard, 0, PLAYER_ONE));
    }
}
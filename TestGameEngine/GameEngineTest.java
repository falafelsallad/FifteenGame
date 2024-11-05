import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameEngineTest {
    private GameEngine gameEngine;

    @Before
    public void setUp() {
        gameEngine = new GameEngine();
    }

    @Test
    public void TestInitializeBoard() {
        gameEngine.initializeBoard();
        int[][] board = gameEngine.getBoard();
        assertNotNull(board);
        assertEquals(4, board.length);
        assertEquals(4, board[0].length);
    }

    @Test
    public void testEmptyTilePosition() {
        gameEngine.initializeBoard();
        int[][] board = gameEngine.getBoard();
        boolean foundEmptyTile = false;
        for (int i = 0; i < board.length; i++) {           // 4x4 board iteration
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    foundEmptyTile = true;
                    break;
                }
            }
        }
        assertTrue(foundEmptyTile);
    }

    @Test
    public void testMoveTile() {
        gameEngine.initializeBoard();
        int initialEmptyRow = gameEngine.getEmptyRow();
        int initialEmptyCol = gameEngine.getEmptyCol();
        int targetRow;
        if (initialEmptyRow == 0) targetRow = 1;
        else targetRow = initialEmptyRow - 1;
        int targetCol = initialEmptyCol;

        gameEngine.moveTile(targetRow, targetCol);

        assertEquals(0, gameEngine.getBoard()[targetRow][targetCol]);
        assertEquals(targetRow, gameEngine.getEmptyRow());
        assertEquals(targetCol, gameEngine.getEmptyCol());
    }

    @Test
    public void testIsSolved() {
        gameEngine.initializeBoard();
        assertFalse(gameEngine.isSolved());

        int[][] solvedGame = {
                {1, 2, 3, 4,},
                {5, 6, 7, 8,},
                {9, 10, 11, 12,},
                {13, 14, 15, 0}
        };
        gameEngine.setBoard(solvedGame);
//        gameEngine.setEmptyRow(3);
//        gameEngine.setEmptyCol(3);

        assertTrue(gameEngine.isSolved());


    }


}

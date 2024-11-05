import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameEngine extends JPanel {
    private static final int SIZE = 4;
    private int[][] board;
    private int emptyRow;
    private int emptyCol;


    public GameEngine() {
        setLayout(new GridLayout(SIZE, SIZE));
        initializeBoard();
        drawBoard();
    }

    public void initializeBoard() {
        List<Integer> tiles = new ArrayList<>();
        for (int i = 0; i < SIZE * SIZE; i++) {
            tiles.add(i);
        }
        Collections.shuffle(tiles);

        board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int tile = tiles.remove(0);
                board[i][j] = tile;
                if (tile == 0) {
                    emptyRow = i;
                    emptyCol = j;
                }
            }
        }
    }

    public void drawBoard() {
        removeAll();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JButton button;
                if (board[i][j] == 0) {
                    button = new JButton("");
                } else {
                    button = new JButton(String.valueOf(board[i][j]));
                }
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> moveTile(finalI, finalJ));
                add(button);
            }
        }
        revalidate();
        repaint();
    }

    public void moveTile(int row, int column) {
        if (Math.abs(row - emptyRow) == 1 && column == emptyCol || (Math.abs(column - emptyCol) == 1 && row == emptyRow)) {
            board[emptyRow][emptyCol] = board[row][column];
            board[row][column] = 0;
            emptyRow = row;
            emptyCol = column;
            drawBoard();
            if (isSolved()) {
                JOptionPane.showMessageDialog(this, "Congratulations! You solved the puzzle!");
                initializeBoard();
                drawBoard();
            }
        }
    }

    public boolean isSolved() {
        int count = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == SIZE - 1 && j == SIZE - 1) {
                    return board[i][j] == 0;
                }
                if (board[i][j] != count) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }


    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getEmptyRow() {
        return emptyRow;
    }

    public void setEmptyRow(int emptyRow) {
        this.emptyRow = emptyRow;
    }

    public int getEmptyCol() {
        return emptyCol;
    }

    public void setEmptyCol(int emptyCol) {
        this.emptyCol = emptyCol;
    }

    public void setAlmostSolvedBoard() {
        int[][] almostSolvedGame = {
                {1, 2, 3, 4,},
                {5, 6, 7, 8,},
                {9, 10, 11, 0,},
                {13, 14, 15, 12}
        };
        setBoard(almostSolvedGame);
        setEmptyRow(2);
        setEmptyCol(3);
        drawBoard();
    }

}

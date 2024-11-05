package FifteenPuzzleWithImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameEngine2 extends JPanel {
    private static final int SIZE = 4;
    Path path = Paths.get("src/Parrot.jpg");
    private int[][] board;
    private int emptyRow;
    private int emptyCol;
    private BufferedImage image;
    private BufferedImage[] imagePieces;

    public GameEngine2() {
        setLayout(new GridLayout(SIZE, SIZE));
        loadImage();
        initializeBoard();
        drawBoard();
    }

    private void loadImage() {
        try {
            File imageFile = new File(path.toString());
            System.out.println("Loading image from: " + imageFile.getAbsolutePath());       // debug method, see if the path is correct
            image = ImageIO.read(imageFile);
            imagePieces = new BufferedImage[SIZE * SIZE];
            int pieceWidth = image.getWidth() / SIZE;
            int pieceHeight = image.getHeight() / SIZE;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {     // Splitting the image into pieces
                    imagePieces[i * SIZE + j] = image.getSubimage(j * pieceWidth, i * pieceHeight, pieceWidth, pieceHeight);
                }
            }
        } catch (IOException e) {
            System.err.println("Could not load image: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void initializeBoard() { // mechanics
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

    public void drawBoard() { // visual
        removeAll();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JButton button = new JButton();

                if (board[i][j] != 0) {  // setting thee pieces of the image as icons for the buttons
                    button.setIcon(new ImageIcon(imagePieces[board[i][j]].getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
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

    public void moveTile(int row, int column) { // movement
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
        int count = 1; // counting from 1 to 15
        for (int i = 0; i < SIZE; i++) { // going through all i:s and then all j:s for all i:s
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

    public void setAlmostSolvedBoard() { // setting the board to almost solved state
        int [][] almostSolvedBoard = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 0, 15}
        };
        setBoard(almostSolvedBoard);
        setEmptyRow(3);
        setEmptyCol(2);
        drawBoard();

    }
}

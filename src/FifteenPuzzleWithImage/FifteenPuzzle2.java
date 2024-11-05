package FifteenPuzzleWithImage;

import javax.swing.*;
import java.awt.*;

public class FifteenPuzzle2 extends JFrame {
    private GameEngine2 gameEngine;

    public FifteenPuzzle2() {
        setTitle("Fifteen Puzzle");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        gameEngine = new GameEngine2();
        add(gameEngine, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> {
            gameEngine.initializeBoard();
            gameEngine.drawBoard();
        });
        add(newGameButton, BorderLayout.SOUTH);

        bottomPanel.add(newGameButton, BorderLayout.SOUTH);
        JButton almostSolvedButton = new JButton("Almost Solved");
        almostSolvedButton.addActionListener(e -> gameEngine.setAlmostSolvedBoard());
        bottomPanel.add(almostSolvedButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }


    public static void main(String[] args) {
        new FifteenPuzzle2();

    }
}
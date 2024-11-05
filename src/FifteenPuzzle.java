import javax.swing.*;
import java.awt.*;

public class FifteenPuzzle extends JFrame {
    private GameEngine gameEngine;

    public FifteenPuzzle() {
        setTitle("Fifteen Puzzle");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        gameEngine = new GameEngine();
        add(gameEngine, BorderLayout.CENTER);

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
        new FifteenPuzzle();

    }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class HangmanGUI extends JFrame {

    /** Panel to draw the hangman figure */
    private static class StickFigurePanel extends JPanel {

        private int guesses = 0;

        public StickFigurePanel(int size) {
            setPreferredSize(new Dimension(size, size));
        }

        public void updateGuesses(int guesses) {
            this.guesses = guesses;
            repaint();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            int w = getWidth();
            int h = getHeight();

            // set up some coordinates relative to panel size
            int leftGallowBase = (int)(0.1*w);
            int rightGallowBase = (int)(0.3*w);
            int groundLevel = (int)(0.9*h);
            int leftSupport = (int)(0.2*w);
            int topBar = (int)(0.1*h);
            int center = (int)(0.5*w);
            int headTop = (int)(0.2*h);
            int headWidth = (int)(0.2*w);
            int headHeight = (int)(0.2*h);
            int bodyTop = headTop + headHeight;
            int bodyBottom = (int)(0.6*h);
            int armTop = bodyTop + (int)(0.02*h);
            int armWidth = (int)(0.1*w);
            int armHeight = (int)(0.1*h);
            int legWidth = (int)(0.08*w);
            int legBottom = (int)(0.8*h);

            // draw hangman image
            g.setColor(Color.BLACK);
            g.drawLine(leftGallowBase, groundLevel, rightGallowBase, groundLevel);
            g.drawLine(leftSupport, groundLevel, leftSupport, topBar);
            g.drawLine(leftSupport, topBar, center, topBar);
            g.drawLine(center, topBar, center, headTop);
            if(guesses < 6) {
                g.drawOval(center - headWidth/2, headTop, headWidth, headHeight);
            }
            if(guesses < 5) {
                g.drawLine(center, bodyTop, center, bodyBottom);
            }
            if(guesses < 4) {
                g.drawLine(center, armTop, center - armWidth, armTop + armHeight);
            }
            if(guesses < 3) {
                g.drawLine(center, armTop, center + armWidth, armTop + armHeight);
            }
            if(guesses < 2) {
                g.drawLine(center, bodyBottom, center - legWidth, legBottom);
            }
            if(guesses < 1) {
                g.drawLine(center, bodyBottom, center + legWidth, legBottom);

            }
        }
    }

    private static class GamePanel extends JPanel {
        private final HangmanInterface game;
        private JLabel wordLabel = new JLabel();
        private JLabel guessCountLabel = new JLabel();
        private JLabel guessedLettersLabel = new JLabel();
        private StickFigurePanel stickPanel;

        public GamePanel(HangmanInterface game) {
            this.game = game;
            stickPanel = new StickFigurePanel(300);

            // TODO: make layout prettier?
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            add(wordLabel);
            add(guessCountLabel);
            add(guessedLettersLabel);
            add(stickPanel);

            addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent ev) {
                        handleGuess(ev.getKeyChar());
                    }
                });
            startGame();
        }

        private void startGame() {
            game.initGame(6);
            updateDisplay();
        }

        private void updateDisplay() {
            String guessStr = game.getPuzzle();
            wordLabel.setText("<html><h1>"+guessStr+"</hl>");
            guessCountLabel.setText("<html><h2>Guesses remaining: " + game.getGuessesRemaining() + "</h2>");

            String letters = "";
            for(char c : game.getGuessedLetters()) {
                letters += c + " ";
            }
            guessedLettersLabel.setText("<html><h2>Guessed letters: " + letters + "</h2>");

            stickPanel.updateGuesses(game.getGuessesRemaining());
        }

        private void handleGuess(char c) {
            if(!game.isGameOver()) {
                game.updateWithGuess(c);
                updateDisplay();
            }

            if(game.isGameOver()) {
                // Question user about another game, and either quit or restart
                String msg = game.isComplete()
                    ? "Hooray! You win!"
                    : "You lose. The word was: " + game.getSecretWord();

                int choice = JOptionPane.showConfirmDialog(this, msg + "\nPlay again?",
                                                           "Play again?",
                                                           JOptionPane.YES_NO_OPTION );
                if ( choice == JOptionPane.NO_OPTION ) {
                    System.exit(0);
                } else {
                    startGame();
                }
            }
        }
    }

    public HangmanGUI(HangmanInterface game) {
        super("Hangman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new GamePanel(game);
        add(panel, BorderLayout.CENTER);
        pack();
        panel.requestFocusInWindow();
    }


    /**
     * Let's play hangman, this time with a GUI!
     *
     * First command line argument is name of the dictionary file.
     * Second (optional) command line allows user to specify evil game.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        if(args.length >= 1) {
            String dictionaryFile = args[0];
            boolean evil = args.length > 1 && args[1].equals("evil");

            final HangmanInterface game = evil ?
                new EvilHangman(dictionaryFile) :
                new FairHangman(dictionaryFile);

            SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JFrame frame = new HangmanGUI(game);
                        frame.setVisible(true);
                    }
                });

        } else {
            System.out.println("Improper number of arguments");
        }
    }

}

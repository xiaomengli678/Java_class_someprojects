import javax.swing.*;
import java.util.*;

public class Hangman {

    /**
     * This method prints ASCII art of the hanged man.
     * @param guesses The number of guesses remaining
     */
    public static void drawAsciiMan(int guesses){

        // top of gallows
        System.out.println(" _______");

        // head (or not)
        if(guesses < 6) {
            System.out.println(" |     O");
        } else {
            System.out.println(" |");
        }


        // Body and arms
        switch (guesses) {

        case 6:
        case 5:
            // no body
            System.out.println(" |");
            break;

        case 4:
            // body, no arms
            System.out.println(" |     |");
            break;

        case 3:
            // body and left arm
            System.out.println(" |    /|");
            break;

        default:
            // body and both arms
            System.out.println(" |    /|\\");
            break;

        }

        // Legs
        switch (guesses) {

        case 1:
            // left leg
            System.out.println(" |    /");
            break;

        case 0:
            // both legs
            System.out.println(" |    / \\");
            break;

        default:
            // no legs
            System.out.println(" |");
            break;

        }

        // bottom of gallows
        System.out.println("_|_" );
    }

    public static void playConsoleHangman(HangmanInterface game) {

        System.out.println("Welcome to Hangman! Try to guess my word.");
        Scanner sc = new Scanner(System.in);


        boolean playAgain = true;
        while(playAgain) {

            game.initGame(6);
        
            while(!game.isGameOver()) {
                System.out.println(); // blank line between guesses
                System.out.println("Guessed letters: " + game.getGuessedLetters());
                System.out.println("Guesses remaining: " + game.getGuessesRemaining());
                drawAsciiMan(game.getGuessesRemaining());
                System.out.println("Word: " + game.getPuzzle());

                System.out.print("Guess a letter: ");
                // grabbing the entire next token and then taking only 1st char
                char letter = sc.next().trim().charAt(0);

                if(!game.updateWithGuess(letter)) {
                    System.out.println("Sorry, there is no " + letter);
                }
            }
        
            drawAsciiMan(game.getGuessesRemaining());
            System.out.println("Word: " + game.getPuzzle());
            if(game.isComplete()) {
                System.out.println("Hooray! You win!");
            } else {
                System.out.println("You lose. The word was: " + game.getSecretWord());
            }

            System.out.print("Play again? (y/n) ");
            String response = sc.next().trim().toLowerCase();
            playAgain = response.equals("y");
        }
    }

    /**
     * Let's play hangman!
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

            playConsoleHangman(game);

        } else {
            System.out.println("Improper number of arguments");
        }
    }
}

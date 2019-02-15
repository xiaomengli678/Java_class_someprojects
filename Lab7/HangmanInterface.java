import java.util.Collection;

/**
 * Interface for managing a hangman game.
 */
public interface HangmanInterface {

    /** Character used for unknown letters in word being guessed. */
    char BLANK = '-';

    /** 
     * Initialize a new game. This should reset all the bookkeeping.
     * @param guesses Number of guesses for game.
     */
    void initGame(int guesses);

    /**
     * How many guesses left until the player loses?
     * @return number of guesses remaining.
     */
    int getGuessesRemaining();

    /**
     * What letters has the player already guessed?
     * @return Collection of letters guessed so far.
     */
    Collection<Character> getGuessedLetters();

    /**
     * What is the current state of the puzzle? The String will have
     * letters in proper position for correct guesses and blanks for
     * unknowns.
     * @return String representation of the puzzle.
     */
    String getPuzzle();

    /**
     * What is the secret word?
     *
     * For a fair game, this the secret word chosen at the beginning
     * of the game.
     *
     * For an evil game, this would be a word that is consistent with
     * the guesses so far.
     *
     * When playing an actual game, this method would only be called
     * when the game is over, but it may be called at any point after
     * the game is initialized for testing purposes.
     *
     * @return The mystery word.
     */
    String getSecretWord();

    /**
     * Is the guessed puzzle complete? That is, have all the blanks
     * been replaced with letters?
     * @return True if puzzle is completed, false if any blanks remain.
     */
    boolean isComplete();

    /**
     * Is the game over? 
     *
     * Game can end when player loses by running out of guesses or
     * when player wins by successfully filling in the puzzle.
     *
     * @return True if game is over, false if not.
     */
    boolean isGameOver();

    /**
     * Respond to player's guess, updating internal bookkeeping
     * appropriately.
     *
     * @param letter The guessed letter.
     * @param True if letter was found in the word.
     */
    boolean updateWithGuess(char letter);

}

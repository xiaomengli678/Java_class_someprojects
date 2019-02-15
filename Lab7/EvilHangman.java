/**
 *@version cs251 Lab002 Date: 11/01/2018
 *@author Xiaomeng Li
 **/

import java.util.Collection;
import java.io.*;
import java.util.*;

/**
 * This is the EvilHangman class. The evilhangman will change the true word
 * according to user's input. Basically, even if you replay it, the true word
 * would be different since I set it to be changing all the time.
 **/
public class EvilHangman extends FairHangman implements HangmanInterface {
    private static int remainGuess = 0;
    private static int allowedGuess = 0;
    private static int usedGuess = 0;
    private static String actualWord = "";
    private static ArrayList<String> dictionaryList = new ArrayList<>();
    private static ArrayList<Character> guessList = new ArrayList<>();
    private static HashSet<Character> allCharGuessed = new HashSet<>();
    private static int wordSize;
    private static ArrayList<String> dictionaryListOld;
    /** Character used for unknown letters in word being guessed. */
    private char BLANK = '-';

    /**
     * Initialize a new game. This should reset all the bookkeeping.
     * @param guesses Number of guesses for game.
     */
    public void initGame(int guesses){
        allowedGuess = guesses;
        remainGuess = guesses;
        usedGuess = 0;
        allCharGuessed = new HashSet<>();
        guessList = new ArrayList<>();
        dictionaryList = dictionaryListOld;
        for(int i = 0; i<wordSize; ++i){
            guessList.add(BLANK);
        }
    }

    /**
     * How many guesses left until the player loses?
     * @return number of guesses remaining.
     */
    public int getGuessesRemaining(){
        remainGuess = allowedGuess - usedGuess;
        return remainGuess;
    }

    /**
     * What letters has the player already guessed?
     * @return Collection of letters guessed so far.
     */
    public Collection getGuessedLetters(){
        return allCharGuessed;
    }

    /**
     * What is the current state of the puzzle? The String will have
     * letters in proper position for correct guesses and blanks for
     * unknowns.
     * @return String representation of the puzzle.
     */
    public String getPuzzle(){
        StringBuilder puzzle = new StringBuilder();
        for (char s : guessList) {
            puzzle.append(s);
        }
        return puzzle.toString();


    }

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
    public String getSecretWord(){
        return actualWord;
    }

    /**
     * Is the guessed puzzle complete? That is, have all the blanks
     * been replaced with letters?
     * @return True if puzzle is completed, false if any blanks remain.
     */
    public boolean isComplete(){
        for (int i = 0; i < guessList.size(); i++) {
            if (guessList.get(i) == BLANK) {
                return false;
            }
        }
        return true;
    }

    /**
     * Is the game over?
     *
     * Game can end when player loses by running out of guesses or
     * when player wins by successfully filling in the puzzle.
     *
     * @return True if game is over, false if not.
     */
    public boolean isGameOver(){
        StringBuilder newpuzzle = new StringBuilder();
        for (char s : guessList) {
            newpuzzle.append(s);
        }


        if(remainGuess < 1 || newpuzzle.toString().equals(actualWord)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Respond to player's guess, updating internal bookkeeping
     * appropriately. When the player is totally running out of guesses,
     * the system will choose a word from current dictionary in
     * order to give an answer.
     * @param letter The guessed letter.
     * @return "True" if letter was found in the word.
     */
    public boolean updateWithGuess(char letter){
        List<Character> guessListOld = new ArrayList<>(guessList);
        allCharGuessed.add(letter);
        usedGuess = usedGuess + 1;

        dictionaryList= divideConquer(dictionaryList, letter);

        if (dictionaryList.size() == 1 || usedGuess  == allowedGuess) {
            actualWord = dictionaryList.get(0);
            
        }
        for(int i = 0; i<wordSize; ++i){
            if (guessListOld.get(i) != guessList.get(i)){
                return true;
            }
        }
        return false;
    }

    /**
     * Respond to player's guess, updating internal bookkeeping
     * appropriately.
     * The basic idea is to classify the list of words into several
     * classifications. Use the one with the largest number of left words
     * to become the new dictionary. Also we need to update
     * the puzzle accordingly.
     * @param ArrayList<String> array, char s.
     * @return newly updated dictionary list.
     */
    public ArrayList<String> divideConquer(ArrayList<String> array, char s){
        HashMap<String, HashSet<String>> dcStore= new HashMap<>();

        for(String str: array){
            ArrayList<Character> tempGuessList = new ArrayList<>();
            for(int i = 0; i<wordSize; ++i){
                tempGuessList.add(BLANK);
            }

            if (str.indexOf(s) != -1) {
                int c = 0;
                for (char chara: str.toCharArray()) {
                    if (chara == s) {
                        tempGuessList.set(c, s);
                    }
                    c = c + 1;
                }
            }
            HashSet<String> hs = dcStore.get(ArrayListToString(tempGuessList));
            if (hs == null){
                HashSet<String> hss = new HashSet<>();
                hss.add(str);
                dcStore.put(ArrayListToString(tempGuessList), hss);
            } else {
                hs.add(str);
                dcStore.put(ArrayListToString(tempGuessList), hs);
            }
        }

        ArrayList<String> biggestList = new ArrayList<>();


        int biggestListSize = 0;
        String biggestListkey = "";

        Set<String> keys = dcStore.keySet();
        for (String keyValue : keys) {
            ArrayList<String> bigList = new ArrayList<>(dcStore.get(keyValue));
            if (bigList.size() > biggestListSize){
                biggestListSize = bigList.size();
                biggestList = bigList;
                biggestListkey = keyValue;
            }
        }
        for(int i = 0; i<wordSize; ++i){
            if (biggestListkey.charAt(i) != BLANK){
                guessList.set(i,biggestListkey.charAt(i));
            }
        }
        
        return biggestList;

    }

    /**
     * Transfer arraylist to a string. String is immutable
     @param arraylist al
     @return a new String
     **/
    public String ArrayListToString(ArrayList<Character> al){
        StringBuilder tempsb = new StringBuilder();
        for(char ch: al){
            tempsb.append(ch);
        }
        return tempsb.toString();
    }

    public EvilHangman(){

    }

    /**
     * Default constructor
     * takes a String of the name of a ﬁle containing a list of
     * words to use as the dictionary for the game.
     * @param dictionaryFile name
     */
    public EvilHangman(String dictionaryFile) {
        HashMap<Integer, ArrayList<String>> read= super.readAndStore(dictionaryFile);
        Set<Integer> keys = read.keySet();

        for (int keyValue : keys) {
            wordSize = keyValue;
            dictionaryList = read.get(keyValue);
        }
        
        for(int i = 0; i<wordSize; ++i){
            guessList.add(BLANK);
        }
        dictionaryListOld = new ArrayList<>(dictionaryList);
    }
}
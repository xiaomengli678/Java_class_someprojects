/**
 *@version cs251 Lab002 Date: 11/01/2018
 *@author Xiaomeng Li
 **/
import org.omg.CORBA.INTERNAL;

import java.util.Collection;
import java.io.*;
import java.util.*;

/** This is FairHangman class. Fairman initializes the answer word at the
 beginning of the game. The word is the same even if you play again**/
public class FairHangman implements HangmanInterface {
    private static int remainGuess = 0;
    private static int allowedGuess = 0;
    private static int usedGuess = 0;
    private static String actualWord = "";
    private static ArrayList<Character> actualList = new ArrayList<>();
    private static ArrayList<Character> guessList = new ArrayList<>();
    private static HashSet<Character> actualSet = new HashSet();
    private static HashSet<Character> allCharGuessed = new HashSet<>();
    private static int wordSize;
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
        for(Character chara: actualWord.toCharArray()){
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
     * appropriately.
     *
     * @param letter The guessed letter.
     * @return "True" if letter was found in the word.
     */
    public boolean updateWithGuess(char letter){
        allCharGuessed.add(letter);
        usedGuess = usedGuess + 1;
        if (actualSet.contains(letter)){
            for (int i = 0; i < actualList.size(); i++) {
                if (actualList.get(i) == letter) {
                    guessList.set(i, letter);
                }
            }
            return true;

        } else {
            return false;
        }
    }
    /**
     * read in the dictionary words and store them into an arraylist.
     *
     * @param dictionaryFile, dictionaryList, wordSize.
     * @return an arraylist storing everything in the dictionary.
     */
    public HashMap<Integer, ArrayList<String>> readAndStore(String dictionaryFile){
        HashMap<Integer, ArrayList<String>> dictionaryMap = new HashMap<>();
        int largerListLenFinal = 0;
        ArrayList<String> largeListFinal= new ArrayList<>();
        try {
            File f = new File(dictionaryFile);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String line = "";

            while ((line = b.readLine()) != null) {
                int newLen = line.length();

                if (dictionaryMap.containsKey(newLen)) {
                    ArrayList<String> k = dictionaryMap.get(newLen);
                    k.add(line);
                    dictionaryMap.put(newLen,k);
                } else {
                    ArrayList<String> kk = new ArrayList<>();
                    kk.add(line);
                    dictionaryMap.put(newLen, kk);
                }

            }
        } catch (IOException x) {
            System.err.println(x);
        }

        Set<Integer> keys = dictionaryMap.keySet();
        for (int keyValue : keys) {
            ArrayList<String> bigList = new ArrayList<>(dictionaryMap.get(keyValue));
            if (bigList.size() > largeListFinal.size()){
                largeListFinal = bigList;
                largerListLenFinal = keyValue;
            }
        }

        HashMap<Integer, ArrayList<String>> returnMap = new HashMap<>();
        returnMap.put(largerListLenFinal, largeListFinal);
        return returnMap;

    }

    public FairHangman(){

    }
    /**
     * Default constructor
     * takes a String of the name of a ﬁle containing a list of
     * words to use as the dictionary for the game.
     * @param dictionaryFile name
     */
    public FairHangman(String dictionaryFile) {
        ArrayList<String> dictionaryList = new ArrayList<>();


        HashMap<Integer, ArrayList<String>> read= readAndStore(dictionaryFile);
        Set<Integer> keys = read.keySet();

        for (int keyValue : keys) {
            wordSize = keyValue;
            dictionaryList = read.get(keyValue);
        }

        Collections.shuffle(dictionaryList);
        actualWord = dictionaryList.get(0);
        for(Character chara: actualWord.toCharArray()){
            actualSet.add(chara);
            actualList.add(chara);
            guessList.add(BLANK);
        }

    }


}
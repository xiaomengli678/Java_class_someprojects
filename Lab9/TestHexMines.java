/**
 *@version cs251 Lab002 Date: 11/28/2018
 *@author Xiaomeng Li
 **/

/**
 This is the TestHexMines class. It could call HexMineManager and gives different
 radius to initialize the different games. The games have no interaction with
 each other
 **/

public class TestHexMines {
    /** include some explaining information as Professor did **/
    public static void main(String[] args) {
        System.out.println("Summary of symbols in my string representations");
        System.out.println("c : covered cell");
        System.out.println("F : flagged cell");
        System.out.println("M : uncovered mine");
        System.out.println(". : uncovered cell with no neighboring mines");
        System.out.println("<number n> : uncovered cell with n neighboring mines");

        System.out.println("Test first manager");
        System.out.println("Initializing hexagon with radius 5 and 7 mines \n");
        HexMineManager small = new HexMineManager(5);
        HexMineManager big = new HexMineManager(7);


        small.PrintGameBoard();
        System.out.println();
        System.out.println("uncover (10, 3)");
        small.Click(10, 3);
        small.BFS();
        small.PrintGameBoard();
        System.out.println();

        System.out.println("toggle flag at (6, 6)");
        small.SetFlag(6, 6);
        small.BFS();
        small.PrintGameBoard();
        System.out.println();

        System.out.println("uncover (7,7)");
        small.Click(7, 7);
        small.BFS();
        small.PrintGameBoard();
        System.out.println();

        System.out.println("Test second manager");
        System.out.println("Initializing hexagon with radius 5 and 7 mines \n");

        big.PrintGameBoard();
        System.out.println();
        System.out.println("toggle flag at (7, 7)");
        big.SetFlag(7, 7);
        big.BFS();
        big.PrintGameBoard();
        System.out.println();

        System.out.println("uncover (8,8)");
        big.Click(8, 8);
        big.BFS();
        big.PrintGameBoard();
        System.out.println();

        System.out.println("toggle flag at (3,8)");
        big.SetFlag(3, 8);
        big.BFS();
        big.PrintGameBoard();
        System.out.println();

        System.out.println("Oops! Uncovered a mine!");
        big.Click(4, 9);
        big.BFS();
        big.PrintGameBoard();
        System.out.println();

        System.out.println("Go back and test first manager some more");
        small.PrintGameBoard();
        System.out.println();

        System.out.println("uncover (5,5)");
        small.Click(5, 5);
        small.BFS();
        small.PrintGameBoard();
        System.out.println();

        System.out.println("Oops! Uncovered a mine again!");
        small.Click(7, 8);
        small.BFS();
        small.PrintGameBoard();
        System.out.println();

    }

}

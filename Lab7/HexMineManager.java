import java.util.*;
public class HexMineManager {
    private static int radius = 3;
    private static int width = radius * 2+1;
    private static HashMap<ArrayList<Integer>, Integer> gameBoardNumber = new HashMap<>();
    private static HashMap<ArrayList<Integer>, Character> gameBoardStatus= new HashMap<>();
    private static int[][] dir = {{1,0}, {-1,0}, {1,-1}, {-1,1}, {0,-1},{0,1}};
    private static ArrayList<ArrayList<Integer>> indexdirect = new ArrayList<>();

    public static void gameInitial (int width){
        for(int i= 0; i<width; ++i){
            for (int j=0; j<width; ++j){
                if(i+j>=radius && i+j<=3*radius) {

                    ArrayList<Integer> tempIndex = new ArrayList();
                    tempIndex.add(i);
                    tempIndex.add(j);
                    indexdirect.add(tempIndex);
                    gameBoardNumber.put(tempIndex, 0);
                    gameBoardStatus.put(tempIndex, 'C');
                }
            }
        }


        setupMine(gameBoardNumber);
        System.out.println(gameBoardNumber);
        for(int i=0; i<indexdirect.size(); ++i){
            int numberOfMinesAround = 0;
            ArrayList<Integer> indx = indexdirect.get(i);
            if (gameBoardNumber.get(indx) != 99){
                for (int j=0; j<6; ++j){
                    int x = indx.get(0) + dir[j][0];
                    int y = indx.get(1) + dir[j][1];
                    ArrayList<Integer> tempxy = new ArrayList<>(Arrays.asList(x,y));
                    if (gameBoardNumber.containsKey(tempxy) && gameBoardNumber.get(tempxy) == 99){
                        numberOfMinesAround = numberOfMinesAround + 1;
                    }
                }
            }
            gameBoardNumber.put(indx, numberOfMinesAround);

        }
        System.out.println(gameBoardNumber);


    }














    public static void setupMine(HashMap<ArrayList<Integer>, Integer> gameBoardNumber){
        ArrayList<Integer> tempL = new ArrayList<>(Arrays.asList(3,0));
        gameBoardNumber.put(tempL, 99);
        tempL = new ArrayList<>(Arrays.asList(5,1));
        gameBoardNumber.put(tempL, 99);
        tempL = new ArrayList<>(Arrays.asList(2,3));
        gameBoardNumber.put(tempL, 99);
        tempL = new ArrayList<>(Arrays.asList(5,3));
        gameBoardNumber.put(tempL, 99);
        tempL = new ArrayList<>(Arrays.asList(2,5));
        gameBoardNumber.put(tempL, 99);
        tempL = new ArrayList<>(Arrays.asList(3,5));
        gameBoardNumber.put(tempL, 99);
    }
    public static void printGameBoard(){
        int smallCounter = 1;
        for(int i= 0; i<width; ++i){
            if (i<width/2+1) {
                for (int j = 0; j < width; ++j) {
                    ArrayList<Integer> tempIndex = new ArrayList();
                    tempIndex.add(i);
                    tempIndex.add(j);
                    if (gameBoardStatus.containsKey(tempIndex) && i <= radius) {
                        System.out.print(gameBoardStatus.get(tempIndex) + " ");
                    } else if (gameBoardStatus.containsKey(tempIndex) && i > radius) {
                        System.out.print(gameBoardStatus.get(tempIndex) + " ");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.print("\n");
            } else {
                int tempCounter = smallCounter;
                while(tempCounter > 0){
                    System.out.print(" ");
                    tempCounter = tempCounter - 1;
                }
                smallCounter = smallCounter + 1;
                for (int j = 0; j < width; ++j) {
                    ArrayList<Integer> tempIndex = new ArrayList();
                    tempIndex.add(i);
                    tempIndex.add(j);
                    if (gameBoardStatus.containsKey(tempIndex) && i <= radius) {
                        System.out.print(gameBoardStatus.get(tempIndex) + " ");
                    } else if (gameBoardStatus.containsKey(tempIndex) && i > radius) {
                        System.out.print(gameBoardStatus.get(tempIndex) + " ");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.print("\n");
            }

        }
    }

    public HexMineManager(int R) {
        radius = R;
        gameInitial(width);
        printGameBoard();
    }

}


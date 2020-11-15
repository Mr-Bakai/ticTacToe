import java.util.*;

public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();


    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your placement");
            int playerPos = sc.nextInt();

            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)){
                System.out.println("Position taken! Enter a correct position");
                playerPos = sc.nextInt();
            }

            placePeace(gameBoard, playerPos, "player");

            String result = checkWinner();
            System.out.println(result);

            Random r = new Random();
            int cpuPos = r.nextInt(9)+1;

            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = r.nextInt(9)+1;
            }

            placePeace(gameBoard, cpuPos, "cpu");
            printGameBoard(gameBoard);

            result =  checkWinner();
            System.out.println(result);
        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }

    public static void placePeace(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(pos);
        } else if(user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(pos);
        }


        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;

            case 2:
                gameBoard[0][2] = symbol;
                break;

            case 3:
                gameBoard[0][4] = symbol;
                break;

            case 4:
                gameBoard[2][0] = symbol;
                break;

            case 5:
                gameBoard[2][2] = symbol;
                break;

            case 6:
                gameBoard[2][4] = symbol;
                break;

            case 7:
                gameBoard[4][0] = symbol;
                break;

            case 8:
                gameBoard[4][2] = symbol;
                break;

            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner(){

        List topRow = Arrays.asList(1, 2, 3);     // Arrays.asList method converts an array into collections, in this case it coverts into a List
        List midRow = Arrays.asList(4, 5, 6);     // as a parameter you can give  any array  - "a"   (String[] a = {1,2,3}))
        List botRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol  = Arrays.asList(2, 5, 8);
        List rightCol =Arrays.asList(3, 6, 9);

        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);


        List<List>  winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);

        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);

        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning){
            if(playerPositions.containsAll(l)){      // playerPosition collection checks if he contains all the elements of "l" in itself (inside of a playerPos)
                return "Congratulations you won!";
            } else if (cpuPositions.containsAll(l)){
                return "CPU wins!";
            } else if (playerPositions.size() + cpuPositions.size() == 9){
                return "CAT";
            }
        }
       return " ";
    }
}

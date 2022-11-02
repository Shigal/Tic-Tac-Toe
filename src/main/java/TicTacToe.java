import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {

        char [] [] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},};

        printGameBoard(gameBoard);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the placement out of 1-9");
//            Scanner scanner = new Scanner(System.in);

            int playerPos = scanner.nextInt();

            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position taken! Enter a new position");
                playerPos = scanner.nextInt();
            }

            String result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }
            placePiece(gameBoard, playerPos, "player");

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1; // here the bound is from zero to eight, that's why we are adding one
            System.out.println(cpuPos);

            while(cpuPositions.contains(cpuPos) || playerPositions.contains(cpuPos)) {
//                System.out.println("Position taken! Enter a new position");
                cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos,"cpu");
            printGameBoard(gameBoard);

            result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }
        }

    }

    public static String checkWinner(){
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winningConditions = new ArrayList<>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        for(List l : winningConditions) {
            if(playerPositions.containsAll(l)) {
                return "Congratulations! You Won";
            } else if(cpuPositions.containsAll(l)){
                return "CPU wins";
            }else if(playerPositions.size() + cpuPositions.size() == 9) {
                return "CAT";
            }
        }
        return "";
    }

    public static void placePiece(char[][] gameBoard, int pos, String user){
        char symbol = ' ';
        if(user.equals("player")) {
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

    public static void printGameBoard(char [] [] gameBoard){
        for(char [] row : gameBoard) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}

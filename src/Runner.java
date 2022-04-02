import java.util.Random;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        int pos;
        char choice;
        boolean isPvP;
        int[] cpuCoord;
        char[][] board =
                {
                        {' ', '|', ' ', '|', ' '},
                        {'-', '+', '-', '+', '-'},
                        {' ', '|', ' ', '|', ' '},
                        {'-', '+', '-', '+', '-'},
                        {' ', '|', ' ', '|', ' '},
                };
        TicTacToe ticTacToe = new TicTacToe(board);
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        System.out.println("Do you want to battle a real player? (y/n)");
        choice = sc.next().charAt(0);
        isPvP = choice == 'Y' || choice == 'y';

        do {
            if (isPvP) {
                if (TicTacToe.noOfTurns % 2 == 0) {
                    System.out.println("Player 1, please enter desired position (1-9): ");
                } else if (TicTacToe.noOfTurns % 2 == 1) {
                    System.out.println("Player 2, please enter desired position (1-9): ");
                }
                pos = sc.nextInt();
                if (ticTacToe.isValid(pos) && !ticTacToe.isOccupied(ticTacToe.posMapping(pos))) {
                    ticTacToe.setBoard(pos, TicTacToe.noOfTurns % 2 == 0 ? 'X' : 'O');
                    ticTacToe.displayBoard();
                    ticTacToe.evaluateBoard();
                } else {
                    System.out.println("Invalid Position!");
                }
            } else {
                System.out.println("Player, please enter desired position (1-9): ");
                pos = sc.nextInt();
                if (ticTacToe.isValid(pos) && !ticTacToe.isOccupied(ticTacToe.posMapping(pos))) {
                    ticTacToe.setBoard(pos, 'X');
                    ticTacToe.displayBoard();
                    ticTacToe.evaluateBoard();

                    do {
                        pos = 1 + random.nextInt(9);
                        cpuCoord = ticTacToe.posMapping(pos);
                    } while (ticTacToe.isOccupied(cpuCoord));

                    System.out.println("CPU setting position: " + pos);
                    ticTacToe.setBoard(pos, 'O');
                    ticTacToe.displayBoard();
                    ticTacToe.evaluateBoard();
                } else {
                    System.out.println("Invalid Position!");
                }
            }
        } while (TicTacToe.noOfTurns != 9);
    }
}

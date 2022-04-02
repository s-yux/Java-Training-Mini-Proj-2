public class TicTacToe {

    char[][] board;
    public static int noOfTurns = 0;

    public TicTacToe(char[][] board) {
        this.board = board;
        System.out.println("Board initialised!");
        displayBoard();
    }

    public void displayBoard() {
        for (char[] rows : board) {
            for (char x : rows) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    public boolean setBoard(int pos, char mark) {
        int[] coord = posMapping(pos);
        if (!isOccupied(coord)) {
            board[coord[0]][coord[1]] = mark;
            noOfTurns++;
            System.out.println("Current Turn: " + noOfTurns);
            return true;
        } else {
            System.out.println("Position occupied!");
            return false;
        }
    }

    public void evaluateBoard() {
        int ans = nonDiagonalCheck(true) + nonDiagonalCheck(false) + diagonalCheck();
        if (ans == 1) {
            System.out.println("You win!");
            System.exit(0);
        } else if (ans == 2) {
            System.out.println("CPU wins!");
            System.exit(0);
        } else if (noOfTurns == 9) {
            System.out.println("Draw!");
            System.exit(0);
        }
    }

    public int nonDiagonalCheck(boolean isRow) {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < board.length; i += 2) {
            for (int j = 0; j < board[i].length; j += 2) {
                if (board[isRow ? i : j][isRow ? j : i] == 'X') {
                    countX++;
                } else if (board[isRow ? i : j][isRow ? j : i] == 'O') {
                    countO++;
                }
            }
            if (countX == 3) {
                return 1;
            } else if (countO == 3) {
                return 2;
            }
            countX = 0;
            countO = 0;
        }
        return 0;
    }

    public int diagonalCheck() {
        if ((board[0][0] == 'X' && board[2][2] == 'X' && board[4][4] == 'X') ||
                (board[4][0] == 'X' && board[2][2] == 'X' && board[0][4] == 'X')) {
            return 1;
        } else if ((board[0][0] == 'O' && board[2][2] == 'O' && board[4][4] == 'O') ||
                (board[4][0] == 'O' && board[2][2] == 'O' && board[0][4] == 'O')) {
            return 2;
        } else {
            return 0;
        }
    }

    public boolean isValid(int pos) {
        return (pos > 0 && pos < 10);
    }

    public boolean isOccupied(int[] coord) {
        return board[coord[0]][coord[1]] != ' ';
    }

    public int[] posMapping(int pos) {
        switch (pos) {
            case 1:
                return new int[]{0, 0};
            case 2:
                return new int[]{0, 2};
            case 3:
                return new int[]{0, 4};
            case 4:
                return new int[]{2, 0};
            case 5:
                return new int[]{2, 2};
            case 6:
                return new int[]{2, 4};
            case 7:
                return new int[]{4, 0};
            case 8:
                return new int[]{4, 2};
            case 9:
                return new int[]{4, 4};
            default:
                System.out.println("Invalid position");
                return null;
        }
    }
}

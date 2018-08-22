import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Board {
    private final int BOARD_WIDTH = 9;

    private ArrayList<Cage> cages;

    public Board() {
        cages = new ArrayList<>();
//        this.initializeCages();
    }

    public void setCages(ArrayList<Cage> cages) {
        this.cages = cages;
    }

    // User input cages
    private void initializeCages() {
        Scanner reader = new Scanner(System.in);

        int num = 1;
        System.out.println("Cage " + num);
        System.out.print("Enter the operation of the new cage, or type \"quit\" to quit: ");
        String input = reader.next();
        while (!input.equals("quit")) {
            String operation = input;
            System.out.print("Enter the cage result (number in the corner of the cage): ");
            int result = reader.nextInt();
            System.out.print("Enter the total number of cells in the cage: ");
            int total = reader.nextInt();

            // Initialize the coordinates and default value of each cell in the cage
            int[][] coords = new int[total][2];
            int[] defaultValues = new int[total];
            for (int i = 0; i < total; i++) {
                System.out.print("Enter the coordinates of cell " + (i + 1) + " in the format \"x, y\": ");
                String tempCoords = reader.next() + reader.next();
                Scanner newReader = new Scanner(tempCoords).useDelimiter("\\s*,\\s*");
                coords[i][0] = newReader.nextInt();
                coords[i][1] = newReader.nextInt();
                System.out.print("Enter the default value of the cell (0 if nonexistent): ");
                defaultValues[i] = 0;
            }

            // Add cage to cages list
            Cage newCage = new Cage(operation, result, coords, defaultValues);
            cages.add(newCage);

            // Start input for new cage
            num++;
            System.out.println();
            System.out.println("Cage " + num);
            System.out.print("Enter the operation of the new cage, or type \"quit\" to quit: ");
            input = reader.next();
        }
    }

    public static boolean checkBoard(int[][] board) {
        // Iterate through each row
        for (int i = 0; i < board.length; i++) {
            // Uses digits array to keep track of digits, making sure each only appears once
            int[] digits = new int[10];
            for (int j : board[i]) {
                if (j > 0) {
                    if (digits[j] == 0)
                        digits[j] = 1;
                    else
                        return false;
                }
            }
        }

        // Iterate through each column
        for (int j = 0; j < board[0].length; j++) {
            int[] digits = new int[10];
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] > 0) {
                    if (digits[board[i][j]] == 0)
                        digits[board[i][j]] = 1;
                    else
                        return false;
                }
            }
        }

        return true;
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    public void solveBoard() {
        int[][] board = new int[BOARD_WIDTH][BOARD_WIDTH];

        ListIterator<Cage> cageListIterator = cages.listIterator();
        while (cageListIterator.hasNext()) {
            Cage currentCage = cageListIterator.next();
            int[][] coords = currentCage.getCoords();

            if (currentCage.canIterate()) {
                // Fill current cage squares on board with new permutation
                int[] newPermutation = currentCage.getNextPermutation();
                for (int i = 0; i < newPermutation.length; i++) {
                    int x = coords[i][0];
                    int y = coords[i][1];
                    board[x][y] = newPermutation[i];
                }

                if (!checkBoard(board))
                    cageListIterator.previous();    // Keep cage iterator at current cage (otherwise it is automatically incremented to next cage in list)
            }
            else {
                cageListIterator.previous();
                cageListIterator.previous();
                currentCage.resetIterator();
                // Reset all cage cells to 0
                for (int i = 0; i < coords.length; i++) {
                    int x = coords[i][0];
                    int y = coords[i][1];
                    board[x][y] = 0;
                }
            }
        }

        printBoard(board);
    }
}

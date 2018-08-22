import java.util.ArrayList;

public class Main {
    public static void regularKenKenTests() {
        String[] operations = new String[]{"div", "div", "sub", "multiply", "multiply",
                "multiply", "add", "div", "sub", "div",
                "sub", "multiply", "add", "sub", "multiply",
                "add", "add", "add", "sub", "multiply", "div", "sub"};
        int[] results = new int[]{3, 2, 2, 35, 84,
                84, 11, 3, 1, 2,
                1, 14, 10, 4, 30,
                4, 11, 9, 5, 21, 2, 5};
        int[][][] coords = new int[][][]{{{0, 0}, {1, 0}},
                {{0, 1}, {1, 1}},
                {{0, 2}, {0, 3}},
                {{0, 4}, {1, 4}},
                {{0, 5}, {0, 6}, {1, 5}, {1, 6}},
                {{2, 0}, {3, 0}, {4, 0}},
                {{2, 1}, {3, 1}},
                {{1, 2}, {2, 2}},
                {{1, 3}, {2, 3}},
                {{2, 4}, {3, 4}},
                {{2, 5}, {3, 5}},
                {{2, 6}, {3, 6}},
                {{5, 0}, {6, 0}, {6, 1}},
                {{4, 1}, {5, 1}},
                {{3, 2}, {4, 2}},
                {{3, 3}, {4, 3}, {4, 4}},
                {{4, 5}, {5, 5}},
                {{4, 6}, {5, 6}},
                {{5, 2}, {6, 2}},
                {{5, 3}, {6, 3}},
                {{5, 4}, {6, 4}},
                {{6, 5}, {6, 6}}};

        int[][] defaultValues = new int[][]{{0, 0},
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0, 0, 0},
                {0, 0, 0},
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0, 0},
                {0, 0},
                {0, 0},
                {0, 0, 0},
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0}};

        ArrayList<Cage> cages = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            Cage newCage = new Cage(operations[i], results[i], coords[i], defaultValues[i]);
            cages.add(newCage);
        }

        Board board = new Board();
        board.setCages(cages);

        long startTime = System.currentTimeMillis();
        board.solveBoard();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("New algorithm: " + elapsedTime + " milliseconds");
    }

    public static void alien() {
        String[] operations = new String[]{"spades", "diamonds", "hearts", "spades", "diamonds",
                                           "hearts", "spades", "clubs", "hearts", "hearts",
                                           "clubs", "clubs", "hearts", "diamonds", "diamonds",
                                           "hearts", "hearts", "spades", "diamonds", "hearts", "clubs"};

        int[] results = new int[]{104, 13, 20, 15, 79,
                                  28, 21, 34, 39, 34,
                                  91, 130, 36, 29, 72,
                                  18, 35, 24, 84, 9, 237};

        int[][][] coords = new int[][][]{{{3, 4}, {4, 3}, {4, 4}, {4, 5}, {5, 4}},
                                         {{2, 4}, {1, 4}},
                                         {{2, 5}, {2, 6}, {3, 5}, {3, 6}},
                                         {{4, 6}, {4, 7}},
                                         {{5, 5}, {5, 6}, {6, 5}, {6, 6}},
                                         {{6, 4}, {7, 4}},
                                         {{5, 2}, {5, 3}, {6, 2}, {6, 3}},
                                         {{4, 1}, {4, 2}},
                                         {{2, 2}, {2, 3}, {3, 2}, {3, 3}},
                                         {{1, 5}, {1, 6}, {1, 7}, {2, 7}, {3, 7}},
                                         {{5, 7}, {6, 7}, {7, 7}, {7, 6}, {7, 5}},
                                         {{5, 1}, {6, 1}, {7, 1}, {7, 2}, {7, 3}},
                                         {{1, 3}, {1, 2}, {1, 1}, {2, 1}, {3, 1}},
                                         {{0, 3}, {0, 4}, {0, 5}},
                                         {{0, 6}, {0, 7}, {0, 8}, {1, 8}, {2, 8}},
                                         {{3, 8}, {4, 8}, {5, 8}},
                                         {{6, 8}, {7, 8}, {8, 8}, {8, 7}, {8, 6}},
                                         {{8, 3}, {8, 4}, {8, 5}},
                                         {{6, 0}, {7, 0}, {8, 0}, {8, 1}, {8, 2}},
                                         {{3, 0}, {4, 0}, {5, 0}},
                                         {{0, 2}, {0, 1}, {0, 0}, {1, 0}, {2, 0}}};

        int[][] defaultValues = new int[][]{{0, 0, 0, 0, 0},
                                            {0, 0},
                                            {0, 0, 0, 0},
                                            {0, 0},
                                            {0, 0, 0, 0},
                                            {0, 0},
                                            {0, 0, 0, 0},
                                            {0, 0},
                                            {0, 0, 0, 0},
                                            {0, 0, 0, 0, 0},
                                            {0, 0, 0, 0, 0},
                                            {0, 0, 0, 0, 0},
                                            {0, 0, 0, 0, 0},
                                            {0, 0, 0},
                                            {0, 0, 0, 0, 0},
                                            {0, 0, 0},
                                            {0, 0, 0, 0, 0},
                                            {0, 0, 0},
                                            {0, 0, 0, 0, 0},
                                            {0, 0, 0},
                                            {0, 0, 0, 0, 0}};

        int[] order_default = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        // Ordered from least to greatest by number of permutations
        int[] order = new int[]{1, 5, 7, 3, 19, 13, 15, 17, 20, 4, 0, 6, 8, 10, 2, 11, 18, 14, 16, 9, 12};
        // Add hardcoded cages (above) into "cages" list
        ArrayList<Cage> cages = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            Cage newCage = new Cage(operations[order[i]], results[order[i]], coords[order[i]], defaultValues[order[i]]);
            cages.add(newCage);
        }

        Board board = new Board();
        board.setCages(cages);

        // Solve KenKen board and output solving time
        long startTime = System.currentTimeMillis();
        board.solveBoard();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("New algorithm: " + elapsedTime + " milliseconds");
    }

    public static void main(String[] args) {
        alien();

        // Output coordinates
        double xCoordinate = Math.pow(4, 2) + Math.pow(4, 2) + Math.pow(4, 2) + Math.pow(1, 2);
        xCoordinate = 23 + (xCoordinate / 1000);
        double yCoordinate = Math.pow(9, 2) + Math.pow(9, 2) + Math.pow(9, 2) + Math.pow(9, 2) + Math.pow(5, 2) + Math.pow(2, 2);
        yCoordinate = 8 + (yCoordinate / 1000);

        System.out.println("The latitude is N 37 " + xCoordinate);
        System.out.println("The longitude is W 122 " + yCoordinate);
    }
}

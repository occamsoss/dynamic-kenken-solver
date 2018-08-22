import java.util.ArrayList;
import java.util.ListIterator;

public class Cage {
    private final int BOARD_WIDTH = 9;

    private String operation;
    private int result;
    private int[][] coords;
    private int[] defaultValues;        // Default values for the cells (0 if non-existent)
    private ArrayList<int[]> permutations;
    private ListIterator<int []> permutationIterator;

    // Exclusively used to check for row/column duplicates when generating permutations
    private ArrayList<Integer>[] rowList;
    private ArrayList<Integer>[] columnList;

    public Cage(String operation, int result, int[][] coords, int[] defaultValues) {
        this.operation = operation;
        this.result = result;
        this.coords = coords;
        this.defaultValues = defaultValues;
        permutations = new ArrayList<>();
        this.initializeRowsAndColumns();
        this.generatePermutations();
    }

    private void initializeRowsAndColumns() {
        // Find number of rows and columns
        ArrayList<Integer> rowNumbers = new ArrayList<>();
        ArrayList<Integer> columnNumbers = new ArrayList<>();

        // Add unique row/column numbers into above lists
        for (int i = 0; i < coords.length; i++) {
            if (!rowNumbers.contains(coords[i][0]))
                rowNumbers.add(coords[i][0]);
            if (!columnNumbers.contains(coords[i][1]))
                columnNumbers.add(coords[i][1]);
        }

        // Store the permutation indices of each row into rowList
        rowList = new ArrayList[rowNumbers.size()];
        for (int i = 0; i < rowList.length; i++) {
            rowList[i] = new ArrayList<>();
            int row = rowNumbers.get(i);
            // Find all the cells that contain this row number and store them the appropriate rowList
            for (int j = 0; j < coords.length; j++) {
                if (coords[j][0] == row)
                    rowList[i].add(j);
            }
        }

        // Store the permutation indices of each column into columnList
        columnList = new ArrayList[columnNumbers.size()];
        for (int i = 0; i < columnList.length; i++) {
            columnList[i] = new ArrayList<>();
            int column = columnNumbers.get(i);
            // Find all the cells that contain this column number and store them the appropriate columnList
            for (int j = 0; j < coords.length; j++) {
                if (coords[j][1] == column)
                    columnList[i].add(j);
            }
        }
    }

    // Addition operation
    public boolean operation1(int[] list) {
        int sum = 0;
        for (int i : list)
            sum += i;

        if (sum == result)
            return true;
        else
            return false;
    }

    // Subtraction operation
    // Assume there are only two operators
    public boolean operation2(int[] list) {
        int difference = list[0] - list[1];
        if (Math.abs(difference) == result)
            return true;
        else
            return false;
    }

    // Multiplication operation
    public boolean operation3(int[] list) {
        int product = 1;
        for (int i : list)
            product = product * i;

        if (product == result)
            return true;
        else
            return false;
    }

    // Division operation
    // Assume there are only two operators
    public boolean operation4(int[] list) {
        float quotient;
        if (list[0] > list[1])
            quotient = (float)list[0] / list[1];
        else
            quotient = (float)list[1] / list[0];

        if (quotient == result)
            return true;
        else
            return false;
    }

    public static boolean clubs(int[] list, int target) {
        int output = 0;
        for (int i : list)
            output = output + (i * i);

        if (output == target)
            return true;
        else
            return false;
    }

    public static boolean diamonds(int[] list, int target) {
        double n = list.length;

        double term1 = 1;
        for (int i : list)
            term1 = term1 * i;
        term1 = Math.pow(term1, (1.0 / n));

        double term2 = 0;
        for (int i : list)
            term2 = term2 + i;
        term2 = term2 / n;

        double term3 = 0;
        for (int i : list)
            term3 = term3 + (1.0 / i);
        term3 = n / term3;

        double output = n * (term1 + term2 + term3);
        if (Math.floor(output) == target)
            return true;
        else
            return false;
    }

    public static boolean hearts(int[] list, int target) {
        double output = 0;
        for (int i : list)
            output = output + Math.pow((double)i, 3);
        output = Math.pow(output, 0.5);

        if (Math.floor(output) == target)
            return true;
        else
            return false;
    }

    public static boolean spades(int[] list, int target) {
        double term1 = 0;
        for (int i : list)
            term1 = term1 + i;

        double term2 = 1;
        for (int i : list)
            term2 = term2 * i;

        double output = term1 + Math.pow(term2, 0.5);
        if (Math.floor(output) == target)
            return true;
        else
            return false;
    }

    // Return true if no duplicates are found, false if duplicates are found
    public boolean noDuplicates(int[] list) {
        for (ArrayList<Integer> row : rowList) {
            // Uses digits array to keep track of digits, making sure each only appears once
            int[] digits = new int[10];
            for (int i : row) {
                if (digits[list[i]] == 0)
                    digits[list[i]] = 1;
                else if (digits[list[i]] >= 1)
                    return false;
            }
        }

        for (ArrayList<Integer> column : columnList) {
            // Uses digits array to keep track of digits, making sure each only appears once
            int[] digits = new int[10];
            for (int i : column) {
                if (digits[list[i]] == 0)
                    digits[list[i]] = 1;
                else if (digits[list[i]] >= 1)
                    return false;
            }
        }

        return true;
    }

    public boolean checkCage(int[] list) {
        // Find whether there are duplicates in each row and column
        boolean output1 = noDuplicates(list);

        // Check operation
        boolean output2 = false;
        switch (operation) {
            case "clubs":
                output2 = clubs(list, result);
                break;
            case "diamonds":
                output2 = diamonds(list, result);
                break;
            case "hearts":
                output2 = hearts(list, result);
                break;
            case "spades":
                output2 = spades(list, result);
                break;
        }

        return (output1 && output2);
    }

    // Generates next possible iteration from current permutation (stored in list)
    public int[] initialIterate(int[] list) {
        // Find indices of default values
        ArrayList<Integer> indices = new ArrayList<>();
        for (int a = 0; a < defaultValues.length; a++) {
            if (defaultValues[a] != 0)
                indices.add(a);
        }

        // Return next possible permutation, starting at the last cell of the list
        int i = list.length - 1;
        while (i >= 0) {
            if (!indices.contains(i)) {
                if (list[i] < BOARD_WIDTH) {
                    list[i]++;
                    return list;
                }
                else {
                    list[i] = 1;
                    i--;
                }
            }
            else
                i--;
        }
        return null;
    }

    public void generatePermutations() {
        // Initialize all unassigned values to 1
        int[] tempList = new int[defaultValues.length];
        for (int i = 0; i < defaultValues.length; i++) {
            if (defaultValues[i] == 0)
                tempList[i] = 1;
            else
                tempList[i] = defaultValues[i];
        }

        // Iterate through all possible values of the cage
        while (tempList != null) {
            if (checkCage(tempList)) {
                int[] copy = tempList.clone();
                permutations.add(copy);
            }
            tempList = initialIterate(tempList);
        }

        permutationIterator = permutations.listIterator();
    }

    public void printPermutations() {
        for (int[] i: permutations) {
            for (int j = 0; j < i.length; j++)
                System.out.print(i[j] + " ");
            System.out.println();
        }
    }

    public ArrayList<int[]> getPermutations() {
        return permutations;
    }

    public int[][] getCoords() {
        return coords;
    }

    // Checks whether iterator cursor is at the end of all of the permutations
    public boolean canIterate() {
        if (permutationIterator.hasNext())
            return true;
        else
            return false;
    }

    // Moves iterator cursor back to the beginning of the permutations list
    public void resetIterator() {
        permutationIterator = permutations.listIterator();
    }

    // Return next permutation based on permutation iterator object
    public int[] getNextPermutation() {
        return permutationIterator.next();
    }
}

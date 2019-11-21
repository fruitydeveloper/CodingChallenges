import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static boolean isValid(int row, int col) {
        return row >= 0 && col >= 0;
    }

    static int sideOrDefault(int[][] table, int row, int col) {
        int v = isValid(row-1, col) ? table[row-1][col] : 0;
        int h = isValid(row, col-1) ? table[row][col-1] : 0;
        return Math.max(v, h);
    }

    static int diagonalOrDefault(int[][] table, int row, int col) {
        return isValid(row-1, col-1) ? table[row-1][col-1] : 0;
    }

    /** 
    * Use longest common subsequence algorithm with dynamic programming.
    * Create a table of both strings as x and y axis. If char from s1 at x
    * equals char from s2 at y then value is top-left diagonal value + 1.
    * Otherwise value is the higher neighbour from top or left.
    * The length of the longest common subsequence is at the very last
    * (x,y) index, but the subsequence can be found by backtracking:
    * if chars at x and y are the same, move diagonal and add it to the start of the string,
    * else move to the highest neighbour.
    */
    static int commonChild(String s1, String s2) {
        int[][] table = new int[s1.length()][s2.length()];

        for(int row = 0; row < s1.length(); row++) {
            for(int col = 0; col < s2.length(); col++) {
                if(s1.charAt(row) == s2.charAt(col)) {
                    table[row][col] = diagonalOrDefault(table, row, col) + 1;
                } else {
                    table[row][col] = sideOrDefault(table, row, col);
                }
            }
        }

        return table[s1.length()-1][s2.length()-1];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

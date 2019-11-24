import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static final int[][] NEIGHBOURS = {
        {-1,-1},{-1,0},{-1,1},
        {0,-1},        {0,1},
        {1,-1}, {1,0}, {1,1}
    };

    // Find the largest connected region in grid that consits of 1's
    static int maxRegion(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        int maxSize = 0;
        for(int row=0; row<rows; row++) {
            for(int col=0; col<cols; col++) {
                int size = dfs(grid, visited, row, col, rows, cols);
                if(size > maxSize) {
                    maxSize = size;
                }
            }
        }
        return maxSize;
    }

    private static int dfs(int[][] grid, boolean[][] visited, int row, int col, int rows, int cols) {
        if(!isValid(row, col, rows, cols) || visited[row][col] == true || grid[row][col] == 0) {
            return 0;
        }
        visited[row][col] = true;
        int size = 1;
        for(int[] n : NEIGHBOURS) {
            size += dfs(grid, visited, row + n[0], col + n[1], rows, cols);
        }
        return size;
    }

    private static boolean isValid(int row, int col, int rows, int cols) {
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] gridRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }

        int res = maxRegion(grid);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

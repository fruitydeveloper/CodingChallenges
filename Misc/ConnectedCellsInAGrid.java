import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int[][] NEIGHBOURS = {
        {-1,-1}, {0,-1}, {1,-1},
        {-1,0},          {1,0},
        {-1,1},  {0,1},  {1,1}
    };

    static int findRegion(int[][] matrix, int x, int y) {
        if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] != 1) {
            return 0;
        }

        matrix[x][y] = -1;
        int regions = 0;
        
        for(int neighbour[] : NEIGHBOURS) {
            regions += findRegion(matrix, x + neighbour[0], y + neighbour[1]);
        }

        return regions + 1;
    }

    // Complete the connectedCell function below.
    static int connectedCell(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int maxRegion = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] == 1) {
                    int region = findRegion(matrix, i, j);
                    if(region > maxRegion) {
                        maxRegion = region;
                    }
                }
            }
        }

        return maxRegion;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] matrixRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int matrixItem = Integer.parseInt(matrixRowItems[j]);
                matrix[i][j] = matrixItem;
            }
        }

        int result = connectedCell(matrix);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static void findCavities(char[][] map) {
        for(int x=1; x<map.length-1; x++) {
            for(int y=1; y<map.length-1; y++) {
                if(map[x][y] > map[x-1][y] && map[x][y] > map[x+1][y]
                    && map[x][y] > map[x][y-1] && map[x][y] > map[x][y+1]) {
                    map[x][y] = 'X';
                }
            }
        }
    }

    // Complete the cavityMap function below.
    static String[] cavityMap(String[] grid) {
        char[][] map = new char[grid.length][grid.length];
        for(int i=0; i<grid.length; i++){
            map[i] = grid[i].toCharArray();
        }
        
        findCavities(map);

        String[] result = new String[grid.length];
        for(int i=0; i<grid.length; i++) {
            result[i] = String.valueOf(map[i]);
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] result = cavityMap(grid);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(result[i]);

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

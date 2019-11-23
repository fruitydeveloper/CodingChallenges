import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the stepPerms function below.
    static int stepPerms(int n, Map<Integer, Integer> map){
        // Ways to climb 1, 2 or 3 steps
        if(n == 1) return 1; if(n == 2) return 2; if(n == 3) return 4;
        // Use memoization
        if(map.containsKey(n)) return map.get(n);
        int res = 0;
        res += stepPerms(n - 1, map);
        res += stepPerms(n - 2, map);
        res += stepPerms(n - 3, map);
        map.put(n, res);
        return res;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int sItr = 0; sItr < s; sItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int res = stepPerms(n, new HashMap<>());

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {


    /**
         d a B c d
       1 1 1 0 0 0
     A 0 0 1 0 0 0
     B 0 0 0 1 1 1
     C 0 0 0 0 1 1
     */
    static String abbreviation(String a, String b) {
        int aL = a.length(), bL = b.length();
        boolean dp[][] = new boolean[aL+1][bL+1];

        dp[0][0] = true;

        boolean wasUppercase = false;
        for(int x = 1; x <= aL; x++) {
            if(wasUppercase || (a.charAt(x-1) >= 'A' && a.charAt(x-1) <= 'Z')) {
                wasUppercase = true;
                dp[x][0] = false;
            } else {
                dp[x][0] = true;
            }
        }

        for(int x = 1; x <= aL; x++) {
            for(int y = 1; y <= bL; y++) {
                int aC = a.charAt(x-1);
                int bC = b.charAt(y-1);

                if(aC == bC) {
                    dp[x][y] = dp[x-1][y-1];
                } else if(aC - 'a' == bC - 'A') {
                    dp[x][y] = dp[x-1][y-1] || dp[x-1][y];
                } else if(aC >= 'A' && aC <= 'Z') {
                    dp[x][y] = false;
                } else {
                    dp[x][y] = dp[x-1][y];
                }
            }
        }

        return dp[aL][bL] ? "YES" : "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

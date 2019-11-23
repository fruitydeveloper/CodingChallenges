import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Distribute candies to children giving an extra candy to the
    // child that scored higher if they are sitting next to each other.
    static long candies(int n, int[] arr) {
        if(n == 0) return 0;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        dp1[0] = 1;
        dp2[n-1] = 1;

        // Left to right. Previous + 1 if larger or 1
        for(int i = 1; i < n; i++) {
            if(arr[i] > arr[i-1]) {
                dp1[i] = dp1[i-1] + 1;
            } else {
                dp1[i] = 1;
            }
        }

        // Initialize with the last element from first pass.
        long sum = dp1[n-1];
        // Right to left. Previous + 1 if larger or 1
        for(int i = n - 2; i >= 0; i--) {
            if(arr[i] > arr[i+1]) {
                dp2[i] = dp2[i+1] + 1;
            } else {
                dp2[i] = 1;
            }
            // Aggregate sum with the maximum from the two parses
            sum += Math.max(dp1[i], dp2[i]);
        }

        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        long result = candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

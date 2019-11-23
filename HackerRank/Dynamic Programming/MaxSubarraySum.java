import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Find the subset of non-adjacent elements with the highest sum.
    static int maxSubsetSum(int[] arr) {
        int N = arr.length, dp[] = new int[N];

        // Compare arr[i], dp[i-1] and arr[i] + dp[i-2]
        for(int i = 0; i < N; i++) {
            dp[i] = i - 1 < 0 ? arr[i] :  Math.max(arr[i], dp[i-1]);
            dp[i] = i - 2 < 0 ? dp[i] : Math.max(dp[i], dp[i-2] + arr[i]);
        }

        return dp[N - 1];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Find largest rectangle in a bar graph of heights h.
    static long largestRectangle(int[] h) {
        int max = 0;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{h[0], 0});
        for(int i=1; i < h.length; i++) {
            if(h[i]>h[i-1]) {
                stack.push(new int[]{h[i], i});
            } else {
                int l2 = -1;
                while(!stack.isEmpty() && stack.peek()[0] > h[i]) {
                    int[] l = stack.pop();
                    l2 = l[1];
                    max = Math.max(max, l[0]*(i-l[1]));
                }
                stack.push(new int[]{h[i], l2 != -1 ? l2 : i});
            }
        }

        while(!stack.isEmpty()) {
            int[] b = stack.pop();
            max = Math.max(max, b[0] * (h.length - b[1]));
        }

        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] h = new int[n];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }

        long result = largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

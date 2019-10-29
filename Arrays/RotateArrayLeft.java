import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    // Time: O(n)
    // Space: O(1)

    static int getJuggleCount(int offset, int length) {
        if(length == 0) {
            return offset;
        }
        return getJuggleCount(length, offset % length);
    }

    static int getOffsetPosition(int current, int offset, int length) {
        int pos = current - offset;
        if(pos < 0) {
            return length + pos;
        }
        return pos;
    }

    // Rotates array a left by d places in-place
    static int[] rotLeft(int[] a, int d) {
        int length = a.length;
        int juggleCount = getJuggleCount(d, length);
        
        for(int j=0; j<juggleCount; j++) {
            int nextKey = j;
            int nextValue = a[nextKey];

            while(true) {
                nextKey = getOffsetPosition(nextKey, d, length);
                int tempValue = a[nextKey];
                a[nextKey] = nextValue;
                nextValue = tempValue;
                if(nextKey == j) {
                    break;
                }
            }
        }

        return a;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] result = rotLeft(a, d);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

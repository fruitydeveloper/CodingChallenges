import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int length = q.length;

        // Transform to contain the distance from original position
        for(int i=0; i<length; i++) {
            q[i] = q[i] - 1 - i;
            if(q[i] > 2) {
                // No element can be more than 2 places ahead of it's
                // original position
                System.out.println("Too chaotic");
                return;
            }
        }

        int swaps = 0;
        while(true){
            boolean swapped = false;
            for(int i=0; i<length; i++) {
                if(q[i] < 0) {
                    // Take an element that is behind it's original position
                    // and swap it back to it's original position, while at the
                    // same time updating the current distance for passed elements
                    swapped = true;
                    swaps -= q[i];
                    int original = i+q[i];
                    for(int j=i; j>original; j--) {
                        q[j] = q[j-1] - 1;
                    }
                    q[original] = 0;
                }
            }
            // Break if an iteration with no swaps has happened
            if(!swapped) {
                break;
            }
        }

        System.out.println(swaps);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}

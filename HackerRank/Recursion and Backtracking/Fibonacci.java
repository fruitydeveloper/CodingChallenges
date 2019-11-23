import java.util.*;

public class Solution {

    static int[] memoization; 

    public static int fibonacci(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        if(memoization[n] != 0) {
            return memoization[n];
        }
        int value = fibonacci(n-2) + fibonacci(n-1);
        memoization[n] = value;
        return value;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        memoization = new int[n+1];
        System.out.println(fibonacci(n));
    }
}


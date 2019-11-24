import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // {[()]} - Balanced
    // {[(]} - Unbalanced
    static String isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        int stackSize = 0;

        for(char c : s.toCharArray()) {
            if(c == '{' || c == '[' || c == '(') {
                stack.push(c);
                stackSize++;
            } else if(c == '}' || c == ']' || c == ')') {
                if(stackSize <= 0) return "NO";
                char result = stack.pop();
                stackSize--;
                char expected = '{';
                if(c == ']') expected = '[';
                if(c == ')') expected = '(';
                if(result != expected) return "NO";
            }
        }

        return stackSize == 0 ? "YES" : "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

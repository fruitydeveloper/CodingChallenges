/*
Given n pairs of parentheses, write a function to generate all combinations of
well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
*/
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        if(n != 0) {
            backtracking(combinations, "", n, n);
        }
        return combinations;
    }
    
    private void backtracking(List<String> combinations, String current, int open, int closed) {
        if(closed == 0) {
            combinations.add(current);
            return;
        }
        if(closed > open) {
            backtracking(combinations, current + ")", open, closed - 1);
        }
        if(open > 0) {
            backtracking(combinations, current + "(", open - 1, closed);
        }
    }
}
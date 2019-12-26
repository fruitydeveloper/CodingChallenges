/*
Given a string containing digits from 2-9 inclusive,
return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below.
Note that 1 does not map to any letters.
    Input: "23"
    Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
*/
class Solution {
    String[][] digits = {
        {"a", "b", "c"},
        {"d", "e", "f"},
        {"g", "h", "i"},
        {"j", "k", "l"},
        {"m", "n", "o"},
        {"p", "q", "r", "s"},
        {"t", "u", "v"},
        {"w", "x", "y", "z"}
    };
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        findCombinations(combinations, "", digits);
        return combinations;
    }
    
    private void findCombinations(List<String> combinations, String current, String remaining) {
        if(remaining.length() == 0) {
            if(current.length() != 0) {
                combinations.add(current);
            }
            return;
        }
        int next = remaining.charAt(0) - '2';
        for(String c : digits[next]) {
            findCombinations(combinations, current + c, remaining.substring(1));
        }
    }
}
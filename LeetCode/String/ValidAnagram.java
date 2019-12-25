class Solution {
    /*
    Given two strings s and t , write a function to determine if t is an anagram of s. Only contains lowercase letters.

    Example 1:

    Input: s = "anagram", t = "nagaram"
    Output: true
    */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] chars = new int[26];
        for(char c : s.toCharArray()) {
            chars[c - 'a']++;
        }
        for(char c : t.toCharArray()) {
            chars[c - 'a']--;
        }
        for(int i : chars) {
            if(i != 0) {
                return false;
            }
        }
        return true;
    }
}
class Solution {
    /*
    Given a string s, find the longest palindromic substring in s.
    You may assume that the maximum length of s is 1000.

    Example 1:

        Input: "babad"
        Output: "bab"
        Note: "aba" is also a valid answer.
    */
    public String longestPalindrome(String s) {
        int max = 0;
        int l = -1, r = -1;
        
        for(int i = 0; i < s.length(); i++) {
            // Check palindromes that have a single middle letter
            // and palindromes that have a double middle letter
            // e.g. "bab" and "baab"
            int len1 = findPalindrome(s, i, i);
            int len2 = findPalindrome(s, i, i + 1);
            // Found a new larger palindrome?
            if(Math.max(len1, len2) > max) {
                max = Math.max(len1, len2) - 1;
                l = i - max / 2;
                r = i + max / 2;
                // For even palindromes, the size is larger by one
                // so close left side in by 1
                if(len2 > len1) {
                    l += 1;
                }
            }
        }
        if(l == -1) {
            return "";
        }
        return s.substring(l, r + 1);
    }
    
    // Find the size of a possible palindrome by expanding around center
    private int findPalindrome(String s, int left, int right) {
        int L = left, R = right;
        while(L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L;
    }
    
    
    
//     public String longestPalindrome(String s) {
//         if (s == null || s.length() < 1) return "";
//         int start = 0, end = 0;
//         for(int i = 0; i < s.length(); i++) {
//             int len1 = expandAroundCenter(s, i, i);
//             int len2 = expandAroundCenter(s, i, i + 1);
//             int len = Math.max(len1, len2);
//             if(len > end - start) {
//                 start = i - (len - 1) / 2;
//                 end = i + len / 2;
//             }
//         }
//         return s.substring(start, end + 1);
//     }
    
//     private int expandAroundCenter(String s, int left, int right) {
//         int L = left, R = right;
//         while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
//             L--;
//             R++;
//         }
//         return R - L - 1;
//     }
}
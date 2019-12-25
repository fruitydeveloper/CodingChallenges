class Solution {
    // Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

    // Example 1:

    // Input: haystack = "hello", needle = "ll"
    // Output: 2
    public int strStr(String haystack, String needle) {
        if(needle.length() == 0) return 0;
        
        for(int i = 0; i < haystack.length(); i++) {
            if(needle.length() > haystack.length() - i) {
                return -1;
            }
            for(int j = 0; j < needle.length(); j ++) {
                if(haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
                if(j == needle.length() - 1) {
                    return i;   
                }
            } 
        }
        
        return -1;
    }
}
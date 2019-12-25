class Solution {
    /*
    Write a function to find the longest common prefix string amongst an array of strings.
    If there is no common prefix, return an empty string "".
        Input: ["flower","flow","flight"]
        Output: "fl"
    */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        int size = -1;
        boolean isValid = true;
        
        while(isValid) {
            size++;
            if(strs[0].length() <= size) {
                break;
            }
            char c = strs[0].charAt(size);
            for(String s : strs) {
                if(s.length() <= size || s.charAt(size) != c) {
                    isValid = false;
                    break;
                }
            }
        }
        
        return strs[0].substring(0, size);
    }
}
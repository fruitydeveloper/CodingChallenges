class Solution {
    /*
    Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

    Note: For the purpose of this problem, we define empty string as valid palindrome.

    Example 1:

    Input: "A man, a plan, a canal: Panama"
    Output: true
    */
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int position = 0;
        for(char c : chars) {
            if(c <= 'Z' && c >= 'A') {
                chars[position++] = Character.toLowerCase(c);
            } else if(c >= 'a' && c <= 'z') {
                chars[position++] = c;
            } else if(c >= '0' && c <= '9') {
                chars[position++] = c;
            }
        }
        int left = 0;
        int right = position - 1;
        while(left < right) {
            if(chars[left++] != chars[right--]) {
                return false;
            }
        }
        return true;
    }
}
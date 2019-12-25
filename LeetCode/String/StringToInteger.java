class Solution {
    /*
    Implement atoi which converts a string to an integer.
    The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
    The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
    If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
    If no valid conversion could be performed, a zero value is returned.
    */
    public int myAtoi(String str) {
        int result = 0;
        boolean foundInt = false;
        boolean foundMinus = false;
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == ' ' && !foundInt) {
                continue;
            }
            if(c == '+' && !foundInt) {
                foundInt = true;
                continue;
            }
            if(c == '-' && !foundInt) {
                foundMinus = true;
                foundInt = true;
                if(++i >= str.length()) {
                    break;
                }
                c = str.charAt(i);
            }
            if(c >= '0' && c <= '9') {
                foundInt = true;
                int value = c - '0';

                if(result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && value > 7)) {
                    return Integer.MAX_VALUE;
                }
                if(result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && value > 8)) {
                    return Integer.MIN_VALUE;
                }
                
                if(foundMinus) {
                    result = result * 10 - value;
                } else {
                    result = result * 10 + value;
                }
                
                continue;
            }
            break;
        }
        return result;
    }
}
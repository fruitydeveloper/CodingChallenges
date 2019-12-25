class Solution {
    /*
    The count-and-say sequence is the sequence of integers with the first five terms as following:

    1.     1
    2.     11
    3.     21
    4.     1211
    5.     111221
    1 is read off as "one 1" or 11.
    11 is read off as "two 1s" or 21.
    21 is read off as "one 2, then one 1" or 1211.
    */
    public String countAndSay(int n) {
        if(n <= 1) {
            return "1";
        }
        String s = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        char c = s.charAt(0);
        int count = 1;
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) != c) {
                sb.append(String.valueOf(count) + c);
                count = 0;
            }
            c = s.charAt(i);
            count++;
        }
        sb.append(String.valueOf(count) + c);
        
        return sb.toString();
    }
}
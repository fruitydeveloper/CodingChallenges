class Solution {
    public int[] plusOne(int[] digits) {
        boolean addDigit = false;
        for(int i = digits.length - 1; i >= 0; i--) {
            if(digits[i] == 9) {
                if(i == 0) {
                    addDigit = true;
                }
                digits[i] = 0;
                continue;
            }
            digits[i] = digits[i] + 1;
            break;
        }
        // If array was [9, 9], answer will be [1, 0, 0]
        if(addDigit) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            for(int i=0; i<digits.length; i++) {
                result[i+1] = digits[i];
            }
            return result;
        }
        return digits;
    }
}
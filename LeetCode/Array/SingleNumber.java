/*
Concept

    If we take XOR of zero and some bit, it will return that bit
    a XOR 0 = a⊕0=a
    If we take XOR of two same bits, it will return 0
    a XOR a = a⊕a=0
    a XOR b XOR a = (a XOR a) XOR b = 0 XOR b = a⊕b⊕a=(a⊕a)⊕b=0⊕b=b
    So we can XOR all bits together to find the unique number.
*/
class Solution {
    public int singleNumber(int[] nums) {
        int num = 0;
        for(int i : nums) {
            num ^= i;
        }
        return num;
    }
}
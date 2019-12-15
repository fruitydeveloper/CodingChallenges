class Solution {
    /*
    Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

    Formally the function should:

        Return true if there exists i, j, k
        such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
    */
    public boolean increasingTriplet(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both,
        // while both have been updated, return true.
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for(int num : nums) {
            if(num <= first) {
                // update first if num is smaller than both first and second
                first = num;
            } else if(num <= second) {
                // update second only if greater than first but smaller than second
                second = num;
            } else {
                // return if you find a number bigger than both
                return true;
            }
        }
        return false;
    }
}
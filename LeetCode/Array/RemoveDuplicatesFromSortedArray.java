class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int pos = 1;
        int cur = nums[0];
        for(int i=1; i < nums.length; i++) {
            if(nums[i] == cur) {
                continue;
            }
            cur = nums[i];
            nums[pos++] = cur;
        }
        return pos;
    }
}
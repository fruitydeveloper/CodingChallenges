class Solution {
    // Move all '0' to end of the array
    public void moveZeroes(int[] nums) {
        int pointer = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] != 0) {
                nums[pointer++] = nums[i];
                if(i + 1 != pointer){
                    nums[i] = 0;
                }
            }
        }
    }
}
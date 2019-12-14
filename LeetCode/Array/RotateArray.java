public class Solution {
     public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for(int i=0; i<nums.length; i++) {
            int cur = i;
            int prev = nums[i];
            do {
                int j = (cur + k) % nums.length;
                int temp = nums[j];
                nums[j] = prev;
                prev = temp;
                cur = j;
                count++;
            }while(cur != i);
            if(count == nums.length) {
                break;
            }
        }
    }
}
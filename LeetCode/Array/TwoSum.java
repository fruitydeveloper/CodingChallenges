class Solution {
    /*
    Given an array of integers, return indices of the two numbers such that they add up to a specific target.
    You may assume that each input would have exactly one solution, and you may not use the same element twice.
    */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i< nums.length; i++) {
            if(map.containsKey(nums[i])) {
                return new int[]{
                    map.get(nums[i]),
                    i
                };
            }
            map.put(target - nums[i], i);
        }
        
        return new int[0];
    }
}
/*
Given a set of distinct integers, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.

    Input: nums = [1,2,3]
    Output:
    [
        [3],
        [1],
        [2],
        [1,2,3],
        [1,3],
        [2,3],
        [1,2],
        []
    ]
*/
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> sets = new ArrayList<>();
        // Arrays.sort(nums); // When need to skip duplicates
        subsets(sets, new ArrayList<Integer>(), 0, nums);
        return sets;
    }
    
    private void subsets(List<List<Integer>> sets, List<Integer> current, int start, int[] nums) {
        sets.add(new ArrayList<>(current));
        
        for(int i = start; i < nums.length; i++) {
            // if(i > start) continue; // When need to skip duplicates
            current.add(nums[i]);
            subsets(sets, current, i + 1, nums);
            current.remove(current.size() - 1);
        }
    }
}
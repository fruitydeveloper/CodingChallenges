/*
Given a collection of distinct integers, return all possible permutations.

    Input: [1,2,3]
    Output:
    [
        [1,2,3],
        [1,3,2],
        [2,1,3],
        [2,3,1],
        [3,1,2],
        [3,2,1]
    ]
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        permuteHelper(permutations, new ArrayList<>(), new boolean[nums.length], nums);
        return permutations;
    }
    
    private void permuteHelper(List<List<Integer>> permutations, List<Integer> current, boolean[] visited, int[] nums) {
        if(current.size() == nums.length) {
            permutations.add(new ArrayList<>(current));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(visited[i]) {
               continue; 
            }
            visited[i] = true;
            current.add(nums[i]);
            permuteHelper(permutations, current, visited, nums);
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }
}
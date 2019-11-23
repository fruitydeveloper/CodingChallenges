/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Map<Integer, Integer> sums;
    
    /**
        Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
        Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
     */
    public int maxLevelSum(TreeNode root) {
        if(root == null) return 0;
        sums = new HashMap<>();
        
        countSum(root, 0);
        
        int max = Integer.MIN_VALUE;
        int maxI = 0;
        int i = 1;
        for(Integer level : sums.keySet()) {
            int cur = sums.get(level);
            if(cur > max) {
                maxI = i;
                max = cur;
            }
            i++;
        }
        
        return maxI;
    }
    
    private void countSum(TreeNode node, int level) {
        if(node == null) return;
        int curSum = sums.getOrDefault(level, 0);
        curSum += node.val;
        sums.put(level, curSum);
        
        countSum(node.left, level + 1);
        countSum(node.right, level + 1);
    }
}
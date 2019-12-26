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
    // Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
    // Uses a modified inorder traversal that returns when it finds the value
    public int kthSmallest(TreeNode root, int k) {
        Counter counter = new Counter();
        return inorder(root, counter, k);
    }
    
    private Integer inorder(TreeNode root, Counter counter, int k) {
        if(root == null) return null;
        Integer left = inorder(root.left, counter, k);
        counter.count++;
        if(left != null) {
            return left;
        }
        if(counter.count == k) {
            return root.val;
        }
        Integer right = inorder(root.right, counter, k);
        return right;
    }
}

class Counter {
    public int count;
    
    public Counter() {
        count = 0;
    }
}
/*
Given an array of non-negative integers arr, you are initially positioned at start
index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i],
check if you can reach to any index with value 0.
*/
class Solution {
    public boolean canReach(int[] arr, int start) {
        return dfs(new boolean[arr.length], arr, start);
    }
    
    private boolean dfs(boolean[] visited, int[] arr, int start) {
        if(start < 0 || start >= arr.length) {
            return false;
        }
        if(arr[start] == 0) {
            return true;
        }
        if(visited[start]) {
            return false;
        }
        visited[start] = true;
        if(dfs(visited, arr, start + arr[start])) {
            return true;
        }
        return dfs(visited, arr, start - arr[start]);
    }
}
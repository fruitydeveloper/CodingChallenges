class Solution {
    public int[] gardenNoAdj(int N, int[][] paths) {
        Map<Integer, List<Integer>> adj = new HashMap<>(N);
        
        for(int i=0; i<N; i++) adj.put(i, new LinkedList<>());
        
        for(int[] path : paths) {
            adj.get(path[0] - 1).add(path[1] - 1);
            adj.get(path[1] - 1).add(path[0] - 1);
        }
        
        int[] result = new int[N];
        for(int i = 0; i < N; i++) {
            int[] colors = {1,2,3,4};
            for(int j : adj.get(i)) {
                if(result[j] != 0) {
                    colors[result[j] - 1] = 0;
                }
            }
            for(int color : colors) {
                if(color != 0) {
                    result[i] = color;
                    break;
                }
            }
        }
        
        return result;
    }
    
}
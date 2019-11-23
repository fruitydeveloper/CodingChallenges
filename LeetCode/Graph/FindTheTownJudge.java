class Solution {
    public int findJudge(int N, int[][] trust) {
        int[] people = new int[N];
        boolean[] hasTrust = new boolean[N];
        
        // Map the amount of trust for each person
        for(int[] t : trust) {
            people[t[1]-1] += 1; // People are 1-indexed
            hasTrust[t[0]-1] = true; // People are 1-indexed
        }
        
        // Find the person who everyone, but himself trusts (N-1 == people)
        // and who trusts noone (hasTrust == false)
        for(int i = 0; i < N; i++) {
            if(hasTrust[i] == false && N - 1 == people[i]) {
                return i + 1;// People are 1-indexed
            }
        }
        return -1;
    }
}
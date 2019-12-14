class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        List<Integer> result = new ArrayList<>();
        int l = 0, r = 0;
        
        while(l < nums1.length && r < nums2.length) {
            if(nums1[l] == nums2[r]) {
                result.add(nums1[l]);
                l++;
                r++;
            } else if(nums1[l] < nums2[r]) {
                l++;
            } else {
                r++;
            }
        }
        
        int[] arr = new int[result.size()];
        int i = 0;
        for(int num : result) {
            arr[i++] = num;
        }
        
        //result.stream().mapToInt(i->i).toArray();
        
        return arr;
    }
}
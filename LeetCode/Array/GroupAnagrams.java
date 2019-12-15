class Solution {
    /* 
    Given an array of strings, group anagrams together.
    Example:

        Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
        Output:
        [
          ["ate","eat","tea"],
          ["nat","tan"],
          ["bat"]
        ]
    */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<>();
        
        for(String s : strs) {
            String key = createKey(s);
            if(anagrams.containsKey(key)) {
                anagrams.get(key).add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                anagrams.put(key, list);
            }
        }
        
        // From hashmap to list
        List<List<String>> result = new ArrayList<>();
        for(String key : anagrams.keySet()) {
            result.add(anagrams.get(key));
        }
        return result;
    }

    // Create key from char array that will be the same for all anagrams
    private String createKey(String s) {
        char[] freq = new char[26];
        for(char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        return new String(freq);
    }
}
class Solution {
    public String longestWord(String[] words) {
        Trie trie = new Trie(words);
        return trie.findLongestWord();
    }
}

class Trie {
    public Node root;
    
    public Trie(String[] words) {
        root = new Node(null, 'a');
        
        for(String word : words) {
            insert(word);
        }
    }
    
    private void insert(String word) {
        Node curr = root;
        
        for(char c : word.toCharArray()) {
            curr.children.putIfAbsent(c, new Node(curr, c));
            curr = curr.children.get(c);
        }
        
        curr.isWord = true;
    }
    
    public String findLongestWord() {
        String longestWord = "";
        
        Node maxDepthNode = findMaxDepthNode(root);
        return reconstruct(maxDepthNode);
    }
    
    private String reconstruct(Node node) {
        StringBuilder sb = new StringBuilder();
        Node curr = node;
        
        while(curr.parent != null) {
            sb.insert(0, curr.parentChar);
            curr = curr.parent;
        }
        
        return sb.toString();
    }
    
    /**
     * Find the word node with the maximum depth so that
     * every parent node was also a word. If found multiple,
     * select one with the lowest lexicographical order.
     */
    private Node findMaxDepthNode(Node curr) {
        Node max = curr;
        char maxKey = 'a';
        
        for(char key : curr.children.keySet()) {
            Node child = curr.children.get(key);
            if(!child.isWord) {
                continue;
            }
            Node n = findMaxDepthNode(child);
            if(n.depth > max.depth) {
                max = n;
                maxKey = key;
            }
            if(n.depth == max.depth) {
                if(maxKey > key) {
                    max = n;
                    maxKey = key;
                }
            }
        }
        
        return max;
    }
}

class Node {
    public Map<Character, Node> children;
    public Node parent;
    public char parentChar;
    public boolean isWord;
    public int depth;
    
    public Node(Node parent, char parentChar) {
        this.parent = parent;
        this.parentChar = parentChar;
        isWord = false;
        children = new HashMap<>();
        depth = parent == null ? 0 : parent.depth + 1;
    }
}
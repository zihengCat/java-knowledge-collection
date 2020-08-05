package io.ziheng.tree.leetcode;
/**
 * 211. Add and Search Word - Data structure design
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 */
public class AddAndSearchWord {
    public static void main(String[] args) {
        /**
         * Your WordDictionary object will be instantiated and called as such:
         */
        WordDictionary obj = new WordDictionary();
        obj.addWord("abc");
        obj.addWord("cba");
        obj.addWord("ccc");
        System.out.println(obj.search("abc"));
        System.out.println(obj.search("ab"));
        System.out.println(obj.search("bac"));
        System.out.println(obj.search("c."));
        System.out.println(obj.search("c.."));
    }
}
class WordDictionary {
    private static int DEFAULT_CAPACITY = 26;
    private class TrieNode {
        public char val;
        public boolean isWord;
        public TrieNode[] children;
        public TrieNode(char c) {
            this.val = c;
            this.isWord = false;
            this.children = new TrieNode[DEFAULT_CAPACITY];
        }
    }
    private TrieNode root;
    public WordDictionary() {
        // dummy root node
        this.root = new TrieNode('0');
    }
    public void addWord(String word) {
        addWordRecursively(this.root, word, 0);
    }
    private void addWordRecursively(TrieNode root, String word, int index) {
        // End with a word.
        if (index >= word.length()) {
            root.isWord = true;
            return;
        }
        // Character exists -> go next
        // Character not exists -> create a new TrieNode and go next
        int nodeIndex = getIndex(word.charAt(index));
        if (root.children[nodeIndex] == null) {
            TrieNode newNode = new TrieNode(word.charAt(index));
            root.children[nodeIndex] = newNode;
            addWordRecursively(newNode, word, index + 1);
        } else {
            addWordRecursively(root.children[nodeIndex], word, index + 1);
        }
    }
    // Character to index
    private int getIndex(char c) {
        return c - 'a';
    }
    public boolean search(String word) {
        return searchWordRecursively(this.root, word, 0);
    }
    private boolean searchWordRecursively(TrieNode root, String word, int index) {
        // Word ends -> check whether root is a word
        if (index >= word.length()) {
            return root.isWord;
        }
        char c = word.charAt(index);
        // Process regular expression character -> `.`
        // traversal all TrieNode in current layer
        if (c == '.') {
            for (int i = 0; i < DEFAULT_CAPACITY; i++) {
                TrieNode theNode = root.children[i];
                if (theNode != null
                    && searchWordRecursively(theNode, word, index + 1) == true) {
                    return true;
                }
            }
            return false;
        }
        // Normal character case
        TrieNode trieNode = root.children[getIndex(c)];
        if (trieNode == null) {
            return false;
        }
        return searchWordRecursively(trieNode, word, index + 1);
    }
}
/* EOF */
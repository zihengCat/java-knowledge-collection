package io.ziheng.tree.leetcode;
/**
 * LeetCode 208. Implement Trie (Prefix Tree)
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 */
public class ImplementTriePrefixTree {
    private class TrieNode {
        private static final int DEFAULT_CAPACITY = 26;
        public char value;
        public boolean isWord;
        public TrieNode[] children;
        public TrieNode() {
            // ...
        }
        public TrieNode(char value) {
            this.value = value;
            this.isWord = false;
            this.children = new TrieNode[DEFAULT_CAPACITY];
        }
    }
    /**
     * 字典树根节点
     */
    private TrieNode root;
    /**
     * 构造函数
     */
    public ImplementTriePrefixTree() {
        root = new TrieNode(' ');
    }
    /**
     * 插入单词
     *
     * @param word
     * @return void
     */
    public void insert(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (currentNode.children[c - 'a'] == null) {
                currentNode.children[c - 'a'] = new TrieNode(c);
            }
            currentNode = currentNode.children[c - 'a'];
        }
        currentNode.isWord = true;
    }
    /**
     * 搜索单词是否存在
     *
     * @param word
     * @return boolean
     */
    public boolean search(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (currentNode.children[c - 'a'] == null) {
                return false;
            }
            currentNode = currentNode.children[c - 'a'];
        }
        return currentNode.isWord;
    }
    /**
     * 字典树中是否存在 prefix 前缀的单词
     *
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        TrieNode currentNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (currentNode.children[c - 'a'] == null) {
                return false;
            }
            currentNode = currentNode.children[c - 'a'];
        }
        return true;
    }
}
/* EOF */
package io.ziheng.tree;
public class TrieTree {
    private class TrieNode {
        private static final int DEFAULT_CAPACITY = 26;
        public char val;
        public boolean isWord;
        public TrieNode[] children;
        public TrieNode() {
            // ...
        }
        public TrieNode(char c) {
            // ...
        }
    }
}
/* EOF */
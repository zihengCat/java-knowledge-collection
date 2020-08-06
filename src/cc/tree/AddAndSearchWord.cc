#include <string>
#include <stdio.h>

using namespace std;

/**
 * 211. Add and Search Word - Data structure design
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 */

class TireNode {
public:
    static const int DEFAULT_CAPACITY = 26;
    char val;
    bool isWord;
    TireNode** children;
    TireNode() {
        TireNode('0');
    }
    TireNode(char c) {
        this -> val = c;
        this -> isWord = false;
        this -> children = new TireNode*[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            (this -> children)[i] = nullptr;
        }
    }
};

class WordDictionary {
private:
    TireNode root;
    void addWordRecursively(TireNode *root, string &word, int index) {
        if (index >= word.length()) {
            root -> isWord = true;
            return;
        }
        int nodeIndex = this -> getCharIndex(word.at(index));
        if ((root -> children)[nodeIndex] == nullptr) {
            TireNode* newNode = new TireNode(word.at(index));
            (root -> children)[nodeIndex] = newNode;
            addWordRecursively(newNode, word, index + 1);
        }
        addWordRecursively((root -> children)[nodeIndex], word, index + 1);
    }
    bool searchWordRecursively(TireNode *root, string &word, int index) {
        if (index >= word.length()) {
            return root -> isWord;
        }
        char c = word.at(index);
        if (c == '.') {
            for (int i = 0; i < TireNode::DEFAULT_CAPACITY; i++) {
                TireNode* theNode = (root -> children)[i];
                if (theNode != nullptr
                    && searchWordRecursively(theNode, word, index + 1) == true) {
                    return true;
                }
            }
            return false;
        }
        int nodeIndex = this -> getCharIndex(c);
        if ((root -> children)[nodeIndex] == nullptr) {
            return false;
        }
        return searchWordRecursively((root -> children)[nodeIndex], word, index + 1);
    }
    int getCharIndex(char c) {
        return c - 'a';
    }
public:
    WordDictionary() {
        this -> root = TireNode('0');
    }
    void addWord(string word) {
        addWordRecursively(&root, word, 0);
    }
    bool search(string word) {
        return searchWordRecursively(&root, word, 0);
    }
};

int main(int argc, char const *argv[]) {
   /**
    * Your WordDictionary object will be instantiated and called as such:
    */
    WordDictionary* obj = new WordDictionary();
    string word = "abc";
    obj -> addWord(word);
    bool r = obj -> search("ab.");
    printf("%d\n", r);
    return 0;
}
/* EOF */
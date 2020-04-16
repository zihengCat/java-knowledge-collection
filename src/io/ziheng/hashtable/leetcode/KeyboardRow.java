package io.ziheng.hashtable.leetcode;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 500. Keyboard Row
 * https://leetcode.com/problems/keyboard-row/
 */
public class KeyboardRow {

    public String[] findWords(String[] words) {
        if (words == null || words.length == 0) {
            return new String[0];
        }
        List<String> resultList = new LinkedList<>();
        Set<Character>[] rowSets = initializeRowSets();
        for (String word : words) {
            String loweredWord = word.toLowerCase();
            Set<Character> rowSet = detectRowSet(rowSets, loweredWord.charAt(0));
            if (isVaild(rowSet, loweredWord)) {
                resultList.add(word);
            }
        }
        return resultList.toArray(new String[resultList.size()]);
    }

    private boolean isVaild(Set<Character> rowSet, String word) {
        for (char c : word.toCharArray()) {
            if (!rowSet.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private Set<Character> detectRowSet(Set<Character>[] rowSets, char c) {
        for (Set<Character> rowSet : rowSets) {
            if (rowSet.contains(c)) {
                return rowSet;
            }
        }
        return new HashSet<>();
    }

    @SuppressWarnings("unchecked")
    private Set<Character>[] initializeRowSets() {
        // String[] strs = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM", };
        Character[][] rowArray = {
            {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', },
            {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', },
            {'z', 'x', 'c', 'v', 'b', 'n', 'm', },
        };
        Set<Character>[] rowSets = new Set[3];
        for (int i = 0; i < rowSets.length; i++) {
            rowSets[i] = new HashSet<>();
            for (Character c : rowArray[i]) {
                rowSets[i].add(c);
            }
        }
        return rowSets;
    }
}
/* EOF */
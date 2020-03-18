package io.ziheng.hashtable.leetcode;

import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 1160. Find Words That Can Be Formed by Characters
 * https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
 */
public class FindWordsThatCanBeFormedByCharacters {
    public static void main(String[] args) {
        FindWordsThatCanBeFormedByCharacters obj = new FindWordsThatCanBeFormedByCharacters();
        String[] words = {"hello","world","leetcode", };
        String chars = "welldonehoneyr";
        System.out.println(obj.countCharacters(words, chars));
    }
    public int countCharacters(String[] words, String chars) {
        if (words == null || chars == null) {
            return 0;
        }
        int length = 0;
        Map<Character, Integer> charsMap = buildCharacterMap(chars);
        for (String word : words) {
            Map<Character, Integer> wordMap = buildCharacterMap(word);
            if (contains(wordMap, charsMap)) {
                length += word.length();
            }
        }
        return length;
    }
    private Map<Character, Integer> buildCharacterMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, 1 + map.get(c));
            }
        }
        return map;
    }
    private boolean contains(
        Map<Character, Integer> wordMap,
        Map<Character, Integer> charsMap) {
        for (Map.Entry<Character, Integer> entry : wordMap.entrySet()) {
            if (!charsMap.containsKey(entry.getKey())
                || charsMap.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
/* EOF */
package io.ziheng.recursion.backtracking.leetcode;

import java.util.Set;
import java.util.HashSet;

/**
 * LeetCode 1079. Letter Tile Possibilities
 * https://leetcode.com/problems/letter-tile-possibilities/
 */
public class LetterTilePossibilities {
    public static void main(String[] args) {
        LetterTilePossibilities obj = new LetterTilePossibilities();
        String tiles = "AAABBC";
        System.out.println(
            obj.numTilePossibilities(tiles)
        );
    }
    public int numTilePossibilities(String tiles) {
        Set<String> aSet = new HashSet<>();
        boolean[] visited = new boolean[tiles.length()];
        numTilePossibilities0(
            0,
            tiles.length(),
            tiles.toCharArray(),
            visited,
            "",
            aSet
        );
        // System.out.println(aSet);
        return aSet.size();
    }
    private void numTilePossibilities0(
        int currentIndex,
        int maximumLength,
        char[] originalArray,
        boolean[] visited,
        String currentString,
        Set<String> resultSet) {
        if (currentIndex >= maximumLength) {
            return;
        }
        for (int i = 0; i < maximumLength; i++) {
            if (!visited[i]) {
                visited[i] = true;
                resultSet.add(
                    currentString + originalArray[i]
                );
                numTilePossibilities0(
                    currentIndex + 1,
                    maximumLength,
                    originalArray,
                    visited,
                    currentString + originalArray[i],
                    resultSet
                );
                visited[i] = false;
            }
        }
    }
}
/* EOF */
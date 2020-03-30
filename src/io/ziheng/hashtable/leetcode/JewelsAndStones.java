package io.ziheng.hashtable.leetcode;

import java.util.Set;
import java.util.HashSet;

/**
 * LeetCode 771. Jewels and Stones
 * https://leetcode.com/problems/jewels-and-stones/
 */
public class JewelsAndStones {
    public int numJewelsInStones(String J, String S) {
        if (J == null || S == null) {
            return 0;
        }
        Set<Character> aSet = new HashSet<>();
        for (char c : J.toCharArray()) {
            aSet.add(c);
        }
        int cnt = 0;
        for (char c : S.toCharArray()) {
            if (aSet.contains(c)) {
                cnt++;
            }
        }
        return cnt;
    }
}
/* EOF */
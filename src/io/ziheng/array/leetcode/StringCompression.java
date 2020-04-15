package io.ziheng.array.leetcode;

import java.util.Map;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 443. String Compression
 * https://leetcode.com/problems/string-compression/
 */
public class StringCompression {
    public static void main(String[] args) {
        StringCompression obj = new StringCompression();
        char[] arr = new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c', };
        System.out.println(
            obj.compress(arr)
        );
        System.out.println(
            Arrays.toString(arr)
        );
    }
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        Map<Character, Integer> aMap = new LinkedHashMap<>();
        for (char c : chars) {
            if (aMap.containsKey(c)) {
                aMap.put(c, 1 + aMap.get(c));
            } else {
                aMap.put(c, 1);
            }
        }
        int index = 0;
        for (Map.Entry<Character, Integer> entry : aMap.entrySet()) {
            char c = entry.getKey();
            int freq = entry.getValue();
            chars[index] = c;
            index++;
            if (freq == 1) {
                // ...
            } else if (freq > 1 && freq < 10) {
                chars[index] = (char)(freq + '0');
                index++;
            } else if (freq >= 10) {
                List<Integer> aList = new LinkedList<>();
                while (freq > 0) {
                    aList.add(freq % 10);
                    freq /= 10;
                }
                Collections.reverse(aList);
                for (int n : aList) {
                    chars[index] = (char)(n + '0');
                    index++;
                }
            }
        }
        return index;
    }
}
/* EOF */
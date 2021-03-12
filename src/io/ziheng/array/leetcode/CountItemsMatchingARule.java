package io.ziheng.array.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 1773. Count Items Matching a Rule
 * https://leetcode.com/problems/count-items-matching-a-rule/
 */
public class CountItemsMatchingARule {
    public static void main(String[] args) {
        CountItemsMatchingARule obj = new CountItemsMatchingARule();
        List<List<String>> items = new LinkedList<List<String>>();
        // [["phone","blue","pixel"],["computer","silver","phone"],["phone","gold","iphone"]], ruleKey = "type", ruleValue = "phone"
        List<String> item1 = new LinkedList<String>();
        item1.addAll(Arrays.asList("phone","blue","pixel"));
        items.add(item1);
        List<String> item2 = new LinkedList<String>();
        item2.addAll(Arrays.asList("computer","silver","phone"));
        items.add(item2);
        List<String> item3 = new LinkedList<String>();
        item3.addAll(Arrays.asList("phone","gold","iphone"));
        items.add(item3);
        String ruleKey = "type";
        String ruleValue = "phone";
        System.out.println(obj.countMatches(items, ruleKey, ruleValue));
    }
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        if (items == null || items.size() == 0) {
            return 0;
        }
        int cnt = 0;
        int idx = -1;
        switch (ruleKey) {
            case "type":
                idx = 0;
                break;
            case "color":
                idx = 1;
                break;
            case "name":
                idx = 2;
                break;
            default:
                break;
        }
        // items[i] = [type{i}, color{i}, name{i}]
        for (List<String> item : items) {
            if (ruleValue.equals(item.get(idx))) {
                cnt++;
            }
        }
        return cnt;
    }
}

/* EOF */

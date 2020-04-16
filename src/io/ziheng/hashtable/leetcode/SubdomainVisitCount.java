package io.ziheng.hashtable.leetcode;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 811. Subdomain Visit Count
 * https://leetcode.com/problems/subdomain-visit-count/
 */
public class SubdomainVisitCount {
    public List<String> subdomainVisits(String[] cpdomains) {
        if (cpdomains == null || cpdomains.length == 0) {
            return new LinkedList<>();
        }
        Map<String, Integer> aMap = new HashMap<>();
        for (String cpdomain : cpdomains) {
            int index = cpdomain.indexOf(' ');
            int visits = Integer.valueOf(
                cpdomain.substring(0, index)
            );
            String domain = cpdomain.substring(index + 1, cpdomain.length());
            for (int i = 0; i < domain.length(); i++) {
                if (domain.charAt(i) == '.') {
                    String subDomain = domain.substring(i + 1, domain.length());
                    aMap.put(
                        subDomain,
                        visits + aMap.getOrDefault(subDomain, 0)
                    );
                }
            }
            aMap.put(domain, visits + aMap.getOrDefault(domain, 0));
        }
        List<String> resultList = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : aMap.entrySet()) {
            resultList.add(entry.getValue() + " " + entry.getKey());
        }
        return resultList;
    }
}
/* EOF */
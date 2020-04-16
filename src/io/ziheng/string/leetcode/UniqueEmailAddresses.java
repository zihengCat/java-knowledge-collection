package io.ziheng.string.leetcode;

import java.util.Set;
import java.util.HashSet;

/**
 * LeetCode 929. Unique Email Addresses
 * https://leetcode.com/problems/unique-email-addresses/
 */
public class UniqueEmailAddresses {
    public static void main(String[] args) {
        UniqueEmailAddresses obj = new UniqueEmailAddresses();
        String[] emails = {
            "test.email+alex@leetcode.com",
            "test.e.mail+bob.cathy@leetcode.com",
            "testemail+david@lee.tcode.com",
        };
        System.out.println(
            obj.numUniqueEmails(emails)
        );
    }
    public int numUniqueEmails(String[] emails) {
        if (emails == null || emails.length == 0) {
            return 0;
        }
        Set<String> resultSet = new HashSet<>();
        for (String email : emails) {
            resultSet.add(trimEmail(email));
        }
        return resultSet.size();
    }
    private String trimEmail(String s) {
        String[] arr = s.split("@");
        String name = arr[0];
        String domain = arr[1];
        // System.out.println(
        //     "name: " + name
        //     + " domain: " + domain
        // );
        // System.out.println("name.indexOf(\".\"): " + name.indexOf("."));
        if (name.indexOf(".") != -1) {
            name = name.replace(".", "");
        }
        if (name.indexOf("+") != -1) {
            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) == '+') {
                    name = name.substring(0, i);
                    break;
                }
            }
        }
        return name + "@" + domain;
    }
}
/* EOF */
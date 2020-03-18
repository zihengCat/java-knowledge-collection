package io.ziheng.string.leetcode;
/**
 * LeetCode 1108. Defanging an IP Address
 * https://leetcode.com/problems/defanging-an-ip-address/
 */
public class DefangingAnIPAddress {
    public String defangIPaddr(String address) {
        if (address == null || address.length() == 0) {
            return address;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            char c = address.charAt(i);
            if (c == '.') {
                sb.append("[.]");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
/* EOF */
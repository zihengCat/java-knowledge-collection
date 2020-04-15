package io.ziheng.string.leetcode;
/**
 * LeetCode 1410. HTML Entity Parser
 * https://leetcode.com/problems/html-entity-parser/
 */
public class HTMLEntityParser {
    public String entityParser(String text) {
        String str = text;
        str = str.replaceAll("&quot;", "\\\"");
        str = str.replaceAll("&apos;", "'");
        str = str.replaceAll("&amp;", "&");
        str = str.replaceAll("&gt;", ">");
        str = str.replaceAll("&lt;", "<");
        str = str.replaceAll("&frasl;", "/");
        return str;
    }
}
/* EOF */
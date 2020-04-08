package io.ziheng.codinginterviews;
/**
 * 剑指 Offer 面试题 04：替换空格
 *
 * 题目描述：
 * 请实现一个函数，
 * 将一个字符串中的每个空格替换成"%20"。
 * 例如：输入字符串为"We Are Happy"，则经过替换之后的字符串为"We%20Are%20Happy"。
 *
 * 知识点：["字符串"]
 */
public class ReplaceSpace {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        ReplaceSpace obj = new ReplaceSpace();
        String str = "We Are Happy";
        System.out.println(
            obj.replaceSpace(str)
        );
    }
    /**
     * 剑指 Offer 面试题 04：替换空格
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param str
     * @return String
     */
    public String replaceSpace(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
/* EOF */
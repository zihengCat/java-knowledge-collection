package io.ziheng.string.leetcode;

/**
 * LeetCode 1694. Reformat Phone Number
 * https://leetcode.com/problems/reformat-phone-number/
 */
public class ReformatPhoneNumber {
    public static void main(String[] args) {
        ReformatPhoneNumber obj = new ReformatPhoneNumber();
        String number = "1-23-45 6";
        System.out.println(
            number + " -> " +
            obj.reformatNumber(number)
        );
        number = "123 4-567";
        System.out.println(
            number + " -> " +
            obj.reformatNumber(number)
        );
        number = "123 4-5678";
        System.out.println(
            number + " -> " +
            obj.reformatNumber(number)
        );
        number = "12 - ";
        System.out.println(
            number + " -> " +
            obj.reformatNumber(number)
        );
        number = "--17-5 229 35-39475 ";
        System.out.println(
            number + " -> " +
            obj.reformatNumber(number)
        );
    }
    public String reformatNumber(String number) {
        if (number == null || number.equals("")) {
            return number;
        }
        String cleanNumber = doClean(number);
        char[] sArray = cleanNumber.toCharArray();
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (idx = 0; sArray.length - idx > 4; idx += 3) {
            sb.append(sArray[idx + 0])
              .append(sArray[idx + 1])
              .append(sArray[idx + 2])
              .append('-');
        }
        switch (sArray.length - idx) {
        case 4:
            sb.append(sArray[idx + 0])
              .append(sArray[idx + 1])
              .append('-')
              .append(sArray[idx + 2])
              .append(sArray[idx + 3]);
            break;
        default:
            for (/* ... */; idx < sArray.length; idx++) {
                sb.append(sArray[idx]);
            }
            break;
        }
        return sb.toString();
    }
    private String doClean(String s) {
        StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == ' ' || c == '-') {
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}

/* EOF */

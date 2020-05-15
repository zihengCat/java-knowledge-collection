package io.ziheng.codinginterviews;

/**
 * 剑指 Offer 面试题 58-1：翻转字符串单词顺序
 *
 * 题目描述：
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符顺序保持不变。例如：
 * 输入："I am a student."
 * 输出："student. a am I"
 *
 * 知识点：["字符串"]
 */
public class ReverseWordsInSentence {
    public static void main(String[] args) {
        ReverseWordsInSentence obj = new ReverseWordsInSentence();
        String s = "I am a student.";
        System.out.println(
            obj.reverseWordsInSentence(s)
        );
    }
    public String reverseWordsInSentence(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        // return reverseWordsInSentenceWithStdLib(s);
        return reverseWordsInSentenceWithoutStdLib(s);
    }
    public String reverseWordsInSentenceWithoutStdLib(String s) {
        char[] charArr = s.toCharArray();
        reverseCharArray(charArr, 0, charArr.length - 1);
        for (int i = 0; i < charArr.length; /* ... */) {
            while (i < charArr.length
                && charArr[i] == ' ') {
                i++;
            }
            int j = i;
            while (j < charArr.length
                && charArr[j] != ' ') {
                j++;
            }
            swapChar(charArr, i, j - 1);
            i = j;
        }
        return String.valueOf(charArr);
    }
    private static void reverseCharArray(char[] arr, int left, int right) {
        while (left < right) {
            swapChar(arr, left, right);
            left++;
            right--;
        }
    }
    private static void swapChar(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public String reverseWordsInSentenceWithStdLib(String s) {
        String[] arrWords = s.split(" ");
        reverseTypeArray(arrWords, 0, arrWords.length - 1);
        return String.join(" ", arrWords);
    }
    private static <T> void reverseTypeArray(T[] arr, int left, int right) {
        while (left < right) {
            swapType(arr, left, right);
            left++;
            right--;
        }
    }
    private static <T> void swapType(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
/* EOF */
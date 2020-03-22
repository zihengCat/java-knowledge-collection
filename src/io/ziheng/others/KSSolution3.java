
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KSSolution3 {
    public static void main(String[] args) {
        test();
        // 读取输入
        // Scanner scanner = new Scanner(System.in);
        // String[] sArr = scanner.nextLine().split(",");
        // List<Integer> resultList = processNumbersArray(sArr);
        // printResult(resultList);
    }
    // 测试用例
    public static void test() {
        String[] numbers = new String[] {
            "15112347234",
            "15176313245",
            "15176313346",
            "15176313325",
            "15166667234",
            "15188847234",
            "15188812345",
        };
        KSSolution3.processNumbersArray(numbers);
    }
    public static void printResult(List<Integer> list) {
        if (list.size() == 0) {
            System.out.println(-1);
            return;
        }
        for (int i : list) {
            System.out.printf("%d ", i);
        }
    }
    public static List<Integer> processNumbersArray(String[] arr) {
        List<Integer> resultList = new LinkedList<>();
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        };
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(
            comparator
        );
        for (String s : arr) {
            System.out.println(
                s + " -> " + calcNumberValue(s)
            );
        }
        //while (!priorityQueue.isEmpty()) {
        //    resultList.add(priorityQueue.poll());
        //}
        return resultList;
    }
    private static int calcNumberValue(String s) {
        int val = 0;
        int szVal = 0;
        int szContinue = -1;
        int bzVal = 1;
        for (int i = 4; i < 11; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                bzVal++;
            }
            else if (Math.abs(charToInt(s.charAt(i)) - charToInt(s.charAt(i - 1))) == 1) {
                int v = s.charAt(i) - s.charAt(i - 1);
                if (Math.abs(v) == 1 && szContinue == -10) {
                    szContinue = v;
                }
                if ((v == 1 && szContinue == 1)
                    || (v == -1 && szContinue == -1)) {
                    szVal++;
                } else {
                    szVal = 0;
                    szContinue = -10;
                }
            }
        }
        // szVal = szVal >= 3 ? szVal : 0;
        // bzVal = bzVal >= 3 ? bzVal : 0;

        System.out.println("sz: " + szVal + " bz: " + bzVal);
        if (szVal == bzVal) {
            return Math.max(
                szVal * 1,
                bzVal * 1
            );
        } else {
            return Math.max(szVal, bzVal) == szVal
            ? szVal * 1
            : bzVal * 1;
        }
    }
    private static int charToInt(char c) {
        return c - '0';
    }
}
/* EOF */
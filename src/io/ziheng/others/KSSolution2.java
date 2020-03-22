
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class KSSolution2 {
    public static void main(String[] args) {
        // test();
        // 读取输入
        Scanner scanner = new Scanner(System.in);
        String[] sArr = scanner.nextLine().split(" ");
        int[] nums = new int[sArr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.valueOf(sArr[i]);
        }
        List<Integer> resultList = findNums(nums);
        printResult(resultList);
    }
    // 测试用例
    public static void test() {
        // KSSolution2 obj = new KSSolution2();
        int[] nums = new int[] {
            1, 22, 22, 33, 22, 12, 45, 44, 5, 
        };
        System.out.println(
            KSSolution2.findNums(nums)
        );
        nums = new int[] {
            1, 22, 54, 123, 
        };
        System.out.println(
            KSSolution2.findNums(nums)
        );
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
    public static List<Integer> findNums(int[] nums) {
        List<Integer> resultList = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (isTarget(i, nums)) {
                resultList.add(i);
            }
        }
        return resultList;
    }
    private static boolean isTarget(int index, int[] nums) {
        int targetNum = nums[index];
        int cnt = 0;
        for (int i = 0; i < index; i++) {
            if (nums[i] > targetNum) {
                cnt++;
            }
        }
        return cnt == 1;
    }
}
/* EOF */
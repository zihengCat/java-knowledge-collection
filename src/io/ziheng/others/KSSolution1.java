
import java.util.Arrays;
import java.util.List;

public class KSSolution1 {
    // 测试用例
    public static void main(String[] args) {
        KSSolution1 obj = new KSSolution1();
        int[] height = new int[] {
            175, 173, 174, 163, 182, 177,
        };
        System.out.println(
            Arrays.toString(
                obj.DistanceToHigher(height)
            )
        );
        height = new int[] {
            175, 179, 174, 163, 176, 177,
        };
        System.out.println(
            Arrays.toString(
                obj.DistanceToHigher(height)
            )
        );
    }
    /**
     * 获取队中从前到后每个人与前方身高高于自己的人的最短距离
     * @param height int整型一维数组 队中从前到后每个人与前方身高高于自己的人的最短距离
     * @return int整型一维数组
     */
    public int[] DistanceToHigher(int[] height) {
        if (height == null || height.length == 0) {
            return new int[0];
        }
        int[] resultArray = new int[height.length];
        for (int i = 0; i < height.length; i++) {
            resultArray[i] = findMinimumDistance(
                i, height
            );
        }
        return resultArray;
    }
    private int findMinimumDistance(int index, int[] height) {
        int distance = 0;
        int pHeight = height[index];
        for (int i = index - 1; i >= 0; i--) {
            distance++;
            if (height[i] > pHeight) {
                return distance;
            }
        }
        return 0;
    }
}
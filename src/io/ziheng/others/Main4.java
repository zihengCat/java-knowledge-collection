
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        // 数据读入
        Scanner scanner = new Scanner(System.in);
        // N 条记录
        String firstLine = scanner.nextLine();
        int n = Integer.valueOf(
            firstLine.split(" ")[0]
        );
        int m = Integer.valueOf(
            firstLine.split(" ")[1]
        );
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
        }
        int q = Integer.valueOf(
            scanner.nextLine()
        );
        for (int i = 0; i < q; i++) {
            System.out.println("NO");
        }
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        // 数据读入
        Scanner scanner = new Scanner(System.in);
        // N 条记录
        int n = Integer.valueOf(scanner.nextLine());
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // 用户类型 -> 模型文件
            String s = scanner.nextLine();
            String user = s.split(" ")[0];
            String model = s.split(" ")[1];
            // System.out.println(user + ": " + model);
            if (!map.containsKey(model)) {
                PriorityQueue<String> q = new PriorityQueue<>();
                q.offer(user);
                map.put(model, q);
            } else {
                if (!map.get(model).contains(user)) {
                    map.get(model).offer(user);
                }
            }
        }
        printResult(map);
    }
    public static void printResult(Map<String, PriorityQueue<String>> map) {
        for (String key : map.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(key);
            sb.append(" ");
            PriorityQueue<String> q = map.get(key);
            // System.out.println("Size(): " + q.size());
            int size = q.size();
            for (int i = 0; i < size; i++) {
                sb.append(q.poll());
                if (i < size - 1) {
                    sb.append(" ");
                }
            }
            System.out.println(sb.toString());
        }
    }

}
/* EOF */
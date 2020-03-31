package io.ziheng.others;

import java.util.Map;
import java.util.HashMap;

public class MaxUnique {

    private static int maxUnique(int[] nums, int n) {
        Map<Integer,Integer> map = new HashMap<>();
        // 加入所有不重复元素
        for (int i = 0; i < n ; i++) {
            int temp = nums[i];
            if (map.get(temp) == null) {
                map.put(temp, -1);
            }
        }
        int len = 0;
        int pre = -1;
        int cur = 0;
        for (int i = 0; i < n ; i++) {
            int temp = nums[i];
            pre = Math.max(pre, map.get(temp));
            cur = i - pre;
            // 更新长度
            len = Math.max(len, cur);
            // 更新表项
            map.put(temp, i);
        }
        return len;
    }
}
/* EOF */
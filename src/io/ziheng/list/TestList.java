package io.ziheng.list;

import java.util.LinkedList;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        System.out.println(list);
        for (int i = 0; i < 10; ++i) {
            list.add(i);
        }
        System.out.println(list);
        list.add(10, 100);
        System.out.println(list);
    }
}
/* EOF */
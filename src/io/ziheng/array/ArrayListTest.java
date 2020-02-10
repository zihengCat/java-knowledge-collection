package io.ziheng.array;

import io.ziheng.array.ArrayList;
import java.util.Random;

public class ArrayListTest {
    public static void main(String[] args) {
        testArrayList();
    }
    private static void testArrayList() {
        Random random = new Random();
        ArrayList<Integer> aList = new ArrayList<>();
        assert aList.size() == 0;
        assert aList.getCapacity() == 8;
        assert aList.toString().equals("[]");
        int capacity = 128;
        for (int i = 0; i < capacity; i++) {
            aList.addLast(random.nextInt(100));
        }
        aList.addLast(random.nextInt(100));
        assert aList.size() == capacity + 1;
        assert aList.getCapacity() == capacity * 2;
        while (!aList.isEmpty()) {
            aList.removeFirst();
        }
        assert aList.size() == 0;
        assert aList.getCapacity() == 0;

        aList.clear();

        aList.addFirst(0);
        aList.addLast(100);
        aList.add(1, 1);
        assert aList.get(1) == 1;
        aList.remove(1);
        aList.remove(0);
        assert aList.size() == 1 && aList.get(0) == 100;
        aList.set(0, 1024);
        assert aList.get(0) == 1024;

        System.out.println("Test result: OK");
    }
}
/* EOF */
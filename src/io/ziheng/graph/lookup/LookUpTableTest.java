package io.ziheng.graph.lookup;

import io.ziheng.graph.lookup.LookUpTable;
import io.ziheng.graph.lookup.LinkedListLookUpTable;
import io.ziheng.graph.lookup.RedBlackTreeSetLookUpTable;

public class LookUpTableTest {
    public static void main(String[] args) {
        testLookUpTable(new LinkedListLookUpTable<Integer>());
        testLookUpTable(new RedBlackTreeSetLookUpTable<Integer>());
    }
    public static void testLookUpTable(LookUpTable<Integer> lookUpTable) {
        int capacity = 10;
        for (int i = 0; i < capacity; i++) {
            lookUpTable.add(i);
        }
        for (LookUpTable.Iterator<Integer> iterator = lookUpTable.iterator();
            iterator.hasNext(); /* ... */ ) {
            System.out.println(iterator.next());
        }
    }
}
/* EOF */
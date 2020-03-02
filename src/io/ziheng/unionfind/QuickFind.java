package io.ziheng.unionfind;
public class QuickFind {
    private int[] id;
    private int count;
    public QuickFind(int capacity) {
        this.count = capacity;
        this.id = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            id[i] = i;
        }
    }

    public int find(int p) {
        return id[p];
    }

    public boolean isConnected(int p, int q) {
        return id[p] == id[q];
    }

    public int union(int p, int q) {
        int pId = id[p];
        int qId = id[q];
        if (pId == qId) {
            return pId;
        }
        for (int i = 0; i < count; i++) {
            if (id[i] == qId) {
                id[i] = pId;
            }
        }
        return pId;
    }
}
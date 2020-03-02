package io.ziheng.unionfind;

import io.ziheng.unionfind.UnionFind;

public class QuickUnion implements UnionFind {

    private int[] parent;
    private int count;

    public QuickUnion(int capacity) {
        this.count = capacity;
        this.parent = new int[capacity];
        // 设置每个节点的父节点为其自身
        for (int i = 0; i < count; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int find(int p) {
        checkIndex(p);
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        checkIndex(p);
        checkIndex(q);
        return find(p) == find(q);
    }

    @Override
    public int union(int p, int q) {
        checkIndex(p);
        checkIndex(q);
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return pRoot;
        }
        parent[qRoot] = pRoot;
        return pRoot;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException();
        }
    }
}
/* EOF */
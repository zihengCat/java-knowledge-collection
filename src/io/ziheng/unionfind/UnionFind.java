package io.ziheng.unionfind;
public interface UnionFind {
    int find(int p);
    int union(int p, int q);
    boolean isConnected(int p, int q);
}
/* EOF */
package io.ziheng.graph.leetcode;
/**
 * LeetCode 785. Is Graph Bipartite
 * https://leetcode.com/problems/is-graph-bipartite/
 */
public class IsGraphBipartite {

    private static int WHITE = 0;
    private static int BLACK = 1;
    private static int NO_COLOR = -1;

    public boolean isBipartite(int[][] graph) {
        int vertexNum = graph.length;
        int[] colors = new int[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            colors[i] = NO_COLOR;
        }
        for (int v = 0; v < vertexNum; v++) {
            if (colors[v] == -1) {
                if (depthFirstSearch(v, WHITE, colors, graph) == false) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean depthFirstSearch(
        int vertex, int color, int[] colors, int[][] graph) {
        colors[vertex] = color;
        for (int adjVertex : graph[vertex]) {
            if (colors[adjVertex] == -1) {
                boolean isOk = depthFirstSearch(adjVertex, inverse(color), colors, graph);
                if (!isOk) {
                    return false;
                }
            } else {
                if (colors[vertex] == colors[adjVertex]) {
                    return false;
                }
            }
        }
        return true;
    }
    private int inverse(int color) {
        return color == WHITE ? BLACK : WHITE;
    }
}
/* EOF */
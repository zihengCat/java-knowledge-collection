package io.ziheng.graph;
public interface Graph {
    /**
     * 获取图中顶点个数。
     *
     * @param void
     * @return int
     */
    int getVertexNum();
    /**
     * 获取图中边的条数。
     *
     * @param void
     * @return int
     */
    int getEdgeNum();
    /**
     * 检查图中两顶点间是否有边。
     * @param vert
     */
    boolean hasEdge(int vertexA, int vertexB);
    /**
     * 获取图中给定顶点的相邻顶点。
     *
     * @param vertex
     * @return int[]
     */
    int[] getAdjacentVertex(int vertex);
    /**
     * 获取图中给定顶点的度。
     *
     * @param vertex
     * @return int
     */
    int degree(int vertex);
}
/* EOF */
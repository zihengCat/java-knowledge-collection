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
     *
     * @param vert
     * @return boolean
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
    /**
     * 深度优先遍历。
     *
     * @param vertex
     * @return int[]
     */
    int[] depthFirstSearch();
    /**
     * 广度优先遍历。
     *
     * @param void
     * @return int[]
     */
    int[] breadthFirstSearch();
    /**
     * 两顶点间是否连通（两顶点位于同一连通分量内，存在路径）。
     *
     * @param vertexA
     * @param vertexB
     * @return boolean
     */
    boolean isConnected(int vertexA, int vertexB);
    /**
     * 检测图中是否有环。
     *
     * @param void
     * @return boolean
     */
    boolean hasCycle();
    /**
     * 寻找图中两顶点间的一条路径。
     *
     * @param sourceVertex
     * @param destinationVertex
     * @return int[]
     */
    int[] findAPath(int sourceVertex, int destinationVertex);
}
/* EOF */
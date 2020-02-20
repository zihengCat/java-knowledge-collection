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
     * 移除图中的一条边。
     *
     * @param vertexA
     * @param vertexB
     * @return void
     */
    void removeEdge(int vertexA, int vertexB);
    /**
     * 添加图中的一条边。
     *
     * @param vertexA
     * @param vertexB
     * @return boolean
     */
    boolean addEdge(int vertexA, int vertexB);
    /**
     * 清空图中所有的边。
     *
     * @param void
     * @return void
     */
    void clearEdges();
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
     * 获取图中连通分量个数。
     *
     * @param void
     * @return int
     */
    int connectedComponents();
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
    /**
     * 寻找图中两顶点间的最短路径。
     *
     * @param sourceVertex
     * @param destinationVertex
     * @return int[]
     */
    int[] findShortestPath(int sourceVertex, int destinationVertex);
    /**
     * 寻找图中两顶点间的最短路径的长度。
     *
     * @param sourceVertex
     * @param destinationVertex
     * @return int
     */
    int findShortestPathLength(int sourceVertex, int destinationVertex);
    /**
     * 寻找哈密尔顿回路。
     *
     * @param void
     * @return int[]
     */
    int[] hamiltonianCircuit();
    /**
     * 寻找给定顶点的哈密尔顿路径。
     *
     * @param vertex
     * @return int[]
     */
    int[] hamiltonianPath(int vertex);

    /**
     * 深拷贝克隆接口。
     *
     * @param void
     * @return Object
     * @throws CloneNotSupportedException
     */
    Object clone() throws CloneNotSupportedException;
}
/* EOF */
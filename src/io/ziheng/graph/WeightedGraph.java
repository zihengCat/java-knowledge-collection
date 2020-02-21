package io.ziheng.graph;
import io.ziheng.graph.Graph;
import java.util.List;
import java.util.Map;
public interface WeightedGraph extends Graph {
    /**
     * 获取两顶点边的权值。
     *
     * @param vertexA
     * @param vertexB
     * @return int
     */
    int getWeight(int vertexA, int vertexB);
    /**
     * 向图中添加一条带权边。
     * 操作失败（边已存在）返回{@code false} ，
     * 操作成功返回{@code true}。
     *
     * @param vertexA
     * @param vertexB
     * @param weight
     * @return boolean
     */
    boolean addEdge(int vertexA, int vertexB, int weight);
    /**
     * 使用 Kruskal 算法寻找带权图的最小生成树。
     * <pre>
     * {@code List<WeightedEdge>} ->
     * {
     *     "vertexA": 0,
     *     "vertexB": 1,
     *     "weight": 123,
     * }
     * </pre>
     *
     * @param void
     * @return {@code List<Map<String, Integer>>}
     */
    List<Map<String, Integer>> kruskalMinimumSpanningTree();
    /**
     * 使用 Prim 算法寻找带权图的最小生成树。
     * <pre>
     * {@code List<WeightedEdge>} ->
     * {
     *     "vertexA": 0,
     *     "vertexB": 1,
     *     "weight": 123,
     * }
     * </pre>
     *
     * @param void
     * @return {@code List<Map<String, Integer>>}
     */
    List<Map<String, Integer>> primMinimumSpanningTree();
    /**
     * 使用 Dijkstra 算法寻找最短路径。
     * - 时间复杂度（迭代）：{@code O(E*V^2)}
     * - 时间复杂度（优先队列）：{@code O(E*logE)}
     * - 时间复杂度（索引堆）：{@code O(E*logV)}
     *
     * @param sourceVertex
     * @param destinationVertex
     * @return {@code int[]}
     */
    int[] dijkstraShortestPath(int sourceVertex, int destinationVertex);
}
/* EOF */
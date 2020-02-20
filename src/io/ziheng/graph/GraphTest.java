package io.ziheng.graph;

import io.ziheng.graph.Graph;
import io.ziheng.graph.AdjacencyMatrix;
import io.ziheng.graph.AdjacencyList;

import io.ziheng.graph.WeightedGraph;
import io.ziheng.graph.WeightedAdjacencyList;

import java.util.Arrays;

public class GraphTest {

    public static void main(String[] args) {
        testGraphAdjacencyMatrix();
        testGraphAdjacencyList();
        testGraphCloneable();
        testGraphDepthFirstSearch();
        testGraphBreadthFirstSearch();
        testGraphFindAPath();
        testGraphFindShortestPath();
        testGraphHasCycle();
        testGraphHamiltonianPathAndCircuit();
        // ...
        testWeightedGraph();
        testWeightedGraphMinimumSpanningTree();
    }
    public static void testWeightedGraphMinimumSpanningTree() {
        int[][] weightedGraph = new int[][] {
            {4, 5, 0, },
            {0, 1, 10, },
            {0, 2, 2, },
            {0, 3, 3, },
            {1, 2, 4, },
            {1, 3, 5, },
        };
        WeightedGraph wGraph = WeightedAdjacencyList.buildWith(weightedGraph);
        assert wGraph.kruskalMinimumSpanningTree().size() == wGraph.primMinimumSpanningTree().size()
        && wGraph.kruskalMinimumSpanningTree().size() == wGraph.getVertexNum() - 1
        && wGraph.primMinimumSpanningTree().size() == wGraph.getVertexNum() - 1;
        System.out.println(
            "WeightedGraph.kruskalMinimumSpanningTree(): " +
            wGraph.kruskalMinimumSpanningTree()
        );
        System.out.println(
            "WeightedGraph.primMinimumSpanningTree(): " +
            wGraph.primMinimumSpanningTree()
        );
    }
    public static void testWeightedGraph() {
        int[][] weightedGraph = new int[][] {
            {4, 5, 0, },
            {0, 1, 1, },
            {0, 2, 2, },
            {0, 3, 3, },
            {1, 2, 4, },
            {1, 3, 5, },
        };
        WeightedGraph wGraph = WeightedAdjacencyList.buildWith(weightedGraph);
        System.out.println(wGraph);
        System.out.println(
            "WeightedAdjacencyList.getWeigh(0, 1): " +
            wGraph.getWeight(0, 1)
        );
    }
    public static void testGraphCloneable() {
        int[][] graph = new int[][] {
            {4, 5, },
            {0, 1, },
            {0, 2, },
            {0, 3, },
            {1, 2, },
            {1, 3, },
        };
        Graph adjList = AdjacencyList.buildWith(graph);
        Graph adjListCloned = null;
        try {
            adjListCloned = (AdjacencyList)adjList.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert adjList.toString().equals(adjListCloned.toString());
        System.out.println(
            "AdjacencyList.clone(): " + System.lineSeparator() +
            adjListCloned.toString()
        );
    }
    public static void testGraphHamiltonianPathAndCircuit() {
        int[][] graph = new int[][] {
            {4, 5, },
            {0, 1, },
            {0, 2, },
            {0, 3, },
            {1, 2, },
            {1, 3, },
        };
        Graph adjList = AdjacencyList.buildWith(graph);
        System.out.println(
            "AdjacencyList.hamiltonianPath(0): " +
            Arrays.toString(adjList.hamiltonianPath(0))
        );
        adjList = AdjacencyList.buildWith(
            "io/ziheng/graph/hamiltonia_graph.txt"
        );
        System.out.println(
            "AdjacencyList.hamiltonianPath(0): " +
            Arrays.toString(adjList.hamiltonianPath(0))
        );
        System.out.println(
            "AdjacencyList.hamiltonianCircuit(): " +
            Arrays.toString(adjList.hamiltonianCircuit())
        );
    }
    public static void testGraphHasCycle() {
        int[][] graph = new int[][] {
            {7, 5, },
            {0, 1, },
            {0, 2, },
            {1, 3, },
            {1, 4, },
            //{2, 3, },
            {2, 6, },
        };
        Graph adjList = AdjacencyList.buildWith(graph);
        System.out.println(
            "AdjacencyList.hasCycle(): " + adjList.hasCycle()
        );
    }
    public static void testGraphFindShortestPath() {
        int[][] graph = new int[][] {
            {7, 6, },
            {0, 1, },
            {0, 2, },
            {1, 3, },
            {1, 4, },
            {2, 3, },
            {2, 6, },
        };
        Graph adjList = AdjacencyList.buildWith(graph);
        System.out.println(
            "AdjacencyList.findShortestPath(0, 6): " +
            Arrays.toString(adjList.findShortestPath(0, 6))
        );
        System.out.println(
            "AdjacencyList.findShortestPathLength(0, 6): " +
            adjList.findShortestPathLength(0, 6)
        );
        System.out.println(
            "AdjacencyList.findShortestPath(0, 5): " +
            Arrays.toString(adjList.findShortestPath(0, 5))
        );
    }
    public static void testGraphFindAPath() {
        int[][] graph = new int[][] {
            {7, 6, },
            {0, 1, },
            {0, 2, },
            {1, 3, },
            {1, 4, },
            {2, 3, },
            {2, 6, },
        };
        Graph adjList = AdjacencyList.buildWith(graph);
        System.out.println(
            "AdjacencyList.findAPath(0, 6): " +
            Arrays.toString(adjList.findAPath(0, 6))
        );
        System.out.println(
            "AdjacencyList.findAPath(2, 5): " +
            Arrays.toString(adjList.findAPath(2, 5))
        );
    }
    public static void testGraphBreadthFirstSearch() {
        int[][] graph = new int[][] {
            {7, 6, },
            {0, 1, },
            {0, 2, },
            {1, 3, },
            {1, 4, },
            {2, 3, },
            {2, 6, },
        };
        Graph adjList = AdjacencyList.buildWith(graph);
        System.out.println(
            "AdjacencyList BFS: " +
            Arrays.toString(adjList.breadthFirstSearch())
        );
    }
    public static void testGraphDepthFirstSearch() {
        int[][] graph = new int[][] {
            {7, 6, },
            {0, 1, },
            {0, 2, },
            {1, 3, },
            {1, 4, },
            {2, 3, },
            {2, 6, },
        };
        Graph adjMatrix = AdjacencyMatrix.buildWith(graph);
        Graph adjList = AdjacencyList.buildWith(graph);
        System.out.println(
            "AdjacencyMatrix DFS: " +
            Arrays.toString(adjMatrix.depthFirstSearch())
        );
        System.out.println(
            "AdjacencyList DFS: " +
            Arrays.toString(adjList.depthFirstSearch())
        );
        System.out.println(
            "isConnected(1, 5): " +
            adjList.isConnected(1, 5)
        );
        System.out.println(
            "isConnected(1, 5): " +
            adjMatrix.isConnected(1, 5)
        );
    }

    public static void testGraphAdjacencyList() {
        Graph graphA = AdjacencyList.buildWith(
            "io/ziheng/graph/graph.txt"
        );
        Graph graphB = AdjacencyList.buildWith(
            new int[][] {
                {7, 9, },
                {0, 1, },
                {0, 3, },
                {1, 2, },
                {1, 6, },
                {2, 3, },
                {2, 5, },
                {3, 4, },
                {4, 5, },
                {5, 6, },
            }
        );
        assert graphA.toString().equals(graphB.toString());
        System.out.println(graphA);
        System.out.println(
            Arrays.toString(graphA.getAdjacentVertex(6))
        );
        System.out.println(graphB.degree(0));
    }
    public static void testGraphAdjacencyMatrix() {
        Graph graphA = AdjacencyMatrix.buildWith(
            "io/ziheng/graph/graph.txt"
        );
        Graph graphB = AdjacencyMatrix.buildWith(
            new int[][] {
                {7, 9, },
                {0, 1, },
                {0, 3, },
                {1, 2, },
                {1, 6, },
                {2, 3, },
                {2, 5, },
                {3, 4, },
                {4, 5, },
                {5, 6, },
            }
        );
        assert graphA.toString().equals(graphB.toString());
        System.out.println(graphA);
        System.out.println(
            Arrays.toString(graphA.getAdjacentVertex(6))
        );
        System.out.println(graphB.degree(0));
    }
}
/* EOF */
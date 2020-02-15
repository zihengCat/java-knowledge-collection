package io.ziheng.graph;

import io.ziheng.graph.Graph;
import io.ziheng.graph.AdjacencyMatrix;
import io.ziheng.graph.AdjacencyList;

import java.util.Arrays;

public class GraphTest {

    public static void main(String[] args) {
        testGraphAdjacencyMatrix();
        testGraphAdjacencyList();
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
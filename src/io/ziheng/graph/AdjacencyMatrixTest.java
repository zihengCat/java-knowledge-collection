package io.ziheng.graph;

import io.ziheng.graph.AdjacencyMatrix;
import java.util.Arrays;

public class AdjacencyMatrixTest {
    public static void main(String[] args) {
        AdjacencyMatrix adjA = AdjacencyMatrix.buildWith(
            "io/ziheng/graph/graph.txt"
        );
        AdjacencyMatrix adjB = AdjacencyMatrix.buildWith(
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
        assert adjA.toString().equals(adjB.toString());
        System.out.println(
            Arrays.toString(adjA.getAdjacentVertex(6))
        );
        System.out.println(adjA.degree(0));
    }
}
/* EOF */
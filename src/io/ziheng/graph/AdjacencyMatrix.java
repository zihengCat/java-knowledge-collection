package io.ziheng.graph;

import java.io.File;
import java.util.Scanner;

public class AdjacencyMatrix {

    private int vertexNum;
    private int edgeNum;
    private int[][] adjacencyMatrix;

    private AdjacencyMatrix() {
        // ...
    }

    public static AdjacencyMatrix buildWith(int[][] adjMatrix) {
        if (adjMatrix == null ||
            adjMatrix.length == 0 ||
            adjMatrix[0].length != 2 ||
            adjMatrix[0][0] < 0 ||
            adjMatrix[0][1] < 0) {
            return null;
        }
        AdjacencyMatrix adj = new AdjacencyMatrix();
        adj.vertexNum = adjMatrix[0][0];
        adj.edgeNum = adjMatrix[0][1];
        adj.adjacencyMatrix = new int[adj.vertexNum][adj.vertexNum];
        for (int i = 1; i < adjMatrix.length; i++) {
            int vertexA = adjMatrix[i][0];
            int vertexB = adjMatrix[i][1];
            vaildateVertex(vertexA, adj.vertexNum);
            vaildateVertex(vertexB, adj.vertexNum);
            adj.adjacencyMatrix[vertexA][vertexB] = 1;
            adj.adjacencyMatrix[vertexB][vertexA] = 1;
        }
        return adj;
    }

    public static AdjacencyMatrix buildWith(String fileName) {
        try {
            AdjacencyMatrix adj = new AdjacencyMatrix();
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            adj.vertexNum = scanner.nextInt();
            adj.edgeNum = scanner.nextInt();
            adj.adjacencyMatrix = new int[adj.vertexNum][adj.vertexNum];
            for (int i = 0; i < adj.edgeNum; i++) {
                int vertexA = scanner.nextInt();
                int vertexB = scanner.nextInt();
                vaildateVertex(vertexA, adj.vertexNum);
                vaildateVertex(vertexB, adj.vertexNum);
                adj.adjacencyMatrix[vertexA][vertexB] = 1;
                adj.adjacencyMatrix[vertexB][vertexA] = 1;
            }
            scanner.close();
            return adj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void vaildateVertex(int vertex, int vertexNum) {
        if (vertex < 0 || vertex >= vertexNum) {
            throw new IllegalArgumentException(
                "[ERROR]: vertex " + vertex + " is invalid!"
            );
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(
            "vertexNum = %d, edgeNum = %d",
            this.vertexNum, this.edgeNum
        ));
        stringBuilder.append(System.lineSeparator());
        for (int i = 0; i < this.vertexNum; i++) {
            for (int j = 0; j < this.vertexNum; j++) {
                stringBuilder.append(String.format(
                    "%d ", this.adjacencyMatrix[i][j]
                ));
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
/* EOF */
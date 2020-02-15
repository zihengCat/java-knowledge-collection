package io.ziheng.graph;

import io.ziheng.graph.Graph;
import io.ziheng.graph.lookup.LookUpTable;
import io.ziheng.graph.lookup.LinkedListLookUpTable;
import io.ziheng.graph.lookup.RedBlackTreeSetLookUpTable;

import java.io.File;
import java.util.Scanner;

public class AdjacencyList implements Graph {

    private int vertexNum;
    private int edgeNum;
    private LookUpTable<Integer>[] adjacencyList;

    private AdjacencyList() {
        // ...
    }

    @SuppressWarnings("unchecked")
    public static AdjacencyList buildWith(int[][] adjMatrix) {
        if (adjMatrix == null ||
            adjMatrix.length == 0 ||
            adjMatrix[0].length != 2 ||
            adjMatrix[0][0] < 0 ||
            adjMatrix[0][1] < 0) {
            return null;
        }
        AdjacencyList adj = new AdjacencyList();
        adj.vertexNum = adjMatrix[0][0];
        adj.edgeNum = adjMatrix[0][1];
        adj.adjacencyList =
            (LookUpTable<Integer>[])new LookUpTable[adj.vertexNum];
        for (int i = 0; i < adj.vertexNum; i++) {
            adj.adjacencyList[i] = new LinkedListLookUpTable<Integer>();
            //adj.adjacencyList[i] = new RedBlackTreeSetLookUpTable<Integer>();
        }
        for (int i = 1; i < adjMatrix.length; i++) {
            int vertexA = adjMatrix[i][0];
            int vertexB = adjMatrix[i][1];
            vaildateVertex(vertexA, adj.vertexNum);
            vaildateVertex(vertexB, adj.vertexNum);
            adj.adjacencyList[vertexA].add(vertexB);
            adj.adjacencyList[vertexB].add(vertexA);
        }
        return adj;
    }
    @SuppressWarnings("unchecked")
    public static AdjacencyList buildWith(String fileName) {
        try {
            AdjacencyList adj = new AdjacencyList();
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            adj.vertexNum = scanner.nextInt();
            adj.edgeNum = scanner.nextInt();
            adj.adjacencyList =
                (LookUpTable<Integer>[])new LookUpTable[adj.vertexNum];
            for (int i = 0; i < adj.vertexNum; i++) {
                //adj.adjacencyList[i] = new LinkedListLookUpTable<Integer>();
                adj.adjacencyList[i] = new RedBlackTreeSetLookUpTable<Integer>();
            }
            for (int i = 0; i < adj.edgeNum; i++) {
                int vertexA = scanner.nextInt();
                int vertexB = scanner.nextInt();
                vaildateVertex(vertexA, adj.vertexNum);
                vaildateVertex(vertexB, adj.vertexNum);
                adj.adjacencyList[vertexA].add(vertexB);
                adj.adjacencyList[vertexB].add(vertexA);
            }
            scanner.close();
            return adj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int getVertexNum() {
        return vertexNum;
    }
    @Override
    public int getEdgeNum() {
        return edgeNum;
    }
    @Override
    public boolean hasEdge(int vertexA, int vertexB) {
        vaildateVertex(vertexA, this.vertexNum);
        vaildateVertex(vertexA, this.vertexNum);
        return adjacencyList[vertexA].contains(vertexB);
    }
    @Override
    public int[] getAdjacentVertex(int vertex) {
        vaildateVertex(vertex, this.vertexNum);
        int[] resultList = new int[adjacencyList[vertex].size()];
        int i = 0;
        for (LookUpTable.Iterator<Integer> iterator = adjacencyList[vertex].iterator();
            iterator.hasNext(); /* ... */ ) {
            resultList[i] = iterator.next();
            i++;
        }
        return resultList;
    }
    @Override
    public int degree(int vertex) {
        vaildateVertex(vertex, this.vertexNum);
        return adjacencyList[vertex].size();
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
            stringBuilder.append(String.format("%d: ", i));
            for (LookUpTable.Iterator<Integer> iterator = adjacencyList[i].iterator();
                iterator.hasNext(); /* ... */ ) {
                stringBuilder.append(String.format(
                    "%d ", iterator.next()
                ));
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
/* EOF */
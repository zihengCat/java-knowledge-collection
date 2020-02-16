package io.ziheng.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import io.ziheng.graph.Graph;

/**
 * 邻接矩阵实现类
 */
public class AdjacencyMatrix implements Graph {
    /**
     * 图中顶点个数
     */
    private int vertexNum;
    /**
     * 图中边的条数
     */
    private int edgeNum;
    /**
     * 领接矩阵
     */
    private int[][] adjacencyMatrix;
    /**
     * 私有构造函数
     */
    private AdjacencyMatrix() {
        // ...
    }
    /**
     * 静态工厂方法：从二维数组中构造邻接矩阵。
     *
     * @param fileName
     * @return AdjacencyMatrix
     */
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
    /**
     * 静态工厂方法：从文件构造邻接矩阵。
     *
     * @param fileName
     * @return AdjacencyMatrix
     */
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
    /**
     * 获取图中顶点个数。
     *
     * @param void
     * @return int
     */
    @Override
    public int getVertexNum() {
        return vertexNum;
    }
    /**
     * 获取图中边的条数。
     *
     * @param void
     * @return int
     */
    @Override
    public int getEdgeNum() {
        return edgeNum;
    }
    /**
     * 判断图中两顶点间是否有边。
     *
     * @param vertexA
     * @param vertexB
     * @return boolean
     */
    @Override
    public boolean hasEdge(int vertexA, int vertexB) {
        vaildateVertex(vertexA, this.vertexNum);
        vaildateVertex(vertexB, this.vertexNum);
        return adjacencyMatrix[vertexA][vertexB] == 1;
    }
    /**
     * 获取图中给定顶点的相邻顶点。
     *
     * @param vertex
     * @return int[]
     */
    @Override
    public int[] getAdjacentVertex(int vertex) {
        vaildateVertex(vertex, this.vertexNum);
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < this.vertexNum; i++) {
            if (adjacencyMatrix[vertex][i] == 1) {
                resultList.add(i);
            }
        }
        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }
    /**
     * 获取图中给定顶点的度。
     *
     * @param vertex
     * @return int
     */
    @Override
    public int degree(int vertex) {
        vaildateVertex(vertex, this.vertexNum);
        int degreeNum = 0;
        for (int i = 0; i < this.vertexNum; i++) {
            if (adjacencyMatrix[vertex][i] == 1) {
                degreeNum++;
            }
        }
        return degreeNum;
    }
    @Override
    public boolean isConnected(int vertexA, int vertexB) {
        vaildateVertex(vertexA, this.vertexNum);
        vaildateVertex(vertexB, this.vertexNum);
        List<Integer> resultList = new LinkedList<Integer>();
        boolean[] visited = new boolean[this.vertexNum];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        depthFirstSearch0(vertexA, visited, resultList);
        return resultList.contains(vertexB);
    }
    @Override
    public int[] depthFirstSearch() {
        List<Integer> resultList = new LinkedList<Integer>();
        boolean[] visited = new boolean[this.vertexNum];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < this.vertexNum; i++) {
            if (!visited[i]) {
                depthFirstSearch0(i, visited, resultList);
            }
        }
        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }
    private void depthFirstSearch0(
        int vertex, boolean[] visited, List<Integer> resultList) {
        visited[vertex] = true;
        resultList.add(vertex);
        for (int v : getAdjacentVertex(vertex)) {
            if (!visited[v]) {
                depthFirstSearch0(v, visited, resultList);
            }
        }
    }
    /**
     * 验证传入顶点的合法性。
     *
     * @param vertex
     * @param vertexNum
     * @return void
     */
    private static void vaildateVertex(int vertex, int vertexNum) {
        if (vertex < 0 || vertex >= vertexNum) {
            throw new IllegalArgumentException(
                "[ERROR]: vertex " + vertex + " is invalid!"
            );
        }
    }
    /**
     * 重载字符串化函数。
     *
     * @param void
     * @return String
     */
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
package io.ziheng.graph;

import io.ziheng.graph.WeightedGraph;

import java.lang.Cloneable;
import java.io.File;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.HashMap;

public class WeightedAdjacencyList implements WeightedGraph, Cloneable {

    private int vertexNum;
    private int edgeNum;
    private Map<Integer, Integer>[] adjacencyMap;

    private WeightedAdjacencyList() {
        // ...
    }

    @SuppressWarnings("unchecked")
    public static WeightedAdjacencyList buildWith(int[][] adjMatrix) {
        if (adjMatrix == null ||
            adjMatrix.length == 0 ||
            // 顶点, 边, 权值
            adjMatrix[0].length != 3 ||
            adjMatrix[0][0] < 0 ||
            adjMatrix[0][1] < 0) {
            return null;
        }
        WeightedAdjacencyList adj = new WeightedAdjacencyList();
        adj.vertexNum = adjMatrix[0][0];
        adj.edgeNum = adjMatrix[0][1];
        adj.adjacencyMap =
            (Map<Integer, Integer>[])new Map[adj.vertexNum];
        for (int i = 0; i < adj.vertexNum; i++) {
            adj.adjacencyMap[i] = new TreeMap<Integer, Integer>();
        }
        for (int i = 1; i < adjMatrix.length; i++) {
            int vertexA = adjMatrix[i][0];
            int vertexB = adjMatrix[i][1];
            int weightedValue = adjMatrix[i][2];
            vaildateVertex(vertexA, adj.vertexNum);
            vaildateVertex(vertexB, adj.vertexNum);
            adj.adjacencyMap[vertexA].put(vertexB, weightedValue);
            adj.adjacencyMap[vertexB].put(vertexA, weightedValue);
        }
        return adj;
    }
    @SuppressWarnings("unchecked")
    public static WeightedAdjacencyList buildWith(String fileName) {
        try {
            WeightedAdjacencyList adj = new WeightedAdjacencyList();
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            adj.vertexNum = scanner.nextInt();
            adj.edgeNum = scanner.nextInt();
            adj.adjacencyMap =
                (Map<Integer, Integer>[])new Map[adj.vertexNum];
            for (int i = 0; i < adj.vertexNum; i++) {
                adj.adjacencyMap[i] = new TreeMap<Integer, Integer>();
            }
            for (int i = 0; i < adj.edgeNum; i++) {
                int vertexA = scanner.nextInt();
                int vertexB = scanner.nextInt();
                int weightedValue = scanner.nextInt();
                vaildateVertex(vertexA, adj.vertexNum);
                vaildateVertex(vertexB, adj.vertexNum);
                adj.adjacencyMap[vertexA].put(vertexB, weightedValue);
                adj.adjacencyMap[vertexB].put(vertexA, weightedValue);
            }
            scanner.close();
            return adj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /****************** Weighted Graph Operations *******************/
    private class Pair<L, R> {
        private L left;
        private R right;
        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }
        public L getLeft() {
            return left;
        }
        public R getRight() {
            return right;
        }
    }
    @Override
    public int[] dijkstraShortestPath(int sourceVertex, int destinationVertex) {
        vaildateVertex(sourceVertex, vertexNum);
        vaildateVertex(destinationVertex, vertexNum);
        Integer[] previous = new Integer[vertexNum];
        // 源头顶点到图中各顶点的最短路径
        Integer[] dis = new Integer[vertexNum];
        // 记录哪些顶点已经确定了最短路径
        Boolean[] visited = new Boolean[vertexNum];
        setArray(previous, -1);
        setArray(dis, Integer.MAX_VALUE);
        setArray(visited, false);
        previous[sourceVertex] = sourceVertex;
        // 源头顶点到自身的最短路径已经确定 -> 0
        dis[sourceVertex] = 0;
        // 不能设为 true
        //visited[sourceVertex] = true;
        while (true) {
            /**
             * Step 1. 寻找最短路径
             * 优化点：优先队列
             */
            int currentMinimalDistance = Integer.MAX_VALUE;
            int currentMinimalVertex = -1;
            PriorityQueue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<>(
                new Comparator<Pair<Integer, Integer>>() {
                    @Override
                    public int compare(
                        Pair<Integer, Integer> o1,
                        Pair<Integer, Integer> o2) {
                        return o1.getRight() - o2.getRight();
                    }
                }
            );
            // 遍历顶点寻找最短路径
            for (int vertex = 0; vertex < vertexNum; vertex++) {
                if (!visited[vertex] && dis[vertex] < currentMinimalDistance) {
                    currentMinimalDistance = dis[vertex];
                    currentMinimalVertex = vertex;
                }
            }
            // 所有顶点都已找到最短路径 -> 退出
            if (currentMinimalVertex == -1) {
                break;
            }
            /**
             * Step 2. 确认最短路径
             */
            visited[currentMinimalVertex] = true;
            /**
             * Step 3. 更新最短路径
             */
            for (int adjVertex : getAdjacentVertex(currentMinimalVertex)) {
                if (!visited[adjVertex]) {
                    if (dis[currentMinimalVertex]
                        + getWeight(currentMinimalVertex, adjVertex)
                        < dis[adjVertex]) {
                        dis[adjVertex] = dis[currentMinimalVertex] +
                            getWeight(currentMinimalVertex, adjVertex);
                        previous[adjVertex] = currentMinimalVertex;
                    }
                }
            }
        }
        //int[] resultArray = new int[dis.length];
        //for (int i = 0; i < resultArray.length; i++) {
        //    resultArray[i] = dis[i];
        //}
        return dijkstraShortestPath0(sourceVertex, destinationVertex, previous);
    }
    private int[] dijkstraShortestPath0(
        int sourceVertex, int destinationVertex, Integer[] previous) {
        List<Integer> resultList = new LinkedList<>();
        int currentVertex = destinationVertex;
        while (currentVertex != sourceVertex) {
            resultList.add(currentVertex);
            currentVertex = previous[currentVertex];
        }
        resultList.add(sourceVertex);
        Collections.reverse(resultList);
        return toIntArray(resultList);
    }
    @Override
    public List<Map<String, Integer>> primMinimumSpanningTree() {
        List<Map<String, Integer>> minimumSpanningTreeList = new LinkedList<>();
        /**
         * 图不连通（连通分量 != 1）无法计算最小生成树
         */
        if (connectedComponents() != 1) {
            return minimumSpanningTreeList;
        }
        /**
         * 切分图：V{0}, V{1...N}
         */
        Boolean[] cutVertices = new Boolean[vertexNum];
        setArray(cutVertices, false);
        cutVertices[0] = true;
        /**
         * 迭代遍历切分图
         */
        for (int i = 1; i < vertexNum; i++) {
            Map<String, Integer> minEdge = new HashMap<>();
            minEdge.put("vertexA", -1);
            minEdge.put("vertexB", -1);
            minEdge.put("weight", Integer.MAX_VALUE);
            /**
             * 在切分图另一部分寻找最小权值边
             */
            for (int currentVertex = 0; currentVertex < vertexNum; currentVertex++) {
                if (cutVertices[currentVertex]) {
                    for (int adjVertex : getAdjacentVertex(currentVertex)) {
                        if (!cutVertices[adjVertex] &&
                            getWeight(currentVertex, adjVertex) < minEdge.get("weight")) {
                            minEdge.replace("vertexA", currentVertex);
                            minEdge.replace("vertexB", adjVertex);
                            minEdge.replace("weight", getWeight(currentVertex, adjVertex));
                        }
                    }
                }
            }
            /**
             * 最小权值边两顶点加入切分图中。
             */
            minimumSpanningTreeList.add(minEdge);
            cutVertices[minEdge.get("vertexA")] = true;
            cutVertices[minEdge.get("vertexB")] = true;
        }
        return minimumSpanningTreeList;
    }
    @Override
    public List<Map<String, Integer>> kruskalMinimumSpanningTree() {
        List<Map<String, Integer>> minimumSpanningTreeList = new LinkedList<>();
        /**
         * 图不连通（连通分量 != 1）无法计算最小生成树
         */
        if (connectedComponents() != 1) {
            return minimumSpanningTreeList;
        }
        List<Map<String, Integer>> edgesList = new LinkedList<>();
        /**
         * 遍历图中所有边，按照权值排序。
         */
        for (int vertex = 0; vertex < vertexNum; vertex++) {
            for (int adjVertex : getAdjacentVertex(vertex)) {
                if (vertex < adjVertex) {
                    Map<String, Integer> map = new HashMap<>();
                    map.put("vertexA", vertex);
                    map.put("vertexB", adjVertex);
                    map.put("weight", getWeight(vertex, adjVertex));
                    edgesList.add(map);
                }
            }
        }
        Collections.sort(edgesList,
            new Comparator<Map<String, Integer>>() {
                @Override
                public int compare(
                    Map<String, Integer> o1,
                    Map<String, Integer> o2) {
                    return o1.get("weight") - o2.get("weight");
                }
            }
        );
        /**
         * 构造一个初始状态为 N 个顶点而无边的非连通图
         */
        WeightedGraph weightedGraphWithoutEdges = null;
        try {
            weightedGraphWithoutEdges = (WeightedAdjacencyList)this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        weightedGraphWithoutEdges.clearEdges();
        /**
         * 选择权值最小的边，
         * 若该边依附的顶点落在不同的连通分量上，则将该边加入到非连通图中，
         * 否则舍去此边，
         * 以此类推，直至所有顶点都处于同一个连通分量上。
         * 该连通分量即为当前图的最小生成树 MST。
         */
        for (Map<String, Integer> weightedEdge : edgesList) {
            int vertexA = weightedEdge.get("vertexA");
            int vertexB = weightedEdge.get("vertexB");
            int weight = weightedEdge.get("weight");
            if (!weightedGraphWithoutEdges.isConnected(vertexA, vertexB)) {
                minimumSpanningTreeList.add(weightedEdge);
                weightedGraphWithoutEdges.addEdge(vertexA, vertexB, weight);
            }
        }
        return minimumSpanningTreeList;
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
    public int getWeight(int vertexA, int vertexB) {
        if (hasEdge(vertexA, vertexB)) {
            return adjacencyMap[vertexA].get(vertexB);
        }
        return -1;
    }
    @Override
    public boolean hasEdge(int vertexA, int vertexB) {
        vaildateVertex(vertexA, this.vertexNum);
        vaildateVertex(vertexB, this.vertexNum);
        return adjacencyMap[vertexA].containsKey(vertexB);
    }
    @Override
    public void removeEdge(int vertexA, int vertexB) {
        vaildateVertex(vertexA, vertexNum);
        vaildateVertex(vertexB, vertexNum);
        adjacencyMap[vertexA].remove(vertexB);
        adjacencyMap[vertexB].remove(vertexA);
    }
    @Override
    public boolean addEdge(int vertexA, int vertexB) {
        // Unsupported Operation
        return false;
    }
    @Override
    public boolean addEdge(int vertexA, int vertexB, int weight) {
        vaildateVertex(vertexA, vertexNum);
        vaildateVertex(vertexB, vertexNum);
        if (!hasEdge(vertexA, vertexB)) {
            adjacencyMap[vertexA].put(vertexB, weight);
            adjacencyMap[vertexB].put(vertexA, weight);
            return true;
        }
        return false;
    }
    @Override
    public void clearEdges() {
        for (int i = 0; i < vertexNum; i++) {
            adjacencyMap[i].clear();
        }
    }
    @Override
    public int[] getAdjacentVertex(int vertex) {
        vaildateVertex(vertex, vertexNum);
        int[] resultList = new int[adjacencyMap[vertex].size()];
        int i = 0;
        for (int adjVertex : adjacencyMap[vertex].keySet()) {
            resultList[i] = adjVertex;
            i++;
        }
        return resultList;
    }
    @Override
    public int degree(int vertex) {
        vaildateVertex(vertex, this.vertexNum);
        return adjacencyMap[vertex].size();
    }
    @Override
    public boolean hasCycle() {
        Map<String, Boolean> map = new HashMap<>();
        map.put("hasCycleState", false);
        boolean[] visited = new boolean[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < vertexNum; i++) {
            if (!visited[i]) {
                hasCycle0(i, i, visited, map);
            }
        }
        return map.get("hasCycleState");
    }
    private void hasCycle0(
        int currentVertex, int parentVertex, boolean[] visited, Map<String, Boolean> map) {
        visited[currentVertex] = true;
        for (int adjVertex : getAdjacentVertex(currentVertex)) {
            if (!visited[adjVertex]) {
                hasCycle0(adjVertex, currentVertex, visited, map);
            } else {
                if (adjVertex != parentVertex) {
                    map.replace("hasCycleState", true);
                }
            }
        }
    }
    @Override
    public int connectedComponents() {
        Boolean[] visited = new Boolean[vertexNum];
        setArray(visited, false);
        int connectedComponentsNum = 0;
        for (int i = 0; i < vertexNum; i++) {
            if (!visited[i]) {
                connectedComponents0(i, visited);
                connectedComponentsNum++;
            }
        }
        return connectedComponentsNum;
    }
    private void connectedComponents0(int vertex, Boolean[] visited) {
        visited[vertex] = true;
        for (int adjVertex : getAdjacentVertex(vertex)) {
            if (!visited[adjVertex]) {
                connectedComponents0(adjVertex, visited);
            }
        }
    }
    @Override
    public int[] findShortestPath(int sourceVertex, int destinationVertex) {
        vaildateVertex(sourceVertex, this.vertexNum);
        vaildateVertex(destinationVertex, this.vertexNum);
        List<Integer> resultList = new LinkedList<Integer>();
        Integer[] visited = new Integer[vertexNum];
        setArray(visited, -1);
        findShortestPath0(
            sourceVertex,
            destinationVertex,
            sourceVertex,
            visited
        );
        //System.out.println(Arrays.toString(visited));
        if (visited[destinationVertex] != -1) {
            for (int currentVertex = destinationVertex;
                currentVertex != sourceVertex;
                currentVertex = visited[currentVertex]) {
                resultList.add(currentVertex);
            }
            resultList.add(sourceVertex);
            Collections.reverse(resultList);
        }
        return toIntArray(resultList);
    }
    private void findShortestPath0(
        int sourceVertex,
        int destinationVertex,
        int parentVertex,
        Integer[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(sourceVertex);
        visited[sourceVertex] = parentVertex;
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            for (int adjVertex : getAdjacentVertex(currentVertex)) {
                if (visited[adjVertex] == -1) {
                    visited[adjVertex] = currentVertex;
                    if (adjVertex == destinationVertex) {
                        return;
                    }
                    queue.offer(adjVertex);
                }
            }
        }
    }
    @Override
    public int findShortestPathLength(int sourceVertex, int destinationVertex) {
        return findShortestPath(sourceVertex, destinationVertex).length - 1;
    }
    @Override
    public int[] findAPath(int sourceVertex, int destinationVertex) {
        vaildateVertex(sourceVertex, this.vertexNum);
        vaildateVertex(destinationVertex, this.vertexNum);
        List<Integer> resultList = new LinkedList<Integer>();
        int[] visited = new int[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            visited[i] = -1;
        }
        findAPath0(sourceVertex, destinationVertex, sourceVertex, visited);
        //System.out.println(Arrays.toString(visited));
        if (visited[destinationVertex] != -1) {
            for (int currentVertex = destinationVertex;
                currentVertex != sourceVertex;
                currentVertex = visited[currentVertex]) {
                resultList.add(currentVertex);
            }
            resultList.add(sourceVertex);
            Collections.reverse(resultList);
        }
        return toIntArray(resultList);
    }
    private void findAPath0(
        int currentVertex, int destinationVertex, int parentVertex, int[] visited) {
        visited[currentVertex] = parentVertex;
        if (currentVertex == destinationVertex) {
            return;
        }
        for (int adjVertex : getAdjacentVertex(currentVertex)) {
            if (visited[adjVertex] == -1) {
                findAPath0(adjVertex, destinationVertex, currentVertex, visited);
            }
        }
    }
    @Override
    public boolean isConnected(int vertexA, int vertexB) {
        vaildateVertex(vertexA, vertexNum);
        vaildateVertex(vertexB, vertexNum);
        List<Integer> resultList = new LinkedList<Integer>();
        Boolean[] visited = new Boolean[vertexNum];
        setArray(visited, false);
        depthFirstSearch0(vertexA, visited, resultList);
        return resultList.contains(vertexB);
    }
    @Override
    public int[] depthFirstSearch() {
        List<Integer> resultList = new LinkedList<Integer>();
        Boolean[] visited = new Boolean[vertexNum];
        setArray(visited, false);
        for (int i = 0; i < vertexNum; i++) {
            if (!visited[i]) {
                //depthFirstSearch0(i, visited, resultList);
                depthFirstSearch1(i, visited, resultList);
            }
        }
        return toIntArray(resultList);
    }
    private void depthFirstSearch0(
        int vertex, Boolean[] visited, List<Integer> resultList) {
        visited[vertex] = true;
        resultList.add(vertex);
        for (int v : getAdjacentVertex(vertex)) {
            if (!visited[v]) {
                depthFirstSearch0(v, visited, resultList);
            }
        }
    }
    private void depthFirstSearch1(
        int vertex, Boolean[] visited, List<Integer> resultList) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(vertex);
        visited[vertex] = true;
        resultList.add(vertex);
        while (!stack.isEmpty()) {
            int currentVertex = stack.pop();
            for (int adjVertex : getAdjacentVertex(currentVertex)) {
                if (!visited[adjVertex]) {
                    stack.push(adjVertex);
                    visited[adjVertex] = true;
                    resultList.add(adjVertex);
                }
            }
        }
    }
    @Override
    public int[] breadthFirstSearch() {
        List<Integer> resultList = new LinkedList<Integer>();
        Boolean[] visited = new Boolean[vertexNum];
        setArray(visited, false);
        for (int i = 0; i < vertexNum; i++) {
            if (!visited[i]) {
                breadthFirstSearch0(i, visited, resultList);
            }
        }
        return toIntArray(resultList);
    }
    private void breadthFirstSearch0(
        int vertex, Boolean[] visited, List<Integer> resultList) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        visited[vertex] = true;
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            resultList.add(currentVertex);
            for (int adjVertex : getAdjacentVertex(currentVertex)) {
                if (!visited[adjVertex]) {
                    queue.offer(adjVertex);
                    visited[adjVertex] = true;
                }
            }
        }
    }
    private int hamiltonianPathEnd = -1;
    @Override
    public int[] hamiltonianCircuit() {
        Integer[] visited = new Integer[vertexNum];
        setArray(visited, -1);
        List<Integer> resultList = new ArrayList<>();
        // 从顶点 0 开始搜寻哈密尔顿回路
        if (hamiltonianCircuit0(0, 0, visited)) {
            int currentVertex = hamiltonianPathEnd;
            while (currentVertex != 0) {
                resultList.add(currentVertex);
                currentVertex = visited[currentVertex];
            }
            resultList.add(0);
            Collections.reverse(resultList);
        }
        return toIntArray(resultList);
    }
    private boolean hamiltonianCircuit0(
        int currentVertex, int parentVertex, Integer[] visited) {
        visited[currentVertex] = parentVertex;
        for (int adjVertex : getAdjacentVertex(currentVertex)) {
            if (visited[adjVertex] == -1) {
                if (hamiltonianCircuit0(adjVertex, currentVertex, visited)) {
                    return true;
                }
            } else {
                if (adjVertex == 0 && allVisited(visited)) {
                    hamiltonianPathEnd = currentVertex;
                    return true;
                }
            }
        }
        // 重置访问状态
        visited[currentVertex] = -1;
        return false;
    }
    @Override
    public int[] hamiltonianPath(int vertex) {
        vaildateVertex(vertex, vertexNum);
        Integer[] visited = new Integer[vertexNum];
        setArray(visited, -1);
        List<Integer> resultList = new ArrayList<>();
        // 从顶点 v 开始搜寻哈密尔顿路径
        if (hamiltonianPath0(vertex, vertex, visited)) {
            int currentVertex = hamiltonianPathEnd;
            while (currentVertex != vertex) {
                resultList.add(currentVertex);
                currentVertex = visited[currentVertex];
            }
            resultList.add(vertex);
            Collections.reverse(resultList);
        }
        return toIntArray(resultList);
    }
    private boolean hamiltonianPath0(
        int currentVertex, int parentVertex, Integer[] visited) {
        visited[currentVertex] = parentVertex;
        for (int adjVertex : getAdjacentVertex(currentVertex)) {
            if (visited[adjVertex] == -1) {
                if (hamiltonianPath0(adjVertex, currentVertex, visited)) {
                    return true;
                }
            } else {
                if (allVisited(visited)) {
                    hamiltonianPathEnd = currentVertex;
                    return true;
                }
            }
        }
        // 重置访问状态
        visited[currentVertex] = -1;
        return false;
    }
    private boolean allVisited(Integer[] visited) {
        for (int i = 0; i < vertexNum; i++) {
            if (visited[i] == -1) {
                return false;
            }
        }
        return true;
    }
    private static void vaildateVertex(int vertex, int vertexNum) {
        if (vertex < 0 || vertex >= vertexNum) {
            throw new IllegalArgumentException(
                "[ERROR]: vertex " + vertex + " is invalid!"
            );
        }
    }
    private static <T> void setArray(T[] arr, T val) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = val;
        }
    }
    private int[] toIntArray(List<Integer> list) {
        int[] resultArray = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            resultArray[i] = list.get(i);
        }
        return resultArray;
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
            for (Map.Entry<Integer, Integer> entry : adjacencyMap[i].entrySet()) {
                stringBuilder.append(String.format(
                    "{%d->%d=%d} ", i, entry.getKey(), entry.getValue()
                ));
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
    @SuppressWarnings("unchecked")
    @Override
    public Object clone() throws CloneNotSupportedException {
        WeightedAdjacencyList adj = new WeightedAdjacencyList();
        adj.vertexNum = this.vertexNum;
        adj.edgeNum = this.edgeNum;
        Map<Integer, Integer>[] adjacencyMapClone =
            (Map<Integer, Integer>[])new Map[adj.vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            adjacencyMapClone[i] = new TreeMap<Integer, Integer>();
            for (Map.Entry<Integer, Integer> entry : adjacencyMap[i].entrySet()) {
                adjacencyMapClone[i].put(entry.getKey(), entry.getValue());
            }
        }
        adj.adjacencyMap = adjacencyMapClone;
        return adj;
    }
}
/* EOF */
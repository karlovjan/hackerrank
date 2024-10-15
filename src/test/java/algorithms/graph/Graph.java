package algorithms.graph;

import java.util.*;

public class Graph<T> {
    private final Map<Vertex<T>, List<Vertex<T>>> vertexMap; //or an adjacency matrix

    public Graph() {
        this.vertexMap = new HashMap<>();
    }

    List<Vertex<T>> getAdjacencyVertices(T vertexData) {
        return vertexMap.get(new Vertex<>(vertexData));
    }

    void addVertex(T data) {
        vertexMap.putIfAbsent(new Vertex<>(data), new LinkedList<>()); //putIfAbsent solves duplications
    }

    void removeVertex(T data) {
        vertexMap.values().forEach(e -> e.remove(new Vertex<>(data)));
        vertexMap.remove(new Vertex<>(data));
    }

    void addEdge(T vData, T wData) {
        Vertex<T> v = new Vertex<>(vData);
        Vertex<T> w = new Vertex<>(wData);
        vertexMap.get(v).add(w);
        vertexMap.get(w).add(v);
    }

    void addDirectionalEdge(T vData, T wData) {
        Vertex<T> v = new Vertex<>(vData);
        Vertex<T> w = new Vertex<>(wData);
        vertexMap.get(v).add(w);
    }

    void addDirectionalEdge(T vData, T wData, int edgeWeight) {
        Vertex<T> v = new Vertex<>(vData);
        Vertex<T> w = new Vertex<>(wData, edgeWeight);
        vertexMap.get(v).add(w);
    }

    void removeEdge(T vData, T wData) {
        Vertex<T> v = new Vertex<>(vData);
        Vertex<T> w = new Vertex<>(wData);
        List<Vertex<T>> vEdges = vertexMap.get(v);
        List<Vertex<T>> wEdges = vertexMap.get(w);
        if (vEdges != null) {
            vEdges.remove(w);
        }
        if (wEdges != null) {
            wEdges.remove(v);
        }
    }

    void removeDirectionalEdge(T vData, T wData) {
        Vertex<T> v = new Vertex<>(vData);
        Vertex<T> w = new Vertex<>(wData);
        List<Vertex<T>> vEdges = vertexMap.get(v);
        if (vEdges != null) {
            vEdges.remove(w);
        }
    }

    Set<T> depthFirstTraversalStackImpl(T startVertexData) {
        Set<T> visited = new LinkedHashSet<>();
        Stack<T> stack = new Stack<>();
        stack.push(startVertexData);
        while (!stack.isEmpty()) {
            T vertexData = stack.pop();
            if (!visited.contains(vertexData)) {
                visited.add(vertexData);
                for (Vertex<T> vertex : getAdjacencyVertices(vertexData)) {
                    stack.push(vertex.getData());
                }
            }
        }
        return visited;
    }

    Set<T> depthFirstTraversalRecursionImpl(T startVertexData) {
        Set<T> visited = new LinkedHashSet<>();
        depthFirstTraversalRecursionHelper(startVertexData, visited);
        return visited;
    }

    void depthFirstTraversalRecursionHelper(T startVertexData, Set<T> visited) {
        visited.add(startVertexData);
        for (Vertex<T> vertex : getAdjacencyVertices(startVertexData)) {
            if (!visited.contains(vertex.getData())) {
                depthFirstTraversalRecursionHelper(vertex.getData(), visited);
            }
        }
    }

    Set<T> breadthFirstTraversal(T startVertexData) {
        Set<T> visited = new LinkedHashSet<>();
        Queue<T> queue = new LinkedList<>();
        queue.add(startVertexData);
        visited.add(startVertexData);
        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            for (Vertex<T> v : getAdjacencyVertices(vertex)) {
                if (!visited.contains(v.getData())) {
                    visited.add(v.getData());
                    queue.add(v.getData());
                }
            }
        }
        return visited;
    }

}

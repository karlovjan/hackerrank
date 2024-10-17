package algorithms.graph;

import java.util.*;

public class Graph<T extends Comparable<T>> {
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

    void addVertex(Vertex<T> vertex) {
        vertexMap.putIfAbsent(vertex, new LinkedList<>()); //putIfAbsent solves duplications
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

    void addEdge(T vData, T wData, int edgeWeight) {
        Vertex<T> v = new Vertex<>(vData, edgeWeight);
        Vertex<T> w = new Vertex<>(wData, edgeWeight);
        vertexMap.get(v).add(w);
        vertexMap.get(w).add(v);
    }

    void addEdge(Vertex<T> v, Vertex<T> w) {
        vertexMap.get(v).add(w);
        vertexMap.get(w).add(v);
    }

    void addDirectionalEdge(T vData, T wData) {
        Vertex<T> v = new Vertex<>(vData);
        Vertex<T> w = new Vertex<>(wData);
        vertexMap.get(v).add(w);
    }

    void addDirectionalEdge(Vertex<T> v, Vertex<T> w) {
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

    Map<T, Integer> dijkstras(T startVertexData) {
        Map<T, Integer> distances = HashMap.newHashMap(vertexMap.size());
        Set<T> visited = HashSet.newHashSet(vertexMap.size());
        PriorityQueue<Vertex<T>> priorityQueue = new PriorityQueue<>();

        //initialize the distance map with the default distance is set to null ( or Infinity = Integer.MAX_VALUE)
        for (Vertex<T> v : vertexMap.keySet()) {
            distances.put(v.getData(), null);
        }
        Vertex<T> startVertex = new Vertex<>(startVertexData, 0);
        priorityQueue.add(startVertex);
        distances.put(startVertex.getData(), startVertex.getWeight());

        while (!priorityQueue.isEmpty() && visited.size() != vertexMap.size()) {

            Vertex<T> vertex = priorityQueue.poll();

            if (!visited.contains(vertex.getData())) {
                visited.add(vertex.getData());
                distances.put(vertex.getData(), vertex.getWeight());

                for (Vertex<T> w : getAdjacencyVertices(vertex.getData())) {
                    if (!visited.contains(w.getData())) {
                        priorityQueue.add(new Vertex<>(w.getData(), vertex.getWeight() + w.getWeight()));
                    }
                }
            }
        }

        return distances;
    }
}

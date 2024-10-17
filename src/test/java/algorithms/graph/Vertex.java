package algorithms.graph;

import java.util.Objects;

public class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>> {
    private final T data;
    private final Integer weight;

    public Vertex(T data) {
        this(data, 1);
    }

    public Vertex(T data, Integer weight) {
        this.data = data;
        this.weight = weight;
    }

    public T getData() {
        return data;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals(data, vertex.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data);
    }

    @Override
    public int compareTo(Vertex<T> vertex) {
        int result = this.weight.compareTo(vertex.getWeight());
        if (result == 0) {
            result = this.data.compareTo(vertex.getData());
        }
        return result;
    }
}

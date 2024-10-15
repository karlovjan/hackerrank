package algorithms.graph;

import java.util.Objects;

public class Vertex<T> {
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
}

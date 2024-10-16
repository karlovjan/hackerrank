package algorithms.graph;

import java.util.Comparator;
import java.util.Objects;

public class Vertex<T> implements Comparator<Vertex<T>> {
    private final T data;
    private Integer weight;

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

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int compare(Vertex<T> vertex1, Vertex<T> vertex2) {
        return vertex1.getWeight().compareTo(vertex2.getWeight());
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

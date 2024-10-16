package algorithms.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DijkstrasTest {

    @Test
    void dijkstrasTest1() {
        var graph = new Graph<String>();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", "Alice", 1);
        graph.addEdge("Bob", "Rob", 2);
        graph.addEdge("Alice", "Mark", 3);
        graph.addEdge("Rob", "Mark", 4);
        graph.addEdge("Alice", "Maria", 5);
        graph.addEdge("Rob", "Maria", 6);

        /*
                                                         Bob
                                                   /             \
                                               Alice -- Maria -- Rob
                                                   \             /
                                                        Mark
         */

        assertEquals("[Bob, Alice, Mark, Rob, Maria]", graph.dijkstras("Bob").toString());
    }

    @Test
    void dijkstrasTest2() {
        var graph = new Graph<Integer>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(2, 1);
        graph.addEdge(2, 7);
        graph.addEdge(3, 1);
        graph.addEdge(4, 1);
        graph.addEdge(4, 6);
        graph.addEdge(5, 1);
        graph.addEdge(5, 7);
        graph.addEdge(6, 4);
        graph.addEdge(6, 7);
        graph.addEdge(7, 2);
        graph.addEdge(7, 5);
        graph.addEdge(7, 6);
        graph.addEdge(7, 8);
        graph.addEdge(8, 7);


        assertEquals("[1, 2, 7, 5, 6, 4, 8, 3]", graph.depthFirstTraversalRecursionImpl(1).toString());
    }



}

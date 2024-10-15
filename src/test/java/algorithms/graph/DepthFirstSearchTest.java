package algorithms.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepthFirstSearchTest {


    @Test
    void depthFirstSearchByStack() {
        var graph = new Graph<String>();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "Rob");
        graph.addEdge("Alice", "Mark");
        graph.addEdge("Rob", "Mark");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");

        assertEquals("[Bob, Rob, Maria, Alice, Mark]", graph.depthFirstTraversalStackImpl("Bob").toString());
    }

    @Test
    void depthFirstSearchByRecursion() {
        var graph = new Graph<String>();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "Rob");
        graph.addEdge("Alice", "Mark");
        graph.addEdge("Rob", "Mark");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");

        /*
                                                         Bob
                                                   /             \
                                               Alice -- Maria -- Rob
                                                   \             /
                                                        Mark
         */

        assertEquals("[Bob, Alice, Mark, Rob, Maria]", graph.depthFirstTraversalRecursionImpl("Bob").toString());
    }

    @Test
    void depthFirstSearchByRecursion2() {
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

    @Test
    void breadthFirstTraversalTest() {
        var graph = new Graph<String>();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "Rob");
        graph.addEdge("Alice", "Mark");
        graph.addEdge("Rob", "Mark");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");

        assertEquals(
                "[Bob, Alice, Rob, Mark, Maria]", graph.breadthFirstTraversal("Bob").toString());
    }


}

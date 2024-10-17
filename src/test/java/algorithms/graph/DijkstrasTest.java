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

        assertEquals("{Mark=4, Bob=0, Rob=2, Maria=6, Alice=1}", graph.dijkstras("Bob").toString());
    }

    @Test
    void dijkstrasDisjunkTest1() {
        var graph = new Graph<String>();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addDirectionalEdge("Bob", "Alice", 1);
        graph.addDirectionalEdge("Alice", "Mark", 3);
        graph.addDirectionalEdge("Rob", "Maria", 6);

        /*
                                                         Bob
                                                   /
                                               Alice            Rob -- Maria
                                                   \
                                                        Mark
         */

        assertEquals("{Mark=4, Bob=0, Rob=null, Maria=null, Alice=1}", graph.dijkstras("Bob").toString());
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
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 4);
        graph.addEdge(1, 5, 5);
        graph.addEdge(2, 1, 10);
        graph.addEdge(2, 7, 7);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 1, 4);
        graph.addEdge(4, 6, 6);
        graph.addEdge(5, 1, 5);
        graph.addEdge(5, 7, 2);
        graph.addEdge(6, 4, 6);
        graph.addEdge(6, 7, 7);
        graph.addEdge(7, 2, 7);
        graph.addEdge(7, 5, 2);
        graph.addEdge(7, 6, 7);
        graph.addEdge(7, 8, 8);
        graph.addEdge(8, 7, 8);


        assertEquals("{1=0, 2=10, 3=1, 4=4, 5=5, 6=10, 7=7, 8=15}", graph.dijkstras(1).toString());
        assertEquals("{1=1, 2=11, 3=0, 4=5, 5=6, 6=11, 7=8, 8=16}", graph.dijkstras(3).toString());
        assertEquals("{1=15, 2=15, 3=16, 4=19, 5=10, 6=15, 7=8, 8=0}", graph.dijkstras(8).toString());
    }


}

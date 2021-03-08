package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class UtilTest {

	@Test
	void intArrayToStringTestClasic() {
		int[] testInts = { 1, 2, 2, 2, 2, 2, 2, 2, 22, 3, 2, 22, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
				3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
				3 };
		String expected = "[1, 2, 2, 2, 2, 2, 2, 2, 22, 3, 2, 22, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3]";

		assertEquals(expected, Arrays.toString(testInts));
	}

	@Test
	void intArrayToStringTestStream() {
		int[] testInts = { 1, 2, 2, 2, 2, 2, 2, 2, 22, 3, 2, 22, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
				3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
				3 };
		String expected = "[1,2,2,2,2,2,2,2,22,3,2,22,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3]";

		assertEquals(expected, intArrayToString(testInts));
	}

	public static String intArrayToString(int[] array) {
		return Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(",", "[", "]"));
	}

	/**
	 * PracticeData Structures->Trees->Tree: Huffman Decoding
Tree: Huffman Decoding
	 https://www.hackerrank.com/challenges/tree-huffman-decoding/problem
	 https://en.wikipedia.org/wiki/Huffman_coding
	 */
	@Nested
	@DisplayName("HuffmanDecodingTestClass")
	class HuffmanDecodingTestClass {

		abstract class Node implements Comparable<Node> {
			public  int frequency; // the frequency of this tree
			public  char data;
			public  Node left, right;
			public Node(int freq) {
				frequency = freq;
			}

			// compares on the frequency
			public int compareTo(Node tree) {
				return frequency - tree.frequency;
			}
		}

		class HuffmanLeaf extends Node {


			public HuffmanLeaf(int freq, char val) {
				super(freq);
				data = val;
			}
		}

		class HuffmanNode extends Node {

			public HuffmanNode(Node l, Node r) {
				super(l.frequency + r.frequency);
				left = l;
				right = r;
			}

		}


		class Decoding {

/*
	class Node
		public  int frequency; // the frequency of this tree
    	public  char data;
    	public  Node left, right;

*/

			String decode(String encodedString, Node root) {


				StringBuilder decodedString = new StringBuilder();

				char[] keys = encodedString.toCharArray();

				Node nodeTmp = root;

				for (char edgeValue : keys) {

					// 0 or 1 ?
					if (edgeValue == '0') {
						nodeTmp = nodeTmp.left;

					} else {
						nodeTmp = nodeTmp.right;
					}

					/*
					The default value of a char attribute is indeed '\u0000' (the null character)
					as stated in the Java Language Specification, section §4.12.5 Initial Values of Variables
					 */
					if (nodeTmp.data != '\u0000') {
						//you found the leaf node
						//stop walking throughout the tree
						//System.out.print(nodeTmp.data);
						decodedString.append(nodeTmp.data);

						nodeTmp = root;
					}
				}

				return decodedString.toString();

			}



		}

		@Test
		void decode_test1() {

			HuffmanLeaf leafC = new HuffmanLeaf(1, 'C');
			HuffmanLeaf leafD = new HuffmanLeaf(1, 'D');

			HuffmanNode node_fi2 = new HuffmanNode(leafC, leafD);
			HuffmanLeaf leafB = new HuffmanLeaf(2, 'B');
			HuffmanNode node_fi4 = new HuffmanNode(node_fi2, leafB);

			HuffmanLeaf leafR = new HuffmanLeaf(2, 'R');
			HuffmanNode node_fi6 = new HuffmanNode(leafR, node_fi4);

			HuffmanLeaf leafA = new HuffmanLeaf(5, 'A');
			HuffmanNode node_fi11 = new HuffmanNode(leafA, node_fi6);

			String result = new Decoding().decode("01111001100011010111100", node_fi11);
			assertEquals("ABRACADABRA", result);
		}

		@Test
		void decode_test2() {

			HuffmanLeaf leafB = new HuffmanLeaf(1, 'B');
			HuffmanLeaf leafC = new HuffmanLeaf(1, 'C');

			HuffmanNode node_fi2 = new HuffmanNode(leafB, leafC);

			HuffmanLeaf leafA = new HuffmanLeaf(3, 'A');

			HuffmanNode node_fi5 = new HuffmanNode(node_fi2, leafA);

			String result = new Decoding().decode("1001011", node_fi5);
			assertEquals("ABACA", result);
		}
	}

	@Test
	void generateNumbersTest() {
		assertIterableEquals(List.of(0,1,2,3,4,5,6,7,8,9,10), StreamUtil.generateNumbersJava8(0, 10, 1, 1).collect(Collectors.toUnmodifiableList()));
		assertIterableEquals(List.of(0,1,2,3,4,5,6,7,8,9,10), StreamUtil.generateNumbersJava9(0, 10, 1, 1).collect(Collectors.toUnmodifiableList()));

		assertIterableEquals(List.of(10,20,30), StreamUtil.generateNumbersJava8(10, 30, 10, 1).collect(Collectors.toUnmodifiableList()));
		assertIterableEquals(List.of(10,20,30), StreamUtil.generateNumbersJava8(1, 30, 1, 10).collect(Collectors.toUnmodifiableList()));
		assertIterableEquals(List.of(10,20,30), StreamUtil.generateNumbersJava9(10, 30, 1, 10).collect(Collectors.toUnmodifiableList()));

		assertIterableEquals(List.of(10,30,50), StreamUtil.generateNumbersJava8(10, 50, 20, 1).collect(Collectors.toUnmodifiableList()));
		assertIterableEquals(List.of(10,30,50), StreamUtil.generateNumbersJava9(10, 50, 2, 10).collect(Collectors.toUnmodifiableList()));
		assertIterableEquals(List.of(10,30,50), StreamUtil.generateNumbersJava9(10, 50, 20, 1).collect(Collectors.toUnmodifiableList()));

		assertIterableEquals(List.of(0), StreamUtil.generateNumbersJava8(0, 0, 1, 1).collect(Collectors.toUnmodifiableList()));
		assertIterableEquals(List.of(1), StreamUtil.generateNumbersJava8(1, 1, 1, 1).collect(Collectors.toUnmodifiableList()));
		assertIterableEquals(List.of(0), StreamUtil.generateNumbersJava9(0, 0, 1, 1).collect(Collectors.toUnmodifiableList()));
		assertIterableEquals(List.of(1), StreamUtil.generateNumbersJava9(1, 1, 1, 1).collect(Collectors.toUnmodifiableList()));

	}
}

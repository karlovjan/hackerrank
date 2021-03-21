package util;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class StreamUtil {

    private StreamUtil() {
    }

    public static Stream<Integer> generateNumbersJava9(int from, int to, int increment, int multiplier) {
        return Stream.iterate(from, n -> n <= to, i -> ((i / multiplier) + increment) * multiplier);
    }

    public static Stream<Integer> generateNumbersJava8(int from, int to, int increment, int multiplier) {
        Stream.Builder<Integer> builder = Stream.builder();

        int i = from;
        int result;
        while ((result = i * multiplier) <= to) {
            builder.accept(result);
            i += increment;
        }

        return builder.build();
    }

    public static <T> Stream<T> filterStreamByIndex(Stream<T> stream, int skipedIndex) {
        AtomicInteger billIndex = new AtomicInteger();
        return stream.filter(j -> billIndex.getAndIncrement() != skipedIndex);
    }

    public static <T> Stream<T> filterStreamByIndexes(Stream<T> stream, List<Integer> skipedIndexes) {
        AtomicInteger billIndex = new AtomicInteger();
        return stream.filter(j -> !skipedIndexes.contains(billIndex.getAndIncrement()));
    }
}

package util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    public static Map<Integer, Integer> groupByAndSumInt(int[] ar) {
        return Arrays.stream(ar).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(Integer::intValue)));
    }

    public static Map<Integer, Long> groupByAndCountInt(int[] ar) {
        return Arrays.stream(ar).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static IntStream mergetIntArrays(int[] a1, int[] a2, BiFunction<Integer, Integer, Integer> mergeFnc){
        return Arrays.stream(a1).flatMap(item1 -> Arrays.stream(a2).map(item2 -> mergeFnc.apply(item1, item2)));
    }
}

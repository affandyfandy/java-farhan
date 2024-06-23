package Assignment7.utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataUtils {
    // Element List Duplicate
    public static <T> List<T> removeDuplicates(Collection<T> collection) {
        return collection.stream().distinct().collect(Collectors.toList());
    }

    // Field Object Duplicate
    public static <T, K> List<T> removeDuplicatesByField(List<T> list, Function<T, K> keyExtractor) {
        Set<K> seenKeys = new HashSet<>();
        return list.stream()
                .filter(e -> seenKeys.add(keyExtractor.apply(e))) // used to track the keys that have already been
                // encountered.
                .collect(Collectors.toList());
    }

    public static <T, U extends Comparable<U>> List<T> sortByField(List<T> list,
            Function<T, U> keyExtractor) {
        return list.stream()
                .sorted(Comparator.comparing(keyExtractor))
                .collect(Collectors.toList());
    }

    public static <T, U extends Comparable<U>> Optional<T> findMaxByField(List<T> list,
            Function<T, U> keyExtractor) {
        return list.stream()
                .max(Comparator.comparing(keyExtractor));
    }

    public static <T, K> Map<K, T> convertListToMap(List<T> list, Function<T, K> keyExtractor) {
        return list.stream()
                .filter(e -> keyExtractor.apply(e) != null) // Exclude elements with null keys
                .collect(Collectors.toMap(
                        keyExtractor,
                        Function.identity(),
                        (existing, replacement) -> replacement // Replace existing with new in case of duplicates
                ));
    }
}

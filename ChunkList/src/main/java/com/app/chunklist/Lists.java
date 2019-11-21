package com.app.chunklist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class Lists {

    private Lists() {
        throw new AssertionError();
    }

    public static <T> Collection<List<T>> toChunkV1(List<T> list, int chunkSize) {

        if (list == null || list.size() < 2) {
            throw new IllegalArgumentException("The list must have at least 1 element");
        }

        if (chunkSize < 1) {
            throw new IllegalArgumentException("Chunk size should be minimum 1");
        }

        AtomicInteger counter = new AtomicInteger();
        Collection<List<T>> result = list.stream()
                .collect(Collectors.groupingBy(c -> counter.getAndIncrement() / chunkSize))
                .values();

        return result;
    }

    public static <T> Collection<List<T>> toChunkV2(List<T> list, int chunkSize) {

        if (list == null || list.size() < 2) {
            throw new IllegalArgumentException("The list must have at least 1 element");
        }

        if (chunkSize < 1) {
            throw new IllegalArgumentException("Chunk size should be minimum 1");
        }

        AtomicInteger counter = new AtomicInteger();
        Collection<List<T>> result = list.parallelStream()
                .collect(Collectors.groupingBy(c -> counter.getAndIncrement() / chunkSize))
                .values();

        return result;
    }

    public static <T> List<List<T>> toChunkV3(List<T> list, int chunkSize) {

        if (list == null || list.size() < 2) {
            throw new IllegalArgumentException("The list must have at least 1 element");
        }

        if (chunkSize < 1) {
            throw new IllegalArgumentException("Chunk size should be minimum 1");
        }

        AtomicInteger counter = new AtomicInteger();
        List<List<T>> result = new ArrayList<>();
        for (T item : list) {
            if (counter.getAndIncrement() % chunkSize == 0) {
                result.add(new ArrayList<>());
            }
            result.get(result.size() - 1).add(item);
        }

        return result;
    }

    public static <T> List<List<T>> toChunkV4(List<T> list, int chunkSize) {

        if (list == null || list.size() < 2) {
            throw new IllegalArgumentException("The list must have at least 1 element");
        }

        if (chunkSize < 1) {
            throw new IllegalArgumentException("Chunk size should be minimum 1");
        }

        List<List<T>> result = list.stream()
                .collect(chunkIt(chunkSize));

        return result;
    }

    public static <T> List<List<T>> toChunkV5(List<T> list, int chunkSize) {

        if (list == null || list.size() < 2) {
            throw new IllegalArgumentException("The list must have at least 1 element");
        }

        if (chunkSize < 1) {
            throw new IllegalArgumentException("Chunk size should be minimum 1");
        }

        List<List<T>> result = list.parallelStream()
                .collect(chunkIt(chunkSize));

        return result;
    }

    public static <T> List<List<T>> toChunkV6(List<T> list, int chunkSize) {

        List<List<T>> result = Chunk.ofSize(list, chunkSize);

        return result;
    }

    private static <T> Collector<T, List<T>, List<List<T>>> chunkIt(int chunkSize) {
        return Collector.of(ArrayList::new, List::add, (x, y) -> {
            x.addAll(y);
            return x;
        }, x -> Chunk.ofSize(x, chunkSize), Collector.Characteristics.UNORDERED
        );
    }
}

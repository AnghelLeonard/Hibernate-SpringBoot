package com.app.chunklist;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public final class Chunk<T> extends AbstractList<List<T>> {

    private final List<T> list;
    private final int chunkSize;

    public Chunk(List<T> list, int chunkSize) {
        this.list = new ArrayList<>(list);
        this.chunkSize = chunkSize;
    }

    public static <T> Chunk<T> ofSize(List<T> list, int chunkSize) {                
        return new Chunk<>(list, chunkSize);
    }

    @Override
    public List<T> get(int index) {
        int start = index * chunkSize;
        int end = Math.min(start + chunkSize, list.size());

        if (start > end) {
            throw new IndexOutOfBoundsException(
                    "Index should range: 0, " + (size() - 1));
        }

        return new ArrayList<>(list.subList(start, end));
    }

    @Override
    public int size() {
        return (int) Math.ceil((double) list.size() / (double) chunkSize);
    }
}

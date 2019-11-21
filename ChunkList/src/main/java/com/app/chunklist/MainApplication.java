package com.app.chunklist;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainApplication {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 1_000_000; i++) {
            list.add(i);
        }

        long startTimeV1 = System.nanoTime();
        Lists.toChunkV1(list, 5);//.forEach(System.out::println);
        displayExecutionTime(System.nanoTime() - startTimeV1);

        long startTimeV2 = System.nanoTime();
        Lists.toChunkV2(list, 5);//.forEach(System.out::println);
        displayExecutionTime(System.nanoTime() - startTimeV2);

        long startTimeV3 = System.nanoTime();
        Lists.toChunkV3(list, 5);//.forEach(System.out::println);
        displayExecutionTime(System.nanoTime() - startTimeV3);

        long startTimeV4 = System.nanoTime();
        Lists.toChunkV4(list, 5);//.forEach(System.out::println);
        displayExecutionTime(System.nanoTime() - startTimeV4);

        long startTimeV5 = System.nanoTime();
        Lists.toChunkV5(list, 5);//.forEach(System.out::println);
        displayExecutionTime(System.nanoTime() - startTimeV5);

        long startTimeV6 = System.nanoTime();
        Lists.toChunkV6(list, 5);//.forEach(System.out::println);
        displayExecutionTime(System.nanoTime() - startTimeV6);
    }

    private static void displayExecutionTime(long time) {
        System.out.println("Execution time: " + time + " ns" + " ("
                + TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS) + " ms)");
    }
}

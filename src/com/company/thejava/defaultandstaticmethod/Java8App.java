package com.company.thejava.defaultandstaticmethod;

import java.util.*;
import java.util.stream.Collectors;

public class Java8App {
    public static void main(String[] args) {
        List<String> name = new ArrayList<>();

        name.add("yeseul");
        name.add("seulshine");
        name.add("jackpot");
        name.add("Foo");

        name.forEach(s -> {
            System.out.println(s);
        });

        name.forEach(System.out::println);

        /**
         * 병렬처리할 떄 유용하다!! spliterator, Stream의 기반이 됨!
         */
        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator1 =  spliterator.trySplit();
        while (spliterator.tryAdvance(System.out::println));
        System.out.println("==================");
        while (spliterator1.tryAdvance(System.out::println));

        name.stream().map(String::toUpperCase)
                    .filter(s -> s.startsWith("Y"))
                    .collect(Collectors.toSet());

        name.removeIf(s -> s.startsWith("y"));
        name.forEach(System.out::println);

        /**
         *  Comparator 에도 함수형 인터페이스 쓸 수 있다!!
         */
        name.sort(String::compareToIgnoreCase);
    }


}

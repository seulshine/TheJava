package com.company.thejava.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("yeseul");
        names.add("jackpot");
        names.add("choi");
        names.add("foo");

        /**
         * stream이란 어떤 연속된 데이터를 처리하는 오퍼레이션의 모음!
         * stream은 데이터를 바꾸지 않는다!!
         * stream은 오직 한번만 처리된다! (데이터가 무제한일 땐 Short Circuit 메소드 사용!)
         */

        Stream<String> string = names.stream()
                .map(String::toUpperCase); // map 은 중계 오퍼레이션인데 중계형 오퍼레이터는 기본적으로 lazy 하다.
        names.forEach(System.out::println);

        System.out.println("==================");

        /**
         * 스트림 파이프라인 :
         * 0 ~ 다수의 중계 오퍼레이션과 1개의 종료 오퍼레이션으로 구성된다.
         * 스트림의 데이터 소스는 오직 터미널 오퍼레이션을 실행할 때에만 처리한다.
         */
        names.stream().map((s) -> {
            System.out.println(s); // 출력이 되지 않는다!! 근본적으로 중계형 오퍼레이터들은 터미널 오퍼레이터가 오기 전까지 실행을 안한다!!
                                    // 그냥 정의를 한거다! 종료형 오퍼레이터가 실행되기 전까지는 중계형 오퍼레이터는 무의미하다! (lazy 한 모습)
            return s.toUpperCase();
        });

        for(String name : names) {
            if (name.startsWith("y")) { // 병렬적으로 처리하기 어렵다.
                System.out.println(name.toUpperCase());
            }
        }

        /**
         * parallelStream 을 쓰면 JVM이 알아서 spliterator 사용해서 병렬적으로 처리한다!!
         * parallelStream 쓴다고 해서 더 빨라지는 건 아니다.. 더 느려질 수 있다!! 스레드를 만들어서 처리하고 수집해야 하는 비용이 들기 때문에!
         * 이게 유용할 땐 데이터가 정말 방대하게 큰 경우! (직접 비교해봐야 안다.)
         */
        List<String> collect = names.parallelStream().map((s) -> { // parallelStream -> stream 으로 바꾸면 같은 main 스레드에서 실행됨!
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());

        collect.forEach(System.out::println);
    }

}

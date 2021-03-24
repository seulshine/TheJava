package com.company.thejava.function;

import java.util.function.*;

public class Foo {

    public static void main(String[] args) {


        // 익명 클래스!
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };

        // 위의 익명 클래스를 람다 형식으로 바꿈!
        RunSomething runSomething2 = () -> System.out.println("Hello2");


        RunSomething runSomething3 = () -> {
            System.out.println("Hello3");
            System.out.println("Lambda");
        };



        Function<Integer, Integer> plus10 = (i) -> i+10;

	    Function<Integer, Integer> multiply2 = (i) -> i*2;

	    Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2); // 10을 더하기 전에 먼저 2를 곱하겠다!

        BiFunction<Integer, Integer, Integer> plusAB = (a,b) -> a+b;

        System.out.println(multiply2AndPlus10.apply(2));

        System.out.println(plus10.andThen(multiply2).apply(2)); // 먼저 plus10 하고 뒤에 multiply2 한다

        Consumer<Integer> printT = (i) -> System.out.println(i); // Consumer 는 return 값이 없는 함수다. T타입을 받아서 아무것도 리턴하지 않는 메서드
        printT.accept(10);

        Supplier<Integer> get10 = () -> 10; // Supplier 는 인자값이 업삳!! T타입의 값을 제공하는 함수 인터페이스
        System.out.println(get10);

        Predicate<String> startsWithYeseul = (s) -> s.startsWith("yeseul"); // 어떤 인자값을 받아서 True/False를 리턴해주는 함수형 인터페이스
        Predicate<Integer> isEven = (i) -> i%2 == 0;

        // Predicate 는 어떻게 사용?? -> True/False 라서 .or/.and OR 연산 AND 연산 이용!


        UnaryOperator<Integer> unaryPlus10 = (i) -> i+10; // Function 의 특수한 케이스 (입력값과 리턴값이 같은 경우)
        BinaryOperator<Integer>  biPlusAB = (a, b) -> a+b; // BiFunction 의 특수한 케이스 입력값 2개 리턴값 1개 모두 같은 경우
    }
}

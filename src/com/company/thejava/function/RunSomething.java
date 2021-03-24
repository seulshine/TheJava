package com.company.thejava.function;

@FunctionalInterface
public interface RunSomething {

    void doIt(); // 추상 메서드가 1개만 있기에 함수형 인터페이스이다.

    // void doAgain(); // @FunctionalInterface에 추상 메서드 2개면 에러가 남!!
    // static, default 있더라도 추상 메서드는 1개이기 때문에 함수형 인터페이스!
    static void printName() {
        System.out.println("Yeseul");
    }

    default void printAge() {
        System.out.println("26");
    }


}

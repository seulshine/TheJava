package com.company.thejava.defaultandstaticmethod;

public interface Foo {

    void printName();

    /**
     * @impleSpec
     * 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() { // abstract void만 하면 구현한 클래스들 다 에러난다!!
        System.out.println(getName().toUpperCase());
    }

    /**
     * default 메소드를 제공할 수 없는 메소드가 있다. -> Object의 default 메소드 toString()...등
     */
    // default String toString();

    /**
     * 유틸리티나 헬퍼 메서드를 제공할 때 사용한다!
     */
    static void printAnything() {
        System.out.println("Foo");
    }

    String getName();
}

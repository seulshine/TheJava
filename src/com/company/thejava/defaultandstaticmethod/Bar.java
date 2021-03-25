package com.company.thejava.defaultandstaticmethod;

public interface Bar extends Foo {
    /**
     * default 메소드를 그 방식으로 사용하고 싶지 않을 때 추상 메서드로 재정의 하면 된다!
     * 대신 Bar를 구현하는 클래스들은 다시 재정의 해야 한다!
     */
    void printNameUpperCase();

}

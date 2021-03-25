package com.company.thejava.defaultandstaticmethod;

public class DefaultFoo implements Foo {

    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println("yeseul");
    }

    /**
     * 재정의도 가능하다!
     * @return
     */
    @Override
    public void printNameUpperCase() {
        System.out.println(this.name.toUpperCase());
    }


    @Override
    public String getName() {
        return this.name;
    }
}

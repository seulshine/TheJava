package com.company.myJava;

import java.util.Objects;

public class LazyInitializationSingleton {

    /**
     * 3. 늦게 초기화 함! 필드에 만들 때 초기화 하지 않고 getInstance() 호출할 때 초기화!
     * => Eager initialization의 단점을 보완
     * 단점 : Thread safe 하지 않는다!
     * : Singleton 처럼 동작하지 않고 수많은 instance가 생기게 된다.
     *
     */
    private static LazyInitializationSingleton instance;

    private LazyInitializationSingleton() {}

    public static LazyInitializationSingleton getInstance() {
        if(Objects.isNull((instance))) {
            instance = new LazyInitializationSingleton();
        }

        return instance;
    }
}

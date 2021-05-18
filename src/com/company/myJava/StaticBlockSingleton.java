package com.company.myJava;

public class StaticBlockSingleton {

    /**
     * 2. Static Block Initialization
     * 예외를 처리할 수 있는 장점이 있지만,
     * 여전히 처음 시작할 때 바로 초기화 되어 메모리에 올라간다는 단점이 있다.
     */
    private static StaticBlockSingleton instance;

    private StaticBlockSingleton() {}

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException("싱글턴 객체 생성 오류");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }

}

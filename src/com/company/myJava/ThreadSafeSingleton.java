package com.company.myJava;

public class ThreadSafeSingleton {

    /**
     * 4. 스레드가 1개가 접근하게 되면 나머지 스레드들은 대기를 하게 됨!
     * 그래서 하나의 instance만 생성됨!
     * 단점 : 느림 ! 성능 저하를 야기하는 비효율적인 방법
     */
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {}

    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }

        return instance;
    }
}

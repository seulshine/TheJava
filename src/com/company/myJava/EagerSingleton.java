package com.company.myJava;

public class EagerSingleton {

    /**
     * 1. Eager initialization (이른 초기화)
     * 필드의 자기 instance 를 바로 초기화!
     * 장점 : 바로 초기화가 돼서 엄청 빠름
     * 단점 :
     * 1. 얘를 안쓰더라도 static 을 로딩하면서 메모리를 가져가 버림
     * 2. 예외 처리를 할 수 있는 방법이 없음
     */
    // 즉시 초기화
    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {

    }

    private static EagerSingleton getInstance() {
        return instance;
    }





}

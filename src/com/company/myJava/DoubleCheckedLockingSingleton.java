package com.company.myJava;

import java.util.Objects;

public class DoubleCheckedLockingSingleton {

    /**
     * 5. NULL 체크를 synchronized 블록 밖에서 한번, 안에서 한번 총 두번 실행
     * 밖에서 하는 체크는 인스턴스가 있는 경우 빠르게 리턴하기 위해서
     * 한쪽에서 하는 체크는 인스턴스가 생성되지 않은 경우 하나의 인스턴스만 생성하기 위해
     */
    private static DoubleCheckedLockingSingleton instance = null;

    private DoubleCheckedLockingSingleton() {

    }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (Objects.isNull(instance)) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}

package com.company.myJava;

public class BillPughSingleton {

    /**
     * 6. Double Checked에 비해 구현이 간단
     * Lazy 로딩이 가능
     * Thread safe
     */
    private BillPughSingleton() {
    }

    private static class SingletonHelper {
        /**
         * final로 인해서 처음 호출 이후 재할당되지 않음!!
         *
         */
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    private static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

}

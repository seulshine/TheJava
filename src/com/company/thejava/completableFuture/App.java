package com.company.thejava.completableFuture;

public class App {

    public static void main(String[] args) throws InterruptedException {

//        MyThread myThread = new MyThread();
//        myThread.start();

        Thread thread = new Thread(() -> {
            while(true) {
                System.out.println("Thread: " + Thread.currentThread().getName());

                try {
                    Thread.sleep(3000l); // 다른 스레드가 먼저 일을 하게 됨!!
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
//                    System.out.println("exit!");
//                    e.printStackTrace();
//                    return;
                }
            }

//            System.out.println("Thread: " + Thread.currentThread().getName());

        });
        thread.start();

        System.out.println("Hello : " + Thread.currentThread().getName());
        try {
            thread.join(); // thread 가 끝날 때 까지 기다린다!!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread + " is finished");
//        Thread.sleep(3000l);
//        thread.interrupt();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }
}

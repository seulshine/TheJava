package com.company.thejava.completableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                System.out.println("Thread: " + Thread.currentThread().getName());
            }
        });

        executorService.submit(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        });
        executorService.shutdown(); // 작업 처리한 다음 종료하겠다!! graceful shutdown!! 끝까지 마치고 죽는다!!
        // executorService.shutdownNow(); // 바로 지금 죽인다!! 현재 진행되고 있는 스레드가 다 끝나고 죽을지 아닐지 보장 못함!


        ScheduledExecutorService executorService1 = Executors.newSingleThreadScheduledExecutor();
//        executorService1.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);
        executorService1.scheduleAtFixedRate(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS);

//        executorService1.shutdown();
    }

    /**
     * Future 스포..
     * 별도의 스레드에서 작업을 실행했는데 결과를 가져오고 싶다면
     * -> Callable 을 사용해야 함!
     * -> Runnable 이랑 같은데 차이가 있다면 리턴할 수 있다!!
     * -> 리턴해오는 무언가가 Future
     */
    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());

    }
}

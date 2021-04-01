package com.company.thejava.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableApp {
    public static void main(String[] args) throws Exception {

//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 스레드 4개 사용!!
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };

        Callable<String> yeseul = () -> {
            Thread.sleep(1000L);
            return "Yeseul";
        };

        /**
         * invokeAll 은 다 끝날때까지 다 기다린다!!
         * yeseul과 hello 가 다 끝났어도 java가 다 끝날 때까지 기다린다!
         *
         */
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, yeseul));

        for (Future<String> f : futures) {
            System.out.println(f.get());
        }

        /**
         *  invokeAny 는 이 중 하나라도 가져오는 즉시 끝난다!
         *  그래서 서버에서 복제용으로 서버1 서버2 서버3 에 같은 파일이 있을 때
         *  서버 3개 중 1개만 가져오면 되니깐!
         */
        String s = executorService.invokeAny(Arrays.asList(hello, java, yeseul));
        System.out.println(s);

        Future<String> helloFuture = executorService.submit(hello);

        System.out.println(helloFuture.isDone());
        // get 이전까지는 안기다리고 계속 실행됨
        System.out.println("Started!");

        /**
         * cancel 할 수가 있는데
         * true 는 현재 진행 중인 작업일 interrupt 해서 취소
         * false 는 기다렸다가 취소 그치만 cancel했기에 get해서 값을 가져올 수 없다.
         * 
         * cancel 하면 isDone은 true 어찌됐든 끝났기 때문에
         */
        helloFuture.cancel(false);
        helloFuture.get(); // 블록킹!

        System.out.println(helloFuture.isDone());

        // get을 만난 순간 멈춤!
        // Started는 빨리 출력됐지만 End는 2초 뒤에 (get한 뒤에) 출력됐다!!
        System.out.println("End!!");
        executorService.shutdown();


    }
}

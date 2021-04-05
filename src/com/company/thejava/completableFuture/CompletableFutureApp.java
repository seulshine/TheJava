package com.company.thejava.completableFuture;

import java.util.concurrent.*;

public class CompletableFutureApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future test = executorService.submit(() -> "hello");

        /**
         * Future 의 단점!
         * Future로는 하기 어렵던 작업들
         * - Future를 외부에서 완료 시킬 수 없다. 취소하거나, get()에 타임아웃을 설정할 수는 있다.
         * - 블로킹 코드(get())를 사용하지 않고서는 작업이 끝났을 때 콜백을 실행할 수 없다.
         * - 여러 Future를 조합할 수 없다, 예) Event 정보 가져온 다음 Event에 참석하는 회원 목록 가져오기
         * - 예외 처리용 API를 제공하지 않는다
         *
         * Future 에서 get을 하기 전까지
         * Future의 결과값을 가지고 아무 것도 할 수 없다!
         *
         * future에 대한 작업은 future.get() 이후에 해야 한다!
         */
        // test.get();


        /**
         * CompletableFuture를 쓰면
         * 더이상 명시적으로 Excutors를 쓸 필요 없다!
         * CompletableFuture를 사용하여 비동기적으로 작업들을 쓸 수 있다!
         */
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("yeseul"); // future의 기본 값을 yeseul로 정해주고 끝냄!

        System.out.println(future.get());

        CompletableFuture<String> futureYeseul = CompletableFuture.completedFuture("yeseul");
        System.out.println(future.get());

        /**
         * Return 이 없는 작업! runAsync 쓰면 된다!
         *
         */
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        });

        voidCompletableFuture.get(); // get또는 join을 해야지 어떤 일이 발생한다!!

        /**
         * Return Type이 있는 경우 supplyAsync
         */

        CompletableFuture<String> supplyFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> {
            /**
             * 이전 Future 에는 callBack을 get 호출하기 전에 정의 하는 게 불가능했는데 이제는 가능해졌다!
             */
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });

        System.out.println(supplyFuture.get());

        /**
         * thenAccept를 통해 Consumer가 되어 String -> Void 가 되었다!
         */
        CompletableFuture<Void> supplyFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept((s) -> {
            /**
             * 이전 Future 에는 callBack을 get 호출하기 전에 정의 하는 게 불가능했는데 이제는 가능해졌다!
             * callBack인데 return 이 없는 경우는 thenAccept!
             * 여기에는 Consumer가 들어온다!
             */
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });

        System.out.println(supplyFuture2.get());


        /**
         * thenRun
         */
        CompletableFuture<Void> supplyFuture3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenRun(() -> {
            /**
             * Return 을 할 것도 없고 그냥 뭔가를 하면 된다!!
             * thenRun 을 쓰면 결과값도 참고 못함!! 이 자리에 Runnable이 온다!
             */
            System.out.println(Thread.currentThread().getName());
        });


        /**
         * 비동기적으로 작업을 처리 했는데 스레드 풀을 만들지도 않고 별도의 스레드에서 동작을 했지???
         * ForkJoinFool 때문이다!! (Java7에서 생김!)
         * ForkJoinFool 도 Excutors를 구현한 건데
         * Deque를 써서 맨 마지막에 들어온게 먼저 나감!
         * 작업단위를 쪼개서 분산시켜 작업을 처리하고 이걸 모아서 결과값을 도출해낼 수 있음!!
         *
         * 그래서 Excutors를 쓰지 않아도 내부적으로 ForkJoinFool에 있는 Common pool을 쓰게 된다
         * 원한다면 얼마든지 Excutors를 쓸 수 있기도 함!
         *
         */
        CompletableFuture<Void> supplyFuture4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }, executorService).thenRunAsync(() -> {

            System.out.println(Thread.currentThread().getName());
        }, executorService);



        System.out.println(supplyFuture4.get());



    }
}

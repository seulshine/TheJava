package com.company.thejava.completableFuture;

import java.util.*;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletableFutureApp2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 이전에는 Future만 가지고는 어떤 작업들을 이어서 처리하는 게 힘들었다.
         * 비동기 작업 2개를 연결하는 게 어려웠다 => callBack을 줄 수 없었기 때문에
         *
         */
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });


        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });



        /**
         * 과거에는
         * Hello 끝난 다음에 World를 해야 한다!
         * 그러면 get해서 hello가 끝나기를 기다려야 한다!

        hello.get();
        world.get();

        */

        CompletableFuture<String> future = hello.thenCompose(s -> getWorld(s));

        System.out.println(future.get());

        /**
         * 서로 의존적이지는 않지만
         * 둘 다 결과가 왔을 때 뭔가 하고 싶은 경우!
         */
        CompletableFuture<String> future2=  hello.thenCombine(world, (h, w) -> {
           return h + " " + w;
        });
        System.out.println(future2.get());

        /**
         * allOf로 부른 CompletableFuture가 다 끝났을 떄!!
         * 뭔가를 할 수 있다.
         * .thenApply, .thenAccept ....
         * 문제는 모든 Task들의 결과가 동일한 타입이라는 보장도 없고..
         * 그 중에 어떤 거는 에러가 났을 수도 있는데!
         * => 결과값 자체가 무의미 하다.... 결과가 NULL
         *
         */
        CompletableFuture<Void> future3 = CompletableFuture.allOf(hello, world)
                .thenAccept(System.out::println);

        System.out.println(future3.get());

        /**
         * 제대로 다 받으려면
         * 모든 Task들의 결과값을 Collection 으로 만들어서 받을 수 있음!
         */
        List<CompletableFuture<String>> futures = Arrays.asList(hello, world);

        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        /**
         *
         * 결과 값 받아가지고
         * 전부다 끝났을 때
         * 후 처리 (forEach : println) 할 수 있다.
         */
        CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> {
                    /**
                     * 결과는 사실상 무의미 하고
                     * 리턴이 중요하다!!
                     *
                      */
                    return futures.stream()
                            .map(CompletableFuture::join) // get은 Checked Exception을 발생시켜 try-catch처리를 해줘야 하는데
                            // join 은 Unchecked Exception이라 예외처리 필요없다.
                            .collect(Collectors.toList());
                });

        results.get().forEach(System.out::println);

        System.out.println("############");

        /**
         * anyOf 는 아무거나 더 빨리 끝나는 것!
         * thenAccept는 리턴 타입이 없음
         */
        CompletableFuture<Void> futureAny = CompletableFuture.anyOf(hello, world).thenAccept((s) -> {
            System.out.println(s);
        });

        futureAny.get();


        /**
         * 비동기적으로 처리하는 Task안에서 에러가 발생한다?
         *
         */

        boolean throwError = true;


        CompletableFuture<String> errorsss = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> { // result는 정상처리 되었을 떄, ex는 exception 발생했을 떄
            if(ex != null) {
                System.out.println(ex);
                return "Error!";
            }

            return result;
        });


                /**
                .exceptionally(ex -> {
                    System.out.println(ex);
                    return "Error!";
                });

                 */
        System.out.println(errorsss.get());

    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    }

}

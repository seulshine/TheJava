package com.company.thejava.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class App {

    public static void main(String[] args) {

        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
        /**
         * Optional 을 쓰기 전!
         *
        Progress progress = spring_boot.getProgress();
        if (progress != null) { // 에러를 만들기 쉬운 코드!! 사람이기 때문에 null 체크를 깜빡할 수 있다!!
            System.out.println(progress.getStudyDuration());
        }
         */

        Optional.of(10); // Optional 에 primitive 사용하면 자동으로 boxing unboxing 일어나는데 많으면 비용이 많이 듦!!
        OptionalInt.of(10); // 권장사항


        /**
         * 있을 수도 있고 없을 수도 있으니까 Optional 이 가장 적합한 리턴 타입
         */
        Optional<OnlineClass> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        boolean present = spring.isPresent();
        System.out.println(present);

        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();

        boolean present2 = optional.isEmpty();
        System.out.println(present2);


        /**
         * 그냥 꺼내면 java.util.NoSuchElementException 발생할 수 있음
         * 확인 하고 꺼내라!!
         */
//        OnlineClass onlineClass = optional.get();
//        System.out.println(onlineClass.getTitle());

        /**
         * Optional API - orElse (있어도 없어도 다 createNewClass 한다.)
         * 이미 만들어져 있는 것 (상수로..)을 사용할 때 적절
         */
        OnlineClass onlineClass1 = optional.orElse(createNewClass());

        /**
         * Optional API - orElseGet (있는 경우에는 Supplier 를 호출하지 않는다.)
         * 동적으로 만들어야 할 때 orElse 보다 적절!
         */
        OnlineClass onlineClass2 = optional.orElseGet(App::createNewClass);

        /**
         * Optional API - orElseThrow
         * 원하는 에러를 던질 수 있다!
         */
        OnlineClass onlineClass3 = optional.orElseThrow(IllegalStateException::new);

        /**
         * Optional API - filter : 있다는 가정 하에 한다! 없는 경우는 아무 일도 일어나지 않는다.
         */
        Optional<OnlineClass> onlineClass4 = optional.filter(oc -> !oc.isClosed());
        Optional<OnlineClass> onlineClass5 = optional.filter(OnlineClass::isClosed);

        Optional<Integer> integer = optional.map(oc -> oc.getId());
        // ==
        Optional<Integer> integer2 = optional.map(OnlineClass::getId);

        System.out.println(integer.isPresent());

        /**
         * 만약 map 으로 꺼내는 타입이 Optional 이면????
         * 굉장히 복잡해진다.
         * Optional 의 Optional...
         */

        Optional<Optional<Progress>> progress = optional.map(OnlineClass::getProgress);
        Optional<Progress> progress1 = progress.orElseThrow();
        progress1.orElseThrow();
        // 위의 2개를 합쳐서 이걸 한번에 까주는...
        Optional<Progress> progress3 = optional.flatMap(OnlineClass::getProgress);

    }

    private static OnlineClass createNewClass() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New Class", false);
    }
}

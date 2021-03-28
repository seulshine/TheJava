package com.company.thejava.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OnlineApp {
    public static void main(String[] args) {


        List<OnlineClass> springClass = new ArrayList<>();
        springClass.add(new OnlineClass(1, "spring boot", true));
        springClass.add(new OnlineClass(2, "spring data jpa", true));
        springClass.add(new OnlineClass(3, "spring mvc", false));
        springClass.add(new OnlineClass(4, "spring core", false));
        springClass.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring 으로 시작하는 수업");
        // TODO
        springClass.stream()
                .filter( oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("closed 되지 않은 수업");
        // TODO
        springClass.stream()
                // .filter(Predicate.not(OnlineClass::isClosed)) // 메서드 레퍼런스 이용!
                .filter (oc -> !oc.isClosed())
                .forEach(oc->System.out.println(oc.getTitle()));


        System.out.println("수업 이름만 모아서 스트림 만들기");
        // TODO
        springClass.stream()
                .map(oc -> oc.getTitle()) // map 에서 OnlineClss 타입에서 String 타입으로 내보냄!
                .forEach(System.out::println); //  String으로 들어오기 때문에 메서드 레퍼런스 사용 가능


        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java Test", true));
        javaClasses.add(new OnlineClass(6, "The Java Code manipulation", true));
        javaClasses.add(new OnlineClass(6, "The Java, 8 to 11", false));

        List<List<OnlineClass>> yeseulEvents = new ArrayList<>();
        yeseulEvents.add(springClass);
        yeseulEvents.add(javaClasses);



        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        // TODO
        yeseulEvents.stream()
                .flatMap(Collection::stream)
                .forEach(oc -> System.out.println(oc.getTitle()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개만 빼고 최대 10개까지만");
        // TODO
        Stream.iterate(10, i -> i+1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);


        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        // TODO
        boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));

        System.out.println(test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        // TODO
        List<String> spring = springClass.stream().filter(oc -> oc.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());
        spring.forEach(System.out::println);
    }



}

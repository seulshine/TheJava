package com.company.thejava.methodReference;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {

    public static void main(String[] args) {
        // UnaryOperator<String> hi = (s) -> "hi" + s;
        
        // 스테틱 메소드 참조
        UnaryOperator<String> hi = Greeting::hi; // ::이 메서드라는 의미 Greeting 클래스의 hi라는 메서드를 가져다 쓰겠다! : 메서드를 호출한게 아니다!! 메서드를 참조하는 UnaryOperator 가 만들어진거다!!

        
        // 특정 객체의 인스턴스 메소드 참조
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello; // 인스턴스 메서드를 사용하겠다!
        System.out.println(hello.apply("yeseul")); // apply를 해야 진짜로 만들어진거다!!


        // 생성자 참조
        Supplier<Greeting> newGreeting = Greeting::new;
        Greeting greeting2 = newGreeting.get(); // get을 해야 진짜 Greeting이 만들어진다!!

        Function<String, Greeting> yeseulGreeting = Greeting::new; // 위와 같은 생성자를 참조하는 것 같지만 argument 에 문자열을 받는 생성자를 참조해서 위와는 다른 생성자를 참조한다!!

        Greeting yeseul = yeseulGreeting.apply("yeseul");
        System.out.println(yeseul.getName() );


        // 임의 객체의 인스턴스 메소드 참조
        String [] names = {"yeseul", "jp", "morgan"};

         /** comparator 도 함수형 인터페이스 이기 때문에 lambda로 바꿀 수 있다 => 메소드 레퍼런스를 쓸 수 있다!!
        Arrays.sort(names, new Comparator<String>(){

            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
     */
         // 임의 객체(names)의 인스턴스 메소드(compareToIgnoreCase) 참조
         Arrays.sort(names, String::compareToIgnoreCase);

        System.out.println(Arrays.toString(names));
    }

}

package com.company.thejava.optional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        List<OnlineClass> springClass = new ArrayList<>();
        springClass.add(new OnlineClass(1, "spring boot", true));
        springClass.add(new OnlineClass(2, "spring data jpa", true));
        springClass.add(new OnlineClass(3, "spring mvc", false));
        springClass.add(new OnlineClass(4, "spring core", false));
        springClass.add(new OnlineClass(5, "rest api development", false));

        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
        /**
         * Optional 을 쓰기 전!
         *
        Progress progress = spring_boot.getProgress();
        if (progress != null) { // 에러를 만들기 쉬운 코드!! 사람이기 때문에 null 체크를 깜빡할 수 있다!!
            System.out.println(progress.getStudyDuration());
        }
         */

    }
}

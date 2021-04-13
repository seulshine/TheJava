package com.company.thejava.annotation;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@Chicken
public class App {

    public static void main(@Chicken String[] args) throws @Chicken RuntimeException {
        List<@Chicken String> names = Arrays.asList("yeseul");
    }

    static class FllesLikeChicken<@Chicken T> {
        public static <@Chicken C> void print(C c){ // TYPE_USE 면 @Chicken C c 도 가능 해짐 (TYPE을 사용하는 모든 곳!!)
            System.out.println(c);
        }
    }
}

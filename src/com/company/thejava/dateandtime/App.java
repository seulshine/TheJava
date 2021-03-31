package com.company.thejava.dateandtime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        long time = date.getTime();
        System.out.println(date);
        System.out.println(time);

        Thread.sleep(1000*3);
        Date after3seconds = new Date();
        System.out.println(after3seconds);
        after3seconds.setTime(time);
        /**
         * Date 변경 가능: mutable 하기 때문에 thread safe 하지 않다. (= 멀티 스레드 환경에서 안전하게 쓰기 어렵다.)
         * 버그 발생할 여지가 많다!!
         */
        System.out.println(after3seconds); // 3초 이전의 값으로 변경할 수 있다.

        Calendar yeseulBirthday = new GregorianCalendar(1996, Calendar.OCTOBER,9); // 10이라 입력하면 0부터 시작하기에 11월로 인식한다.

        /**
         * 위에 거에 대한 API인데 int 이기 때문에 아무 값이나 들어올 수 있다. -100 도 가능... type safe 하지 않다.
         * Month 라는 Enum type을 받게 조언
         */
//        public GregorianCalendar(int year, int month, int dayOfMonth) {
//            this(year, month, dayOfMonth, 0, 0, 0, 0);
//        }

        System.out.println(yeseulBirthday);
        System.out.println(yeseulBirthday.getTime()); // time인데 왜 Date가 나오냐!! 이상하다! 헷갈린당~~~!

        /**
         * 변경할 수 있다. IMMUTABLE 하지 않다.
         */
        yeseulBirthday.add(Calendar.DAY_OF_YEAR, 1);
        System.out.println(yeseulBirthday.getTime());


    }
}

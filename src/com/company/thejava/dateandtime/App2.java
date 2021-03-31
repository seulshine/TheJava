package com.company.thejava.dateandtime;

import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class App2 {
    public static void main(String[] args) {
        Instant instant = Instant.now();

        System.out.println(instant); // 기준시 UTC = GMT
        System.out.println(instant.atZone(ZoneId.of("GMT")));

        ZoneId zone = ZoneId.systemDefault(); // 지금 시스템 기준 시
        System.out.println(zone);
        ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println(zonedDateTime);


        LocalDateTime now = LocalDateTime.now(); // 사람용! 서버 시스템 기본 시간대 참고해서 가져온다!! 주의 해야 함
        System.out.println(now);

        /**
         * 특성상 immutable 하고 새로운 instant 가 만들어짐!!
         * 예전 now가 바뀌는 게 아님!!!!
         * 그냥 두면 아무 일도 일어나지 않음
         * LocalDateTime changed 라고 선언해 줘야 함!!
         */
        LocalDateTime changed = now.plus(10, ChronoUnit.DAYS);
        System.out.println("###########");
        System.out.println(changed);

        LocalDateTime birthDay = LocalDateTime.of(1996, Month.OCTOBER, 9, 0, 0, 0);
        System.out.println(birthDay);

        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);

        Instant nowInstant = Instant.now();
        ZonedDateTime zonedDateTime1 = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(zonedDateTime1);


        /**
         * 날짜 차이 일 줄 알았는데 day(31에서 9일까지 얼마남았는지 = 9)에 대한 차이만 보여줌,,,,
         * Period 는 사람용 시간을 비교!
         */
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2021, Month.OCTOBER, 9);

        Period period = Period.between(today, thisYearBirthday);
        System.out.println(period.getDays());

        Period until = today.until(thisYearBirthday);
        System.out.println(until.get(ChronoUnit.DAYS));

        /**
         * Duration 은 머신용 시간을 비교!!
         */
        Instant now2 = Instant.now();
        Instant plus = now2.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now2, plus);
        System.out.println(between.getSeconds());


        /**
         * Formatting
         */
        LocalDateTime now3 = LocalDateTime.now();
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern(("MM/dd/yyyy"));
        System.out.println(now3.format(MMddyyyy));

        /**
         * Parsing
         */
        LocalDate parse = LocalDate.parse("10/09/1996", MMddyyyy);
        System.out.println(parse);


        /**
         * 레거시를 지원해줌!! 서로 호환이 됨!
         * Date를 Instant 로 바꿀 수 있음 ! Instant는 새로운 API 인데
         */
        Date date = new Date();
        Instant instant1 = date.toInstant(); // Date -> Instant
        Date nowDate = Date.from(instant1);  // Instant -> Date

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        // GregorianCalendar -> LocalDateTime
        // Instant로 바꿀 수 있으면 다 최신으로 바꿀 수 있다 생각하면 됨!
        LocalDateTime dateTime2 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        // 반대로 가능 경우
        ZonedDateTime dateTime3 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
        GregorianCalendar from = GregorianCalendar.from(dateTime3);

        // ZoneId 도 새로운 API!
        // 예전 API는 TimeZone!! 이것도 서로 호환 (PST = Pacific Time Zone)
        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        // 반대로 최근 ZoneId API 에서 레거시에 해당하는 TimeZone을 만들 수 있음
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);
    }
}

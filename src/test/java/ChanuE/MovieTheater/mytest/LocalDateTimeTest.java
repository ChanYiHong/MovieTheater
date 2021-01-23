package ChanuE.MovieTheater.mytest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeTest {

    public static void main(String[] args) {

        LocalDate localDate = LocalDate.of(2020, 4, 8);

        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());


        LocalTime localTime = LocalTime.of(23, 40);

        System.out.println(localTime.getHour());
        System.out.println(localTime.getMinute());


    }

}

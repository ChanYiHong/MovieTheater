package ChanuE.MovieTheater;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("str1", "str2", "str3");
        Stream<String> stream = list.stream();
        stream.forEach(l-> {
            System.out.println(l);
        });

        List<Student> students = Arrays.asList(
                new Student("hong", 90),
                new Student("Choi", 100)
        );

        Stream<Student> studentStream = students.stream();
        studentStream.forEach(s ->{
            String name = s.getName();
            int score = s.getScore();
            System.out.println(name + " " + score);
        });

        double avg = students.stream()
                // 중간 처리 : 학생의 점수 뽑아냄.
                .mapToInt(Student::getScore)
                // 최종 처리 (평균 점수).
                .average()
                .getAsDouble();

        System.out.println(avg);
    }



    @Getter
    @AllArgsConstructor
    static class Student{
        private String name;
        private int score;
    }
}

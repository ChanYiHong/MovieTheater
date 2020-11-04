package ChanuE.MovieTheater.repository;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.TimeTable;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class TimeTableRepositoryTest {

    @Autowired
    TimeTableRepository timeTableRepository;

    @Test
    public void saveTimeTable() throws Exception {
        //given
        TimeTable timeTable = TimeTable.builder()
                .time(LocalDateTime.now())
                .build();

        Long saveId = timeTableRepository.save(timeTable);

        //when

        TimeTable findTimeTable = timeTableRepository.findOne(saveId);

        //then

        assertThat(findTimeTable.getTime()).isEqualTo(timeTable.getTime());
        //assertThat(findTimeTable.getMovie()).isEqualTo(timeTable.getMovie());

    }

}
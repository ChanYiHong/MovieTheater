package ChanuE.MovieTheater.dto.TimeTable;

import ChanuE.MovieTheater.domain.TimeTable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class TimeTableResponseDto {

    private Long id;
    private LocalDateTime time;

    public TimeTableResponseDto(Long id, LocalDateTime time){
        this.id = id;
        this.time = time;
    }

    public static List<TimeTableResponseDto> timeTableToTimeTableResponseDto(List<TimeTable> timeTables){
        List<TimeTableResponseDto> responseDtos = new ArrayList<>();

        for(TimeTable t : timeTables){
            responseDtos.add(new TimeTableResponseDto(t.getId(), t.getTime()));
        }

        return responseDtos;
    }
}

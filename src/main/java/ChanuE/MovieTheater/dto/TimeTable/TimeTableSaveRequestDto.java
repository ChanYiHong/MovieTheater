package ChanuE.MovieTheater.dto.TimeTable;

import ChanuE.MovieTheater.domain.TimeTable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TimeTableSaveRequestDto {

    private LocalDateTime time;

    @Builder
    public TimeTableSaveRequestDto(LocalDateTime time){
        this.time = time;
    }

    public TimeTable toEntity(){
        return TimeTable.builder().time(this.time).build();
    }
}

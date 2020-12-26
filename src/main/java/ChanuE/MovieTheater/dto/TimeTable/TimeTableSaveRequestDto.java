package ChanuE.MovieTheater.dto.TimeTable;

import ChanuE.MovieTheater.domain.TimeTable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter @Setter
@NoArgsConstructor
public class TimeTableSaveRequestDto {

    private String hour;
    private String minute;

    @Builder
    public TimeTableSaveRequestDto(String hour, String minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public TimeTable toEntity(){

        int hour = Integer.parseInt(this.hour);
        int minute = Integer.parseInt(this.minute);

        LocalTime time = LocalTime.of(hour, minute);

        return TimeTable.builder().time(time).build();
    }
}

package ChanuE.MovieTheater.dto.time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeSaveDTO {

    private int hour;
    private int minute;
    private Long cinemaId;
    private int seatNum;

}

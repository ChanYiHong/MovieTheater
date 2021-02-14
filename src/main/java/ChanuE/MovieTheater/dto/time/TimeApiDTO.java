package ChanuE.MovieTheater.dto.time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeApiDTO {

    private String area;
    private String specific;
    private int year;
    private int month;
    private int day;

}

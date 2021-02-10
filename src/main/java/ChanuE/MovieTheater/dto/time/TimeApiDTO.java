package ChanuE.MovieTheater.dto.time;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TimeApiDTO {

    private String year;
    private String month;
    private String day;

    @QueryProjection
    public TimeApiDTO(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}

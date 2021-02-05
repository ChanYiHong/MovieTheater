package ChanuE.MovieTheater.dto.theater;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Data
@ToString
public class TheaterAreaApiDTO {

    private String area;
    private Long specificCnt;

    @QueryProjection
    public TheaterAreaApiDTO(String area, Long specificCnt) {
        this.area = area;
        this.specificCnt = specificCnt;
    }
}

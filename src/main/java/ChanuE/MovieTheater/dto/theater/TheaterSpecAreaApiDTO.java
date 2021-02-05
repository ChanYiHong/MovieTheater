package ChanuE.MovieTheater.dto.theater;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.List;

@Data
@ToString
public class TheaterSpecAreaApiDTO {

    private String area;
    private String specificArea;

    @QueryProjection
    public TheaterSpecAreaApiDTO(String area, String specificArea) {
        this.area = area;
        this.specificArea = specificArea;
    }
}

package ChanuE.MovieTheater.dto.area;

import ChanuE.MovieTheater.domain.Area;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AreaResponseDto {

    private Long id;
    private String name;

    public AreaResponseDto(Area area){
        this.id = area.getId();
        this.name = area.getName();
    }
}

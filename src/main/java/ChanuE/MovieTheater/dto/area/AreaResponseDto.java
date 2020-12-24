package ChanuE.MovieTheater.dto.area;

import ChanuE.MovieTheater.domain.Area;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class AreaResponseDto {

    private Long id;
    private String name;

    public AreaResponseDto(Area area){
        this.id = area.getId();
        this.name = area.getAreaName();
    }

    public static List<AreaResponseDto> areaToAreaResponseDto(List<Area> areas){
        List<AreaResponseDto> areaResponseDtos = new ArrayList<>();

        // Builder를 통해 AreaResponseDto를 바로 생성해서 Collection List에 저장.
        for(Area a : areas){
            areaResponseDtos.add(new AreaResponseDto(a));
        }

        return areaResponseDtos;
    }
}

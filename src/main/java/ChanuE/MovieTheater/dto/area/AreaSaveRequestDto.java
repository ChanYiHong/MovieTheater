package ChanuE.MovieTheater.dto.area;

import ChanuE.MovieTheater.domain.Area;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class AreaSaveRequestDto {

    private String name;

    @Builder
    public AreaSaveRequestDto(String areaName){
        this.name = areaName;
    }

    public Area toEntity(){
        return Area.builder().areaName(this.name).build();
    }
}

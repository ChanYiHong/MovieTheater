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
    private String specificName;

    @Builder
    public AreaSaveRequestDto(String areaName, String specificName){
        this.name = areaName;
        this.specificName = specificName;
    }

    public Area toEntity(){
        return Area.builder().name(this.name).specificName(this.specificName).build();
    }
}

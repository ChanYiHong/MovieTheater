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
    public AreaSaveRequestDto(String name){
        this.name = name;
    }

    public Area toEntity(){
        return Area.builder().name(this.name).build();
    }
}

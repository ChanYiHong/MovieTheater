package ChanuE.MovieTheater.dto.specificArea;

import ChanuE.MovieTheater.domain.SpecificArea;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SpecificAreaSaveRequestDto {

    private String name;

    @Builder
    public SpecificAreaSaveRequestDto(String name){
        this.name = name;
    }

    public SpecificArea toEntity(){
        return SpecificArea.builder().specificAreaName(this.name).build();
    }

}

package ChanuE.MovieTheater.dto.specificArea;

import ChanuE.MovieTheater.domain.SpecificArea;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SpecificAreaResponseDto {

    private Long id;
    private String name;

    public SpecificAreaResponseDto(SpecificArea specificArea){
        this.id = specificArea.getId();
        this.name = specificArea.getName();
    }

}

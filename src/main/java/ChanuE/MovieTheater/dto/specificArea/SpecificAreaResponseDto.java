package ChanuE.MovieTheater.dto.specificArea;

import ChanuE.MovieTheater.domain.SpecificArea;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SpecificAreaResponseDto {

    private Long id;
    private String name;

    @Builder
    public SpecificAreaResponseDto(SpecificArea specificArea){
        this.id = specificArea.getId();
        this.name = specificArea.getName();
    }

   //public static List<SpecificAreaResponseDto>
}

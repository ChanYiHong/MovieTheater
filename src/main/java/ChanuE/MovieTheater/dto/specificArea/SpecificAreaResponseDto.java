package ChanuE.MovieTheater.dto.specificArea;

import ChanuE.MovieTheater.domain.SpecificArea;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class SpecificAreaResponseDto {

    private Long id;
    private String name;

    public SpecificAreaResponseDto(SpecificArea specificArea){
        this.id = specificArea.getId();
        this.name = specificArea.getName();
    }

   public static List<SpecificAreaResponseDto> SpecificAreaToSpecificAreaResponseDto(List<SpecificArea> specificAreas){
        List<SpecificAreaResponseDto> responseDtos = new ArrayList<>();

        for(SpecificArea s : specificAreas){
            responseDtos.add(new SpecificAreaResponseDto(s));
        }

        return responseDtos;
   }
}

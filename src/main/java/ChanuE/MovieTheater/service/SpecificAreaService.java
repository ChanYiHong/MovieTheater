package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Area;
import ChanuE.MovieTheater.domain.SpecificArea;
import ChanuE.MovieTheater.dto.area.AreaResponseDto;
import ChanuE.MovieTheater.dto.specificArea.SpecificAreaResponseDto;
import ChanuE.MovieTheater.dto.specificArea.SpecificAreaSaveRequestDto;
import ChanuE.MovieTheater.repository.AreaRepository;
import ChanuE.MovieTheater.repository.SpecificAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SpecificAreaService {

    private final SpecificAreaRepository specificAreaRepository;
    private final AreaRepository areaRepository;

    @Transactional
    public void saveSpecificArea(SpecificAreaSaveRequestDto requestDto, Long areaId){
        SpecificArea specificArea = requestDto.toEntity();
        checkDuplicateSpecificArea(specificArea.getName());
        Area area = areaRepository.findOne(areaId);
        specificArea.setArea(area);
        specificAreaRepository.saveSpecificArea(specificArea);
    }

    private void checkDuplicateSpecificArea(String name){
        List<SpecificArea> areas = specificAreaRepository.findOneByName(name);
        if(!areas.isEmpty()){
            throw new IllegalStateException("Duplicate Specific Area.. Please try other name...");
        }
    }

    public List<SpecificAreaResponseDto> findAllSpecificArea(Long areaId){
        List<SpecificArea> areas = specificAreaRepository.findSpecificAreaByAreaId(areaId);
        return SpecificAreaResponseDto.SpecificAreaToSpecificAreaResponseDtos(areas);
    }

    public SpecificAreaResponseDto findOne(Long specificAreaId){
        SpecificArea specificArea = specificAreaRepository.findOne(specificAreaId);
        return SpecificAreaResponseDto.SpecificAreaToSpecificAreaResponseDto(specificArea);
    }

}

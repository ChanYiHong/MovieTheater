package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Area;
import ChanuE.MovieTheater.domain.SpecificArea;
import ChanuE.MovieTheater.dto.specificArea.SpecificAreaResponseDto;
import ChanuE.MovieTheater.dto.specificArea.SpecificAreaSaveRequestDto;
import ChanuE.MovieTheater.repository.area.AreaRepository;
import ChanuE.MovieTheater.repository.area.AreaSpringDataJpaRepository;
import ChanuE.MovieTheater.repository.specificArea.SpecificAreaRepository;
import ChanuE.MovieTheater.repository.specificArea.SpecificAreaSearch;
import ChanuE.MovieTheater.repository.specificArea.SpecificAreaSpringDataJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SpecificAreaService {

    private final SpecificAreaSpringDataJpaRepository specificAreaRepository;
    private final AreaSpringDataJpaRepository areaRepository;

    @Transactional
    public void saveSpecificArea(SpecificAreaSaveRequestDto requestDto, Long areaId){
        SpecificArea specificArea = requestDto.toEntity();
        checkDuplicateSpecificArea(specificArea.getSpecificAreaName());
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없음 id = " + areaId));
        specificArea.setArea(area);
        specificAreaRepository.save(specificArea);
    }

    private void checkDuplicateSpecificArea(String name){
        List<SpecificArea> areas = specificAreaRepository.findSpecificAreaBySpecificAreaName(name);
        if(!areas.isEmpty()){
            throw new IllegalStateException("Duplicate Specific Area.. Please try other name...");
        }
    }

    public List<SpecificAreaResponseDto> findAllSpecificArea(Long areaId){
        List<SpecificArea> areas = specificAreaRepository.findSpecificAreaByAreaId(areaId);
        return areas.stream()
                .map(SpecificAreaResponseDto::new)
                .collect(Collectors.toList());
    }

    public SpecificAreaResponseDto findOne(Long specificAreaId){
        SpecificArea specificArea = specificAreaRepository.findById(specificAreaId)
                .orElseThrow(() -> new IllegalArgumentException("해당 특정 지역이 없음 id = " + specificAreaId));
        return new SpecificAreaResponseDto(specificArea);
    }

    public List<SpecificAreaResponseDto> findAllBySpecificAreaSearch(SpecificAreaSearch specificAreaSearch) {
        List<SpecificArea> result = specificAreaRepository.findAllBySearchCond(specificAreaSearch);
        return result.stream()
                .map(SpecificAreaResponseDto::new)
                .collect(Collectors.toList());
    }

}

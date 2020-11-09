package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.SpecificArea;
import ChanuE.MovieTheater.dto.specificArea.SpecificAreaSaveRequestDto;
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

    public void saveSpecificAreaRepository(SpecificAreaSaveRequestDto requestDto){
        SpecificArea specificArea = requestDto.toEntity();
        checkDuplicateSpecificArea(specificArea.getName());
        specificAreaRepository.saveSpecificArea(specificArea);
    }

    private void checkDuplicateSpecificArea(String name){
        List<SpecificArea> areas = specificAreaRepository.findOneByName(name);
        if(!areas.isEmpty()){
            throw new IllegalStateException("Duplicate Specific Area.. Please try other name...");
        }
    }


}

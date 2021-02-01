package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Date;
import ChanuE.MovieTheater.domain.SpecificArea;
import ChanuE.MovieTheater.dto.date.DateResponseDto;
import ChanuE.MovieTheater.dto.date.DateSaveRequestDto;
import ChanuE.MovieTheater.repository.date.DateSpringDataJpaRepository;
import ChanuE.MovieTheater.repository.specificArea.SpecificAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DateService {

    private final DateSpringDataJpaRepository dateRepository;
    private final SpecificAreaRepository specificAreaRepository;

    @Transactional
    public void saveDate(DateSaveRequestDto requestDto, Long specificAreaId){

        Date date = requestDto.toEntity();
        SpecificArea specificArea = specificAreaRepository.findById(specificAreaId)
                .orElseThrow(() -> new IllegalArgumentException("해당 특정 지역이 없음. id = " + specificAreaId));
        date.setSpecificArea(specificArea);
        dateRepository.save(date);

    }

    public List<DateResponseDto> findAllDatesBySpecificAreaId(Long id){

        List<Date> dates = dateRepository.findAllDateBySpecificAreaId(id);
        return dates.stream()
                .map(DateResponseDto::new)
                .collect(Collectors.toList());

    }

    public DateResponseDto findOne(Long id){
        return new DateResponseDto(dateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 하는 날짜가 없음 id = " + id)));
    }

}

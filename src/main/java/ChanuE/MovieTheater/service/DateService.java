package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Date;
import ChanuE.MovieTheater.domain.SpecificArea;
import ChanuE.MovieTheater.dto.date.DateResponseDto;
import ChanuE.MovieTheater.dto.date.DateSaveRequestDto;
import ChanuE.MovieTheater.repository.DateRepository;
import ChanuE.MovieTheater.repository.SpecificAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DateService {

    private final DateRepository dateRepository;
    private final SpecificAreaRepository specificAreaRepository;

    @Transactional
    public void saveDate(DateSaveRequestDto requestDto, Long specificAreaId){

        Date date = requestDto.toEntity();
        SpecificArea specificArea = specificAreaRepository.findOne(specificAreaId);
        date.setSpecificArea(specificArea);
        dateRepository.saveDate(date);

    }

    public List<DateResponseDto> findAllDatesBySpecificAreaId(Long id){

        List<Date> dates = dateRepository.findAllDateBySpecificAreaId(id);
        return DateResponseDto.DateToDateResponseDtos(dates);

    }

}

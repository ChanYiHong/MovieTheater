package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Date;
import ChanuE.MovieTheater.domain.TimeTable;
import ChanuE.MovieTheater.dto.TimeTable.TimeTableResponseDto;
import ChanuE.MovieTheater.dto.TimeTable.TimeTableSaveRequestDto;
import ChanuE.MovieTheater.repository.date.DateSpringDataJpaRepository;
import ChanuE.MovieTheater.repository.timeTable.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;
    private final DateSpringDataJpaRepository dateRepository;

    @Transactional
    public void saveTimeTable(TimeTableSaveRequestDto requestDto, Long dateId){
        TimeTable timeTable = requestDto.toEntity();
        Date date = dateRepository.findById(dateId)
                .orElseThrow(() -> new IllegalArgumentException("해당 하는 날짜가 없음 id = " + dateId));
        timeTable.setDate(date);
        timeTableRepository.save(timeTable);
    }

    public List<TimeTableResponseDto> findAllTimeTable(){
        return timeTableRepository.findAll()
                .stream().map(TimeTableResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<TimeTableResponseDto> findAllTimeTableByDateId(Long id){
        return timeTableRepository.findAllTimeTableByDateId(id)
                .stream().map(TimeTableResponseDto::new)
                .collect(Collectors.toList());
    }
}

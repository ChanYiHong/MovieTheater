package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Seat;
import ChanuE.MovieTheater.domain.TimeTable;
import ChanuE.MovieTheater.dto.TimeTable.TimeTableResponseDto;
import ChanuE.MovieTheater.dto.TimeTable.TimeTableSaveRequestDto;
import ChanuE.MovieTheater.dto.seat.SeatResponseDto;
import ChanuE.MovieTheater.dto.seat.SeatSaveRequestDto;
import ChanuE.MovieTheater.repository.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;

    @Transactional
    public void saveTimeTable(TimeTableSaveRequestDto requestDto){
        TimeTable timeTable = requestDto.toEntity();
        timeTableRepository.save(timeTable);
    }

    public List<SeatResponseDto> findAllSeat(){
        return SeatResponseDto.seatToSeatResponseDto(seatRepository.findAll());
    }
}

package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Seat;
import ChanuE.MovieTheater.dto.seat.SeatResponseDto;
import ChanuE.MovieTheater.dto.seat.SeatSaveRequestDto;
import ChanuE.MovieTheater.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeatService {

    private final SeatRepository seatRepository;

    @Transactional
    public void saveSeat(SeatSaveRequestDto requestDto){
        Seat seat = requestDto.toEntity();
        seatRepository.save(seat);
    }

    public List<SeatResponseDto> findAllSeat(){
        return SeatResponseDto.seatToSeatResponseDto(seatRepository.findAll());
    }
}

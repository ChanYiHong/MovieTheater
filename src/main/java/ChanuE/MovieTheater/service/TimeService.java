package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Cinema;
import ChanuE.MovieTheater.domain.Time;
import ChanuE.MovieTheater.dto.seat.SeatDTO;
import ChanuE.MovieTheater.dto.time.TimeApiDTO;
import ChanuE.MovieTheater.dto.time.TimeResponseDTO;
import ChanuE.MovieTheater.dto.time.TimeSaveDTO;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public interface TimeService {

    Long save(TimeSaveDTO timeSaveDTO);

    // Time은 한 극장에 많지는 않으니까 페이징 x
    List<TimeResponseDTO> list(Long cinemaId);

    void remove(Long timeId);

    // 예약 화면에서 시간 조회
    List<TimeResponseDTO> listForAPI(Long movieId, TimeApiDTO timeApiDTO);

    default Time dtoToEntity(TimeSaveDTO timeSaveDTO, Cinema cinema) {
        return Time.builder()
                .time(LocalTime.of(timeSaveDTO.getHour(),timeSaveDTO.getMinute()))
                .cinema(cinema)
                .seatNum(timeSaveDTO.getSeatNum())
                .availableNum(timeSaveDTO.getSeatNum())
                .build();
    }

    default TimeResponseDTO entityToDTO(Time time) {
        return TimeResponseDTO.builder()
                .id(time.getId())
                .cinemaId(time.getCinema().getId())
                .seatNum(time.getSeatNum())
                .time(time.getTime())
                .availableNum(time.getAvailableNum())
                .cinemaName(time.getCinema().getName())
                .display(time.getCinema().getDisplay())
                .build();
    }

}

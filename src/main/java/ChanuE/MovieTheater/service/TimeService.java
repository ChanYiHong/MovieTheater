package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Cinema;
import ChanuE.MovieTheater.domain.Time;
import ChanuE.MovieTheater.dto.seat.SeatDTO;
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

    default Time dtoToEntity(TimeSaveDTO timeSaveDTO, Cinema cinema) {
        return Time.builder()
                .time(LocalTime.of(timeSaveDTO.getHour(),timeSaveDTO.getMinute()))
                .cinema(cinema)
                .seatNum(timeSaveDTO.getSeatNum())
                .build();
    }

    default TimeResponseDTO entityToDTO(Time time) {
        return TimeResponseDTO.builder()
                .id(time.getId())
                .cinemaId(time.getCinema().getId())
                .seatNum(time.getSeatNum())
                .time(time.getTime())
                .build();
    }

}

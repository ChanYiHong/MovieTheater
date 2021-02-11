package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Cinema;
import ChanuE.MovieTheater.domain.Seat;
import ChanuE.MovieTheater.domain.Time;
import ChanuE.MovieTheater.dto.time.TimeResponseDTO;
import ChanuE.MovieTheater.dto.time.TimeSaveDTO;
import ChanuE.MovieTheater.repository.cinema.CinemaRepository;
import ChanuE.MovieTheater.repository.time.TimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log4j2
public class TimeServiceImpl implements TimeService{

    private final TimeRepository timeRepository;
    private final CinemaRepository cinemaRepository;
    private final SeatService seatService;

    @Override
    @Transactional
    public Long save(TimeSaveDTO timeSaveDTO) {
        Cinema cinema = cinemaRepository.findById(timeSaveDTO.getCinemaId())
                .orElseThrow(() -> new IllegalStateException("해당 Cinema가 없음"));

        Time time = dtoToEntity(timeSaveDTO, cinema);
        // 처음 극장 시간 정할 때, 좌석을 모두 사용 가능한 상태로 넣어줌.
        List<Seat> seats = seatService.makeSeats(timeSaveDTO.getSeatNum(), time);
        time.setSeats(seats);

        timeRepository.save(time);

        return time.getId();
    }

    @Override
    public List<TimeResponseDTO> list(Long cinemaId) {
        List<Time> result = timeRepository.findByCinemaId(cinemaId);
        return result.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    /** Cascading remove 속성으로 인해 time을 지우면 seat이 다 지워짐..
     * 문제는 seat이 100개면 100번의 seat을 지우는 쿼리가 나가는데, 이러면 효율이 떨어짐. 최적화가 필요!**/
    @Override
    @Transactional
    public void remove(Long timeId) {
        timeRepository.deleteById(timeId);
    }
}

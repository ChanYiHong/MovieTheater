package ChanuE.MovieTheater.service.cinema;

import ChanuE.MovieTheater.domain.Cinema;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Theater;
import ChanuE.MovieTheater.domain.Time;
import ChanuE.MovieTheater.dto.cinema.CinemaDTO;
import ChanuE.MovieTheater.dto.cinema.CinemaDateApiDTO;
import ChanuE.MovieTheater.dto.cinema.CinemaSaveDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.cinema.CinemaRepository;
import ChanuE.MovieTheater.repository.cinema.CinemaSearch;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import ChanuE.MovieTheater.repository.theater.TheaterRepository;
import ChanuE.MovieTheater.repository.time.TimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log4j2
public class CinemaServiceImpl implements CinemaService{

    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final TimeRepository timeRepository;

    @Override
    @Transactional
    public Long save(CinemaSaveDTO cinemaSaveDTO, Long theaterId) {
        Optional<Movie> movie = movieRepository.findMovieByTitle(cinemaSaveDTO.getTitle());
        if(!movie.isPresent()){
            throw new IllegalStateException("Not Exist Movie!!!");
        }
        Optional<Theater> theater = theaterRepository.findById(theaterId);
        if(!theater.isPresent()){
            throw new IllegalStateException("Not Exist Theater!!!");
        }
        Cinema cinema = dtoToEntity(cinemaSaveDTO, movie.get(), theater.get());

        log.info("Save Cinema : " + cinema);

        cinemaRepository.save(cinema);
        return cinema.getId();
    }

    @Override
    public PageResponseDTO<Cinema, CinemaDTO> list(Long theaterId, CinemaSearch cinemaSearch, PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id"));
        Page<Cinema> result = cinemaRepository.findCinemaWithSearchCond(theaterId, cinemaSearch, pageable);
        Function<Cinema, CinemaDTO> fn = this::entityToDTO;

        log.info("Get Cinema list with theater Id : " + theaterId);

        return new PageResponseDTO<>(result, fn);
    }

    @Override
    public CinemaDTO get(Long id) {
        log.info("Get one Cinema : " + id);
        Cinema result = cinemaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Cinema!!!"));
        return entityToDTO(result);
    }

    @Override
    public List<CinemaDateApiDTO> getDateForAPI(Long movieId, String area, String specificArea) {
        List<LocalDate> result = cinemaRepository.findCinemaDateForAPI(movieId, area, specificArea);
        return result.stream().map(localDate -> {
             return CinemaDateApiDTO.builder()
                    .year(localDate.getYear())
                    .month(localDate.getMonthValue())
                    .day(localDate.getDayOfMonth()).build();
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remove(Long cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 상영관.. 삭제 불가능.."));

        cinema.changeMovie(null);
        cinema.changeTheater(null);

        List<Time> times = timeRepository.findByCinemaId(cinemaId);
        for (Time time : times) {
            timeRepository.delete(time);
        }

        cinemaRepository.delete(cinema);
    }
}

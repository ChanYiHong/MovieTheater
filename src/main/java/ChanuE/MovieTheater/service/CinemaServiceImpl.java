package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Cinema;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Theater;
import ChanuE.MovieTheater.dto.cinema.CinemaDTO;
import ChanuE.MovieTheater.dto.cinema.CinemaSaveDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.cinema.CinemaRepository;
import ChanuE.MovieTheater.repository.cinema.CinemaSearch;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import ChanuE.MovieTheater.repository.theater.TheaterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log4j2
public class CinemaServiceImpl implements CinemaService{

    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;

    @Override
    @Transactional
    public Long save(CinemaSaveDTO cinemaSaveDTO, Long theaterId) {
        Optional<Movie> movie = movieRepository.findMovieByMovieName(cinemaSaveDTO.getMovieName());
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
}

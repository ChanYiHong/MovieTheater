package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Cinema;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Theater;
import ChanuE.MovieTheater.dto.cinema.CinemaDTO;
import ChanuE.MovieTheater.dto.cinema.CinemaDateApiDTO;
import ChanuE.MovieTheater.dto.cinema.CinemaSaveDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.cinema.CinemaSearch;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public interface CinemaService {

    Long save(CinemaSaveDTO cinemaSaveDTO, Long theaterId);

    PageResponseDTO<Cinema, CinemaDTO> list(Long theaterId, CinemaSearch cinemaSearch, PageRequestDTO pageRequestDTO);

    CinemaDTO get(Long id);

    List<CinemaDateApiDTO> getDateForAPI(Long movieId, String area, String specificArea);

    void remove(Long cinemaId);

    default Cinema dtoToEntity(CinemaSaveDTO cinemaSaveDTO, Movie movie, Theater theater){
        return Cinema.builder()
                .id(cinemaSaveDTO.getId())
                .movie(movie)
                .theater(theater)
                .name(cinemaSaveDTO.getName())
                .display(cinemaSaveDTO.getDisplay())
                .seats(cinemaSaveDTO.getSeats())
                .date(LocalDate.of(cinemaSaveDTO.getYear(), cinemaSaveDTO.getMonth(), cinemaSaveDTO.getDay()))
                .build();
    }

    default CinemaDTO entityToDTO(Cinema cinema){
        return CinemaDTO.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .title(cinema.getMovie().getTitle())
                .area(cinema.getTheater().getArea())
                .specificArea(cinema.getTheater().getSpecificArea())
                .display(cinema.getDisplay())
                .date(cinema.getDate())
                .seats(cinema.getSeats())
                .build();
    }

}

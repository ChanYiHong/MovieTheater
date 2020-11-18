package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Cinema;
import ChanuE.MovieTheater.dto.cinema.CinemaResponseDto;
import ChanuE.MovieTheater.dto.cinema.CinemaSaveRequestDto;
import ChanuE.MovieTheater.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public void saveCinema(CinemaSaveRequestDto dto){
        Cinema cinema = dto.toEntity();
        cinemaRepository.saveCinema(cinema);
    }

    public List<CinemaResponseDto> findAllCinemaByDate(Long dateId){
        List<Cinema> cinemas = cinemaRepository.findAllCinemaByDateId(dateId);
        return CinemaResponseDto.CinemaToCinemaResponseDtos(cinemas);
    }

}

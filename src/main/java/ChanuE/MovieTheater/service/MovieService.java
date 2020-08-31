package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.dto.movie.MovieSaveRequestDto;
import ChanuE.MovieTheater.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;

    public void saveMovie(MovieSaveRequestDto requestDto){
        Movie movie = requestDto.toEntity();
        movieRepository.save(movie);
    }

    public List<MovieResponseDto> findAllMovie(){
        List<Movie> movies = movieRepository.findAll();
        return new MovieResponseDto().movieToMovieResponseDto(movies);
    }
}

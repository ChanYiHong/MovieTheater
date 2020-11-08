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

    @Transactional
    public void saveMovie(MovieSaveRequestDto requestDto){
        Movie movie = requestDto.toEntity();
        checkDuplicateMovie(movie.getName());
        movieRepository.save(movie);
    }

    private void checkDuplicateMovie(String name){
        List<Movie> movies = movieRepository.findOneByName(name);
        if(!movies.isEmpty()){
            throw new IllegalStateException("Duplicate Movie Name!! Please type other movie name!!");
        }
    }

    public MovieResponseDto findOneMovieById(Long id){
        Movie movie = movieRepository.findOne(id);
        return MovieResponseDto.movieToMovieResponseDto(movie);
    }

    public MovieResponseDto findOneMovieByName(String name){
        List<Movie> movies = movieRepository.findOneByName(name);
        return MovieResponseDto.movieToMovieResponseDtos(movies).get(0);
    }

    public List<MovieResponseDto> findAllMovie(){
        List<Movie> movies = movieRepository.findAll();
        return MovieResponseDto.movieToMovieResponseDtos(movies);
    }
}

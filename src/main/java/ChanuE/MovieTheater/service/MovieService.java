package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.dto.movie.MovieSaveRequestDto;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import ChanuE.MovieTheater.repository.movie.MovieSearch;
import ChanuE.MovieTheater.repository.movie.MovieSpringDataJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieSpringDataJpaRepository movieRepository;

    @Transactional
    public Long saveMovie(MovieSaveRequestDto requestDto){
        Movie movie = dtoToEntity(requestDto);
        checkDuplicateMovie(movie.getMovieName());
        movieRepository.save(movie);
        return movie.getId();
    }

    private void checkDuplicateMovie(String name){
        List<Movie> movies = movieRepository.findMovieByMovieName(name);
        if(!movies.isEmpty()){
            throw new IllegalStateException("Duplicate Movie Name!! Please type other movie name!!");
        }
    }

    public MovieResponseDto findOne(Long id){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화가 없습니다. id = " + id));

        return entityToDto(movie);
    }

    public PageResponseDTO<Movie, MovieResponseDto> findAll(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Movie> movies = movieRepository.findAll(pageable);

        Function<Movie, MovieResponseDto> fn = movie -> {
            return MovieResponseDto.builder()
                    .id(movie.getId())
                    .movieName(movie.getMovieName())
                    .build();
        };
        return new PageResponseDTO<>(movies, fn);
    }

    public PageResponseDTO<Movie, MovieResponseDto> list(PageRequestDTO pageRequestDTO, MovieSearch movieSearch) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());
        Page<Movie> result = movieRepository.findAllBySearchCond(movieSearch, pageable);
        // Search condition, controller랑 view에 추가 하기 시작
        Function<Movie, MovieResponseDto> fn = this::entityToDto;
        return new PageResponseDTO<>(result, fn);
    }

    private MovieResponseDto entityToDto(Movie movie) {
        return MovieResponseDto.builder()
                .id(movie.getId())
                .movieName(movie.getMovieName())
                .build();
    }

    private Movie dtoToEntity(MovieSaveRequestDto dto) {
        return Movie.builder()
                .movieName(dto.getName())
                .build();
    }
}

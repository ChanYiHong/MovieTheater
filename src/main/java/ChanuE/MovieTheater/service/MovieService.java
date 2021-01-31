package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.dto.movie.MovieSaveRequestDto;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.movie.MovieSearch;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;

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

    // 리뷰 개수 까지 출력.
    public MovieResponseDto findOne(Long id){

        List<Object[]> result = movieRepository.findMovieWithReviewCount(id);
        Object[] objects = result.get(0);

        return entityToDto((Movie)objects[0], (Long)objects[1], (Double)objects[2]);
    }

    // 영화 예약 화면에서 단순한 영화 목록만 조회시 사용.
    public List<MovieResponseDto> findAll(){
        List<Movie> result = movieRepository.findAll();
        return result.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    // 영화 목록 페이징 추가.
    public PageResponseDTO<Object[], MovieResponseDto> list(PageRequestDTO pageRequestDTO, MovieSearch movieSearch) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());
        Page<Object[]> result = movieRepository.findAllBySearchCond(movieSearch, pageable);
        // Search condition, controller랑 view에 추가 하기 시작
        Function<Object[], MovieResponseDto> fn = entity -> {
            if(entity[2] == null){
                return entityToDto((Movie) entity[0], (Long) entity[1], 0);
            } else {
                return entityToDto(
                        (Movie) entity[0], (Long) entity[1], (Double) entity[2]);
            }
        };
        return new PageResponseDTO<>(result, fn);
    }

    private MovieResponseDto entityToDto(Movie movie) {
        return MovieResponseDto.builder()
                .id(movie.getId())
                .movieName(movie.getMovieName())
                .ageLimit(movie.getAgeLimit())
                .director(movie.getDirector())
                .runningTime(movie.getRunningTime())
                .description(movie.getDescription())
                .build();
    }

    private MovieResponseDto entityToDto(Movie movie, Long reviewCnt, double gradeAvg) {
        return MovieResponseDto.builder()
                .id(movie.getId())
                .movieName(movie.getMovieName())
                .ageLimit(movie.getAgeLimit())
                .director(movie.getDirector())
                .runningTime(movie.getRunningTime())
                .description(movie.getDescription())
                .gradeAvg(gradeAvg)
                .reviewCnt(reviewCnt.intValue())
                .build();
    }

    private Movie dtoToEntity(MovieSaveRequestDto dto) {
        return Movie.builder()
                .movieName(dto.getName())
                .director(dto.getDirector())
                .ageLimit(dto.getAgeLimit())
                .runningTime(dto.getRunningTime())
                .description(dto.getDescription())
                .build();
    }
}

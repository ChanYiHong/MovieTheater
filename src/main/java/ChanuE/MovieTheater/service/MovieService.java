package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.dto.movie.MovieResponseDTO;
import ChanuE.MovieTheater.dto.movie.MovieSaveRequestDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.movie.MovieSearch;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log4j2
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public Long saveMovie(MovieSaveRequestDTO requestDto){
        Movie movie = dtoToEntity(requestDto);
        checkDuplicateMovie(movie.getMovieName());
        movieRepository.save(movie);
        return movie.getId();
    }

    private void checkDuplicateMovie(String name){
        Optional<Movie> movies = movieRepository.findMovieByMovieName(name);
        if(!movies.isPresent()){
            throw new IllegalStateException("Duplicate Movie Name!! Please type other movie name!!");
        }
    }

    // 리뷰 개수 까지 출력.
    public MovieResponseDTO findOne(Long id){

        List<Object[]> result = movieRepository.findMovieWithReviewCount(id);
        Object[] objects = result.get(0);
        Movie movie = (Movie)objects[0];
        Long reviewCnt = (Long)objects[1];
        Double avg = (Double)objects[2];

        if(avg == null) return entityToDto(movie, reviewCnt, 0);
        else return entityToDto(movie, reviewCnt, avg);
    }

    // 영화 예약 화면에서 단순한 영화 목록만 조회시 사용.
    public List<MovieResponseDTO> findAll(){
        List<Movie> result = movieRepository.findAll();
        return result.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    // 영화 목록 페이징 추가.
    public PageResponseDTO<Object[], MovieResponseDTO> list(PageRequestDTO pageRequestDTO, MovieSearch movieSearch) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());
        Page<Object[]> result = movieRepository.findAllBySearchCond(movieSearch, pageable);
        // Search condition, controller랑 view에 추가 하기 시작
        Function<Object[], MovieResponseDTO> fn = entity -> {
            if(entity[2] == null){
                return entityToDto((Movie) entity[0], (Long) entity[1], 0);
            } else {
                return entityToDto(
                        (Movie) entity[0], (Long) entity[1], (Double) entity[2]);
            }
        };
        return new PageResponseDTO<>(result, fn);
    }

    private MovieResponseDTO entityToDto(Movie movie) {
        return MovieResponseDTO.builder()
                .id(movie.getId())
                .movieName(movie.getMovieName())
                .ageLimit(movie.getAgeLimit())
                .director(movie.getDirector())
                .runningTime(movie.getRunningTime())
                .description(movie.getDescription())
                .build();
    }

    private MovieResponseDTO entityToDto(Movie movie, Long reviewCnt, double gradeAvg) {
        return MovieResponseDTO.builder()
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

    private Movie dtoToEntity(MovieSaveRequestDTO dto) {
        return Movie.builder()
                .movieName(dto.getName())
                .director(dto.getDirector())
                .ageLimit(dto.getAgeLimit())
                .runningTime(dto.getRunningTime())
                .description(dto.getDescription())
                .build();
    }
}

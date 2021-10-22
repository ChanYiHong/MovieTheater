package ChanuE.MovieTheater.service.movie;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.MovieImage;
import ChanuE.MovieTheater.dto.movie.MovieApiSaveDTO;
import ChanuE.MovieTheater.dto.movie.MovieRatingHomeViewDTO;
import ChanuE.MovieTheater.dto.movie.MovieResponseDTO;
import ChanuE.MovieTheater.dto.movie.MovieRequestDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.movie.MovieSearch;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import ChanuE.MovieTheater.repository.movieimage.MovieImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;

    @Transactional
    public Long saveMovie(MovieRequestDTO movieRequestDTO, List<MovieImage> movieImages){
        Movie movie = dtoToEntity(movieRequestDTO, movieImages);
        checkDuplicateMovie(movie.getTitle());

        movieRepository.save(movie);

        log.info("영화 저장 {}", movie);

        for (MovieImage movieImage : movieImages) {
            movieImage.setMovie(movie);
            movieImageRepository.save(movieImage);

            log.info("영화 이미지 저장 {}", movieImage);
        }

        return movie.getId();
    }

    @Override
    public boolean saveMovieApi(MovieApiSaveDTO movieApiSaveDTO) {

        // duplicate check
        Optional<Movie> movie = movieRepository.findMovieByTitle(movieApiSaveDTO.getTitle());
        if (movie.isPresent()) {
            return false;
        }

        return true;
    }

    @Override
    public List<MovieRatingHomeViewDTO> getMovieForHomeView() {
        List<Object[]> result = movieRepository.findMovieWithAvgRatingForHomeView();

        List<MovieRatingHomeViewDTO> ret = new ArrayList<>();
        result.forEach(objects -> {
            Movie movie = (Movie) objects[0];
            Double avg = (Double) objects[1];

            ret.add(MovieRatingHomeViewDTO.builder()
                    .title(movie.getTitle())
                    .rating(avg).build());
        });

        return ret;
    }

    private void checkDuplicateMovie(String name){
        Optional<Movie> movies = movieRepository.findMovieByTitle(name);
        if(movies.isPresent()){
            throw new IllegalStateException("Duplicate Movie Name!! Please type other movie name!!");
        }
    }

    // 리뷰 개수 까지 출력.
    public MovieResponseDTO findOne(Long id){

        List<Object[]> result = movieRepository.findMovieWithImageAndReviewCount(id);
        Object[] objects = result.get(0);
        Movie movie = (Movie)objects[0];
        MovieImage movieImage = (MovieImage)objects[1];
        Long reviewCnt = (Long)objects[2];
        Double avg = (Double)objects[3];

        if(avg == null) return entityToDto(movie, movieImage, reviewCnt, 0);
        else return entityToDto(movie, movieImage, reviewCnt, avg);
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
            if(entity[3] == null){
                return entityToDto((Movie) entity[0], (MovieImage) entity[1], (Long) entity[2], 0);
            } else {
                return entityToDto(
                        (Movie) entity[0], (MovieImage) entity[1], (Long) entity[2], (Double) entity[3]);
            }
        };

        return new PageResponseDTO<>(result, fn);
    }
}

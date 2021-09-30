package ChanuE.MovieTheater.service.movie;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.MovieImage;
import ChanuE.MovieTheater.dto.movie.MovieApiSaveDTO;
import ChanuE.MovieTheater.dto.movie.MovieRatingHomeViewDTO;
import ChanuE.MovieTheater.dto.movie.MovieRequestDTO;
import ChanuE.MovieTheater.dto.movie.MovieResponseDTO;
import ChanuE.MovieTheater.dto.movieimage.MovieImageDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.movie.MovieSearch;

import java.util.List;

public interface MovieService {

    Long saveMovie(MovieRequestDTO movieRequestDTO, List<MovieImage> movieImages);

    // 관리자 영화 추가 API (네이버, Kobis api 연동)
    boolean saveMovieApi(MovieApiSaveDTO movieApiSaveDTO);

    // 홈 화면에 평점 높은 순으로
    List<MovieRatingHomeViewDTO> getMovieForHomeView();

    MovieResponseDTO findOne(Long id);

    List<MovieResponseDTO> findAll();

    PageResponseDTO<Object[], MovieResponseDTO> list(PageRequestDTO pageRequestDTO, MovieSearch movieSearch);

    default MovieResponseDTO entityToDto(Movie movie) {
        return MovieResponseDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .ageLimit(movie.getAgeLimit())
                .director(movie.getDirector())
                .runningTime(movie.getRunningTime())
                .description(movie.getDescription())
                .build();
    }

    default MovieResponseDTO entityToDto(Movie movie, MovieImage movieImage , Long reviewCnt, double gradeAvg) {

        if (movieImage == null) {
            return MovieResponseDTO.builder()
                    .id(movie.getId())
                    .title(movie.getTitle())
                    .ageLimit(movie.getAgeLimit())
                    .director(movie.getDirector())
                    .runningTime(movie.getRunningTime())
                    .description(movie.getDescription())
                    .gradeAvg(gradeAvg)
                    .reviewCnt(reviewCnt.intValue())
                    .movieImageDTO(new MovieImageDTO())
                    .build();
        }
        MovieImageDTO movieImageDTO = MovieImageDTO.builder()
                .uuid(movieImage.getUuid())
                .imageName(movieImage.getImgName())
                .path(movieImage.getPath())
                .build();


        return MovieResponseDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .ageLimit(movie.getAgeLimit())
                .director(movie.getDirector())
                .runningTime(movie.getRunningTime())
                .description(movie.getDescription())
                .gradeAvg(gradeAvg)
                .reviewCnt(reviewCnt.intValue())
                .movieImageDTO(movieImageDTO)
                .build();
    }

    default Movie dtoToEntity(MovieRequestDTO dto, List<MovieImage> movieImages) {
        return Movie.builder()
                .title(dto.getTitle())
                .director(dto.getDirector())
                .ageLimit(dto.getAgeLimit())
                .runningTime(dto.getRunningTime())
                .description(dto.getDescription())
                .movieImages(movieImages)
                .build();
    }

}

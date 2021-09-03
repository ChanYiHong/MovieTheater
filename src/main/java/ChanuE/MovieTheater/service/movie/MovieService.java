package ChanuE.MovieTheater.service.movie;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.MovieImage;
import ChanuE.MovieTheater.dto.movie.MovieRequestDTO;
import ChanuE.MovieTheater.dto.movie.MovieResponseDTO;
import ChanuE.MovieTheater.dto.movieimage.MovieImageDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.movie.MovieSearch;

import java.util.List;

public interface MovieService {

    Long saveMovie(MovieRequestDTO movieRequestDTO, List<MovieImage> movieImages);

    MovieResponseDTO findOne(Long id);

    List<MovieResponseDTO> findAll();

    PageResponseDTO<Object[], MovieResponseDTO> list(PageRequestDTO pageRequestDTO, MovieSearch movieSearch);

    default MovieResponseDTO entityToDto(Movie movie) {
        return MovieResponseDTO.builder()
                .id(movie.getId())
                .movieName(movie.getMovieName())
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
                    .movieName(movie.getMovieName())
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
                .movieName(movie.getMovieName())
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
                .movieName(dto.getMovieName())
                .director(dto.getDirector())
                .ageLimit(dto.getAgeLimit())
                .runningTime(dto.getRunningTime())
                .description(dto.getDescription())
                .movieImages(movieImages)
                .build();
    }

}

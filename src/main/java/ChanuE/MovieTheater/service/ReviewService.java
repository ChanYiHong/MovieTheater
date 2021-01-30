package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Review;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.review.ReviewDTO;

import java.util.List;

public interface ReviewService {

    Long register(ReviewDTO reviewDTO);

    PageResponseDTO<Review, ReviewDTO> list(PageRequestDTO pageRequestDTO, Long movieId);

    void modify(ReviewDTO reviewDTO);

    void remove(ReviewDTO reviewDTO);

    default Review dtoToEntity(ReviewDTO reviewDTO) {
        Movie movie = Movie.builder().id(reviewDTO.getMovieId()).build();
        return Review.builder()
                .id(reviewDTO.getId())
                .movie(movie)
                .content(reviewDTO.getContent())
                .writer(reviewDTO.getWriter())
                .grade(reviewDTO.getGrade())
                .build();
    }

    default ReviewDTO entityToDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .movieId(review.getMovie().getId())
                .content(review.getContent())
                .writer(review.getWriter())
                .grade(review.getGrade())
                .build();
    }
}

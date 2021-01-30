package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Review;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.review.ReviewDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {

    @Autowired ReviewService reviewService;

    @Test
    public void insertReviews() throws Exception {

        Movie movie = Movie.builder().id(1L).build();
        IntStream.rangeClosed(1,200).forEach(value -> {
            ReviewDTO reviewDTO = ReviewDTO.builder().movieId(movie.getId())
                    .content("Test.."+value)
                    .writer("Writer"+(int)(Math.random()*100)+1)
                    .grade((int)(Math.random() * 5)+1)
                    .build();

            reviewService.register(reviewDTO);
        });


        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResponseDTO<Review, ReviewDTO> result = reviewService.list(pageRequestDTO, 1L);

        List<ReviewDTO> dtoList = result.getDtoList();
        for (ReviewDTO reviewDTO : dtoList) {
            System.out.println(reviewDTO);
        }
    }

}
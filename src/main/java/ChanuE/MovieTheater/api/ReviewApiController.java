package ChanuE.MovieTheater.api;

import ChanuE.MovieTheater.domain.Review;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.review.ReviewDTO;
import ChanuE.MovieTheater.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews/")
@RequiredArgsConstructor
@Log4j2
public class ReviewApiController {

    private final ReviewService reviewService;

    @GetMapping("/{movieId}")
    public ResponseEntity<PageResponseDTO<Review, ReviewDTO>> getList(@PathVariable("movieId") Long movieId,
                                                                      PageRequestDTO pageRequestDTO) {
        log.info("GET Review list : movie ID " + movieId);

        PageResponseDTO<Review, ReviewDTO> result = reviewService.list(pageRequestDTO, movieId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

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
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public ResponseEntity<Long> saveReview(@RequestBody ReviewDTO reviewDTO) {

        log.info("POST Review save : " + reviewDTO);

        Long result = reviewService.register(reviewDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> modifyReview(@PathVariable("id") Long id, @RequestBody ReviewDTO reviewDTO) {

        log.info("Modify Review : " + reviewDTO);
        reviewService.modify(reviewDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> removeReview(@PathVariable Long id) {

        log.info("Delete Review : " + id);
        reviewService.remove(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
